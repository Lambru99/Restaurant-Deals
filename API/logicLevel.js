var httpLevel = require('./httpLevel.js');
var directionsApi = require('./directionsAPI.js');
var distanceApi = require('./distanceAPI.js');
var sqootApi = require('./SqootAPI');

exports.getRests = function(latitude, longitude, radius, callback)
{
  var location = `${latitude},${longitude}`;
  /*sqootApi.sqoot(location, radius, 1, function(response){
    callback(response);
    return;
    var numDeals = response.query.deals.length;
    var deals = {
      restaurants: []
    }
    for(i = 0; i < numDeals; ++i)
    {
      var deal = response.query.deals[i].deal;
      var rest = {
        name: deal.merchant.name,
        address: (deal.merchant.address != null ? deal.merchant.address : `${deal.merchant.latitude},${deal.merchant.longitude}`),
        image_url: deal.image_url,
        distance: null,
        short_title: deal.short_title,
        title: deal.title,
        fine_print: deal.fine_print,
        url: deal.url
      }
      deals.restaurants.push(rest);
    }
    //callback(deals);
  });*/
  var temp = {
    restaurants [
      {
        name: "Bob's Bob Shack",
        address: "123 Bob's Bob Lane",
        image_url: "http://mylolface.com/assets/faces/happy-smile.jpg",
        distance: 9.3,
        short_title: "Deal Short",
        title: "Free Bobs all day long!",
        fine_print: "With purchase of a Bob",
        url: "example.com"
      }
      {
        name: "Jane's Jane Shack",
        address: "123 Jane's Jane Lane",
        image_url: "http://mylolface.com/assets/faces/happy-smile.jpg",
        distance: 9.3,
        short_title: "Deal Short",
        title: "Free Janes all day long!",
        fine_print: "With purchase of a Jane",
        url: "example.com"
      }
    ]
  }
  callback(temp);
}

exports.getDir = function(latitude, longitude, address, callback)
{
  var temp = {
    hehe: "XD"
  };
  callback(temp);
}
