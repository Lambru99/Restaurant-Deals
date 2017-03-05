var fs = require('fs');
var mapsApi = require('@google/maps');
var mapsApiClient = null;
fs.readFile('./.apiKeys/googleMaps', 'utf8', function(err, data) {
  if(err) {
    return console.log(err);
  }
  mapsApiClient = mapsApi.createClient({
    key: data
  });
});

exports.getDistance = function(location, address, mode, callback)
{
  mapsApiClient.distanceMatrix({
    origins: location,
    destinations: address,
    mode: mode
  }, function(err, response){
    if(!err)
    {
      callback(response.json.results);
    }
    else {
      console.log(err);
    }
  }
  );
}
