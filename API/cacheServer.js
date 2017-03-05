/*var logicLevel = require('./logicLevel.js');
var cManager = require('cache-manager');
var rStore = require('cache-manager-redis');

var rCache = cacheManager.caching({
    store: redisStore,
    host: 'localhost', // default value
    port: 6379, // default value
    auth_pass: 1360,
    db: 0,
    ttl: 86400
});

var ttl = 60;

// listen for redis connection error event
redisCache.store.events.on('redisError', function(error) {
    // handle error here
    console.log(error);
});

redisCache.set('foo', 'bar', { ttl: ttl }, function(err) {
    if (err) {
      throw err;
    }

    redisCache.get('foo', function(err, result) {
        console.log(result);
        // >> 'bar'
        redisCache.del('foo', function(err) {});
    });
});

function getUser(id, cb) {
    setTimeout(function () {
        console.log("Returning user from slow database.");
        cb(null, {id: id, name: 'Bob'});
    }, 100);
}

var userId = 123;
var key = 'user_' + userId;

// Note: ttl is optional in wrap()
redisCache.wrap(key, function (cb) {
    getUser(userId, cb);
}, { ttl: ttl }, function (err, user) {
    console.log(user);

    // Second time fetches user from redisCache
    redisCache.wrap(key, function (cb) {
        getUser(userId, cb);
    }, function (err, user) {
        console.log(user);
    });
});
*/
//dependencies needed
var app = require('express')();
var responseTime = require('response-time');
var axios = require('axios');
var redis = require('redis');
var distanceAPI = require('./distanceAPI.js');
var SqootAPI = require('./SqootAPI.js');

var client = redis.createClient();

client.on('error', function(err) {
  console.log("Error " + err);
});

app.set('port',(process.env.PORT || 6379));

app.use(responseTime());

var distance;
distanceAPI.getDistance(location,address,mode,function(response)
{
  distance = response;
});
var sqoot;
SqootAPI.sqoot(loca,rad,pagenum,function(response)
{
  sqoot = response;
});

client.get(function(error, result)); {

if (result) {
  res.send({"distance": result, "source": "redis cache"});
} else {
 .then()
}
