const mysql = require('mysql2/promise');
const createConnection = require('./connection');

async function getTableInfo(tableName) {
    const connection = await createConnection();
    const [rows] = await connection.query(`SHOW COLUMNS FROM ??`, [tableName]);
    await connection.end();
    return rows;
}

async function getTableData(tableName) {
    const connection = await createConnection();
    const escapedTableName = mysql.escapeId(tableName);
    const [rows] = await connection.query(`SELECT * FROM ${escapedTableName}`);
    await connection.end();
    return rows;
}

module.exports = {
    getTableInfo,
    getTableData
};