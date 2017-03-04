exports.sqoot = function(loca, rad, pagenum)
{
	options = {
				hostname:'api.sqoot.com';
				method: 'GET';
				path: `/v2/deals?api_key=key&category=resteraunt&online=false&location=${loca}&radius=${rad}&page=${pagenum]}&per_page=100&order=distance`;	
			  }
	http.request(options,function(res){
		callback(res);
	});
}