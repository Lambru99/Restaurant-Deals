var httpLevel = require('./httpLevel.js');

exports.getRests = function(latitute, longitude, radius, callback)
{
  var temp = {
    restaurants: [
    {
      name: "Bob's Bob Shack",
      image_url: 'http://mylolface.com/assets/faces/happy-smile.jpg',
      distance: 9.3,
      deal: "Free Bobs until the End of Time",
      hours: "-1:00 AM to 13:00 PM"
    } ]
  }
  callback(temp);
}
