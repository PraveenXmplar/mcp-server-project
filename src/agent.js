const mysql = require('mysql2');

class Agent {
    constructor(dbConnection) {
        this.dbConnection = dbConnection;
    }

    async getTableInfo(tableName) {
        const query = `SHOW TABLE STATUS LIKE ?`;
        const [rows] = await this.dbConnection.execute(query, [tableName]);
        return rows;
    }

    async getTableData(tableName) {
        if (!tableName || typeof tableName !== 'string') {
            throw new Error('Invalid table name');
        }
        const escapedTableName = mysql.escapeId(tableName);
        const query = `SELECT * FROM ${escapedTableName}`;
        const [rows] = await this.dbConnection.execute(query);
        return rows;
    }
}

module.exports = Agent;