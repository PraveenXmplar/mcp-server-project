const mysql = require('mysql2/promise');
require('dotenv').config();

async function createConnection() {
    const connection = await mysql.createConnection({
        host: process.env.DB_HOST,
        user: process.env.DB_USER,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_NAME
    });

    console.log('Connected to the MySQL database.');
    return connection;
}

module.exports = createConnection;