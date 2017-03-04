var httpLevel = require('./httpLevel.js');

exports.getRests = function(latitute, longitude, radius, callback)
{
  var temp = {
    name: "Bob's Bob Shack",
    image-url: "http://vignette4.wikia.nocookie.net/supersmashbrosfanon/images/2/2a/Screen_Shot_2015-05-06_at_8.29.10_PM.png/revision/latest?cb=20150509164120",
    distance: 9.3,
    deal: "Free Bobs until the End of Time",
    hours: "-1:00 AM to 13:00 PM"
  }
  callback(temp);
}
