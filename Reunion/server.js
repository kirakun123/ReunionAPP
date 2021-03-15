const express = require('express');
const bodyParser = require('body-parser');
const dbConfig = require('./dbConnection');


const userRouter = require('./login');

const app = express();
const port = 3000;

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))
// parse various different custom JSON types as JSON
app.use(bodyParser.json({ type: 'application/json' }));
//app.use(bodyParser.json());

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header(
        'Access-Control-Allow-Headers',
        'Origin, X-Requested-With, Content-Type, Accept',
    );
    next();
});

app.use('/login', userRouter);

app.get('/', (req, res) => res.send('Hello World!'))
app.post('/', (req, res) => res.send('Hello POST World!'))
app.all('/ping', (req, res) => res.send(new Date()))

app.listen(port, () => console.log('current_local_ip ${$port}!'));