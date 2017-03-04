var express = require('express');
var logicLevel = require('./logicLevel.js');
var app = express();
//var http = require('http');

var restsRes = null;
var dirRes = null;

app.get("/", function(req, res)
{
  res.send("Hello, world!");
});

app.get("/rests/:latitude/:longitude/:radius", function(req, res)
{
  restsRes = res;
  logicLevel.getRests(req.params.latitude, req.params.longitude, req.params.radius, sendRestsResponse);
});

app.get("/dir/:latitude/:longitude/:address", function(req, res) {
  dirRes = res;
  logicLevel.getDir(req.params.latitude, req.params.longitude, req.params.address, sendDirResponse);
});
app.listen(8000, function()
{
  console.log(`Listening on port 8000`);
});

function sendRestsResponse(response)
{
  restsRes.send(response);
}

function sendDirResponse(response)
{
  dirRes.send(response);
}
