var fs = require('fs');
var mapsApi = require('@google/maps');
var mapsApiClient = null;
fs.readFile('./.apiKeys/googleMaps', 'utf8', function(err, data) {
  if(err) {
    return console.log(err);
  }
  mapsApiClient = mapsApi.createClient({
    key: data.substring(0,data.length - 1)
  });
});

exports.getDirection = function(start,dest,mode,points,alternatives,highways,tolls,callback)
{
  mapsApiClient.directions({
    origin: start,
    destination: dest,
    travelMode: mode,
    optimizeWaypoints: points,
    provideRouteAlternatives: alternatives,
    avoidHighways: highways,
    avoidTolls: tolls
  }, function(err,res){
    if(!err)
    {
      callback(res.json.results);
    }
    else {
			console.log(err);
    }
  });
}
