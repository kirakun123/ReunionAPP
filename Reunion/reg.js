//const db = require('./dbConnections.js')
const dbConnection = require('./dbConnection')

const teste = dbConnection.sequelize.define('users', {
    email: {
        type: dbConnection.Sequelize.STRING
    },
    senha:{
        type: dbConnection.Sequelize.STRING
    } 
})

module.exports = reg;