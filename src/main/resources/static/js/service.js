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
	
	this.getStoresForUser = function(userId) {
		return this.getJson("/stores-for-user.json", {
			userId : userId
		});
	};

	this.deleteUser = function(userId) {
		return this.post("/delete-user.json", userId);
	};
	
	this.saveUser = function(user) {
		return this.post("/save-user.json", user);
	};

	this.getStoreSelections = function(userId) {
		return this.getJson("/store-selections-for-user.json", {
			userId : userId
		});
	};
	
	this.saveStoreAssignments = function(userId, storeIds) {
		return this.post("/save-store-assignments.json", {
			userId: userId,
			storeIds: storeIds
		});
	};
});

var StoreService = Service.extend(function() {
	
	this.getStores = function() {
		return this.getJson("/stores.json");
	};
	
	this.getUsersForStore = function(storeId) {
		return this.getJson("/users-for-store.json", {
			storeId : storeId
		});
	};
	
	this.addStore = function(store) {
		return this.post("/add-store.json", store);
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
	
	this.saveAssociate = function(associate) {
		return this.post("/save-associate.json", associate);
	};
	
});

var ItemService = Service.extend(function() {
	
	this.getItems = function() {
		return this.getJson("/items.json");
	};
	
	this.addItem = function(item) {
		return this.post("/add-item.json", item);
	};
	
	this.deleteItem = function(itemId) {
		return this.post("/delete-item.json", itemId);
	};
});

