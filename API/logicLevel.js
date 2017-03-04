var httpLevel = require('./httpLevel.js');

exports.getRests = function(latitute, longitude, radius, callback)
{
  var temp = {
    name: "Bob's Bob Shack",
    distance: 9.3,
    deal: "Free Bobs until the End of Time",
    hours: "-1:00 AM to 13:00 PM"
  }
  callback(temp);
}
