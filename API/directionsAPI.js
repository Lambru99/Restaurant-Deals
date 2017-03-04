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
