var httpLevel = require('./httpLevel.js');
var directionsApi = require('./directionsAPI.js');
var distanceApi = require('./distanceAPI.js');
var sqootApi = require('./SqootAPI');

exports.getRests = function(latitude, longitude, radius, callback)
{
  var location = `${latitude},${longitude}`;
  sqootApi.sqoot(location, radius, 1, function(response){
    var numDeals = response.deals.length;
    var deals = {
      restaurants: []
    }
    for(i = 0; i < numDeals; ++i)
    {
      var deal = response.deals[i].deal;
      var rest = {
        name: deal.merchant.name,
        address: (deal.merchant.address != null ? deal.merchant.address : ""),
        location: `${deal.merchant.latitude},${deal.merchant.longitude}`,
        image_url: deal.image_url + "&geometry=150x150",
        distance: 2,
        short_title: deal.short_title,
        title: deal.title,
        fine_print: deal.fine_print,
        url: deal.url
      }

      deals.restaurants.push(rest);
    }
    getDistances(location, deals, callback);
  });
}

function getDistances(location, deals, callback)
{
  var cnt = 0;
  for(i = 0; i < deals.restaurants.length; ++i)
  {
    distanceApi.getDistance(location, deals.restaurants[i].location, 'driving', function(index, response){
      deals.restaurants[index].distance = response.rows[0].elements[0].distance.value;
      ++cnt;
      if(cnt >= deals.restaurants.length - 1)
        callback(deals);
    }.bind(this, i));
  }
}

exports.getDir = function(latitude, longitude, address, callback)
{
  distanceApi.getDistance("955 Loop Road Richardson", "300 Orchid Circle, Cedar Park, TX", "driving", callback);
}
