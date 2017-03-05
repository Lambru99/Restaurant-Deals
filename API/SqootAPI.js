var fs = require('fs');
var http = require('http');

var apiKey = null;
fs.readFile('./.apiKeys/sqoot', 'utf8', function(err, data) {
  if(err) {
    return console.log(err);
  }
	apiKey = data;
);

var per_page = 100;

exports.sqoot = function(loca, rad, pagenum, callback)
{
	options = {
				hostname:'api.sqoot.com';
				method: 'GET';
				path: `/v2/deals?api_key=${apiKey}&category=resteraunt&online=false&location=${loca}&radius=${rad}&page=${pagenum]}&per_page=${per_page}&order=distance`;
			  }
	http.request(options,function(res){
		callback(res);
	});
}
