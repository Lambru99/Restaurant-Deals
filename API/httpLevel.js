var express = require('express');
var logicLevel = require('./logicLevel.js');
var app = express();
//var http = require('http');

var restsRes = null;
var dirRes = null;

app.get("/", function(req, res)
{
  var str = "hackjskn.tk/rests/lat/long/rad<br />"
  str = str + "{<br />";
  str = str + "\"restaurants\":[{<br />";
  str = str + "\"name\": name<br />";
  str = str + "\"address\": address<br />";
  str = str + "\"location\": latitude,longitude<br />";
  str = str + "\"image_url\": url<br />";
  str = str + "\"distance\": distance in meters<br />";
  str = str + "\"short_title\": short deal<br />";
  str = str + "\"title\": deal<br />";
  str = str + "\"fine_print\": fine print<br />";
  str = str + "\"url\": url<br />";
  str = str + "}]<br />"
  str = str + "}"
  res.send(str);
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
