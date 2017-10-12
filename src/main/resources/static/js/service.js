var Service = Class.extend(function(){
 
    this.getJson = function(url, data) {
    	return $.getJSON(contextRoot + url, data);
    };
});

var UserService = Service.extend(function() {
	
	this.getUsers = function() {
		return this.getJson("/users.json");
	}
	
});