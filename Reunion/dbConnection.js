
const mysql = require('mysql');
const host = 'localhost';
const user ='root';
const password = 'mysql';
const database = process.env.DB_DATABASE || 'projeto';
module.exports = { host, user, password, database };
