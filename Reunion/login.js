var express = require('express')
const dbConnection = require("./dbConnection");
const bodyParser = require('body-parser');

//const register = require("./reg")
//const app = express();
const router = express.Router();
//const port = 3000;
//
const connection = require('./connection');
const query = require('./query');

// parse application/x-www-form-urlencoded
//app.use(bodyParser.urlencoded({ extended: false }))
 // parse application/json
//app.use(bodyParser.json())
 

router.post('/auth', async(req,res) =>{
	const { email, password } = req.body;
	console.log("Email: " + email + " PW: " + password);
	if (email && password) {
		const conn = await connection(dbConnection).catch(e => {});
		const conexao = await query(conn,'SELECT * FROM users WHERE email = ? AND password = ?', [email, password]);
		res.send([{ conexao }]);
	}
});

router.get('/users/:id', async(req,res) =>{
	const {id} = req.params;    
	const conn = await connection(dbConnection).catch(e => {});
    const conexao = await query(conn, `SELECT * FROM users WHERE userID = ?`, [id])
		res.send([{ conexao }]);
});

router.post('/reg', async(req,res) =>{
	const { email, password, usernm } = req.body;
	//console.log("Email: " + email + " PW: " + password + " UN: "+ usernm);
	console.log("Email: " + email + " PW: " + password + " USR "+ usernm);
	const conn = await connection(dbConnection).catch(e => {});
	const conexao = await query(conn,`INSERT INTO users (email,  password, username) VALUE (?, ?, ?)`, [email, password, usernm])
})

router.post('/addDate', async(req,res) =>{
	const { reunionName, date, hour, userID} = req.body;
	const conn = await connection(dbConnection).catch(e => {});
	//const pegarUserID = await query(conn, `SELECT * FROM users WHERE userID = ?`, [id])
	const conexao = await query(conn,`INSERT INTO reunion (reunionName, date, hour, userID) VALUE (?, ?, ?, ?)`, [date])
})

router.post('/addDateGroup', async(req,res) =>{
	const { date, reunionName } = req.body;
	const conn = await connection(dbConnection).catch(e => {});
	const conexao = await query(conn,`INSERT INTO reunion (date, reunionName) VALUE (?)`, [date])
})

router.post('/addOwnerGroup', async(req,res) =>{
let sql = "INSERT into userlinks(owner) VALUES ('email')";
connection.query(sql, (err, result) => {
	if(err) {

	} else {
		let inserted_id = result.insertId;
		console.log(inserted_id);   
        res.status(200).json({message: ""});
	}
});
});

router.get('/home', async(req,res) =>{
	console.log("Reunião: " +  reunionName + " Marcada: " + date);
	const conn = await connection(dbConnection).catch(e => {});
	const conexao = await query(conn,'SELECT * FROM reunion WHERE reunionName = ? AND date = ?', [reunionName, date]);
})
module.exports = router;