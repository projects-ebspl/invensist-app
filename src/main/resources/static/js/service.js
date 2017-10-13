var Service = Class.extend(function(){
 
    this.getJson = function(url, data) {
    	return $.getJSON(contextRoot + url, data);
    };

    this.post = function(url, data) {
    	return $.post(contextRoot + url, data);
    };
});

var UserService = Service.extend(function() {
	
	this.getUsers = function() {
		return this.getJson("/users.json");
	};
	
	this.deleteUser = function(userId) {
		return this.post("/delete-user.json", userId);
	};

});

var StoreService = Service.extend(function() {
	
	this.getStores = function() {
		return this.getJson("/stores.json");
	};
	
	this.deleteStore = function(storeId) {
		return this.post("/delete-store.json", storeId);
	};

});

var AssociateService = Service.extend(function() {
	
	this.getAssociates = function() {
		return this.getJson("/associates.json");
	};
	
	this.deleteAssociate = function(associateId) {
		return this.post("/delete-associate.json", associateId);
	};

});
