var express = require('express');
var logicLevel = require('./logicLevel.js');
var app = express();
//var http = require('http');

var restsRes = null;

app.get("/", function(req, res)
{
  res.send("Hello, world!");
});

app.get("/rests/:latitute/:longitude/:radius", function(req, res)
{
  restsRes = res;
  logicLevel.getRests(req.params.latitute, req.params.longitude, req.params.radius, sendRestsResponse);
});

app.listen(8000, function()
{
  console.log(`Listening on port 8000`);
});

function sendRestsResponse(response)
{
  restsRes.send(response);
}
