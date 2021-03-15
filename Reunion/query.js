module.exports = async (conn, q, params) => new Promise(
    (resolve, reject) => {
        const handler = (error, result) => {
            if (error) {
                reject(error);
                return;
            }
            resolve(result);
            console.log('executou!');
        }
        conn.query(q, params, handler);
    }).catch(console.log);




/*
connection.query(sqlQry, function(error, results, fields){
    if(error)
        res.json(error);
    else
        res.json(results);
    connection.end();
    console.log('executou!');
});
*/