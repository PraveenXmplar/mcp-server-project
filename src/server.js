const express = require('express');
const Agent = require('./agent');
const createConnection = require('./db/connection');
const net = require('net');
const mysql = require('mysql2');

const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());

const checkPortAvailability = (port) => {
    return new Promise((resolve, reject) => {
        const tester = net.createServer()
            .once('error', (err) => {
                if (err.code === 'EADDRINUSE') {
                    reject(new Error(`Port ${port} is already in use.`));
                } else {
                    reject(err);
                }
            })
            .once('listening', () => {
                tester.once('close', () => resolve(true)).close();
            })
            .listen(port);
    });
};

const startServer = async () => {
    try {
        await checkPortAvailability(port);
        const dbConnection = await createConnection();
        const agent = new Agent(dbConnection);

        app.get('/table-info/:tableName', async (req, res) => {
            const tableName = req.params.tableName;
            try {
                const info = await agent.getTableInfo(tableName);
                res.json(info);
            } catch (error) {
                res.status(500).json({ error: error.message });
            }
        });

        app.get('/table-data/:tableName', async (req, res) => {
            const tableName = req.params.tableName;
            try {
                const data = await agent.getTableData(tableName);
                res.json(data);
            } catch (error) {
                res.status(500).json({ error: error.message });
            }
        });

        app.get('/schema', async (req, res) => {
            try {
                const [tables] = await dbConnection.execute('SHOW TABLES');
                res.json(tables);
            } catch (error) {
                res.status(500).json({ error: error.message });
            }
        });

        app.post('/fetch-data', async (req, res) => {
            const { tableName } = req.body;

            if (!tableName) {
                return res.status(400).json({ error: 'Table name is required' });
            }

            try {
                const escapedTableName = mysql.escapeId(tableName);
                const [rows] = await dbConnection.execute(`SELECT * FROM ${escapedTableName}`);
                res.json(rows);
            } catch (error) {
                res.status(500).json({ error: error.message });
            }
        });

        app.post('/custom-query', async (req, res) => {
            const { query } = req.body;

            if (!query || !query.trim().toUpperCase().startsWith('SELECT')) {
                return res.status(400).json({ error: 'Only SELECT queries are allowed' });
            }

            try {
                const [rows] = await dbConnection.execute(query);
                res.json(rows);
            } catch (error) {
                res.status(500).json({ error: error.message });
            }
        });

        app.listen(port, () => {
            console.log(`MCP server is running on http://localhost:${port}`);
        });
    } catch (error) {
        console.error('Error starting the server:', error.message);
    }
};

startServer();