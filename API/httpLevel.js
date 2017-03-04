var express = require('express');
var app = express();
//var http = require('http');

app.get("/", function(req, res)
{
  res.send("Hello, world!");
});

app.listen(80, function()
{
  Console.log(`Listening on port 80`);
});
