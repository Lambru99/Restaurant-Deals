var httpLevel = require('./httpLevel.js');
var directionsApi = require('./directionsApi.js');

exports.getRests = function(latitude, longitude, radius, callback)
{
  var temp = {
    restaurants: [
    {
      name: "Bob's Bob Shack",
      address: "123 Bob Bob Lane",
      image_url: 'http://mylolface.com/assets/faces/happy-smile.jpg',
      distance: 9.3,
      deal: "Free Bobs until the End of Time",
      hours: "-1:00 AM to 13:00 PM"
    } ]
  };
  callback(temp);
}

exports.getDir = function(latitude, longitude, address, callback)
{
  var temp = {
    hehe: "XD"
  };
  callback(temp);
}
