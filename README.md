# MCP Server Project

This project is an MCP (Multi-Channel Processing) server that connects to a MySQL database. It provides an agent that can fetch table information and data from the database.

## Project Structure

```
mcp-server-project
├── src
│   ├── server.js          # Entry point of the MCP server
│   ├── agent.js           # Agent class for database interaction
│   ├── db
│   │   ├── connection.js   # Database connection setup
│   │   └── queries.js      # SQL query functions
│   └── utils
│       └── helpers.js      # Utility functions
├── package.json            # npm configuration file
├── .env                    # Environment variables for database connection
├── .gitignore              # Files and directories to ignore by Git
└── README.md               # Project documentation
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd mcp-server-project
   ```

2. **Install dependencies:**
   ```
   npm install
   ```

3. **Configure environment variables:**
   Create a `.env` file in the root directory and add your database connection settings:
   ```
   DB_HOST=your_database_host
   DB_USER=your_database_user
   DB_PASSWORD=your_database_password
   DB_NAME=your_database_name
   ```

4. **Start the server:**
   ```
   npm start
   ```

## Usage

Once the server is running, you can interact with the MCP agent to fetch table information and data from your MySQL database. Refer to the `src/agent.js` file for available methods and their usage.

## Contributing

Feel free to submit issues or pull requests for improvements or bug fixes.