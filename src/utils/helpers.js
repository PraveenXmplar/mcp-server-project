export function formatTableData(data) {
    return data.map(row => {
        return Object.fromEntries(
            Object.entries(row).map(([key, value]) => [key, String(value)])
        );
    });
}

export function handleError(error) {
    console.error('An error occurred:', error.message);
    return {
        success: false,
        message: error.message,
    };
}

export function validateTableName(tableName) {
    const regex = /^[a-zA-Z0-9_]+$/;
    if (!regex.test(tableName)) {
        throw new Error('Invalid table name. Only alphanumeric characters and underscores are allowed.');
    }
}