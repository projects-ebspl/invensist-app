(function ( $ ) {
 
    $.fn.populateGearProps = function(object) {
    	for(var prop in object) {
    		this.attr("data.gear." + prop, object[prop]);
    	}
        return this;
    };
 
    $.fn.retrieveGearModel = function() {
    	alert(this.find("[data.gear.*]").length);
        return this;
    };

}( jQuery ));

var DualSelector = Class.extend(function(){
 
	this._available_items_panel_title = "Available Items";
	this._selected_items_panel_title = "Selected Items";
	
    this.constructor = function(dom) {
    	this.$DOM = $(dom);
    	this.$DOM.css("min-height", "300px");
    	this.refresh();
    };
    
    this.refresh = function() {
    	this.$DOM.empty();
    	var row = $("<div/>").addClass("row").css("height", "100%");
    	row.append(this.createAvailableItemsPanel());
    	row.append(this.createButtons());
    	row.append(this.createSelectedItemsPanel());
    	row.appendTo(this.$DOM);
    };
    
    this.createAvailableItemsPanel = function() {
    	// col
    	var col = $("<div/>").addClass("col-sm-5");
    	//   panel
    	var panel = $("<div/>").addClass("panel panel-default").appendTo(col);
    	//      heading
    	var heading = $("<div/>").text(this._available_items_panel_title).addClass("panel-heading").appendTo(panel);
    	//      body
    	var body = $("<div/>").addClass("panel-body").appendTo(panel);
    	this.availableItemsList = this.createList().attr("id", "gear-list-av").appendTo(body);
    	return col;
    };
    
    this.createButtons = function() {
    	this.buttons = $("<div/>").addClass("dual-selector-buttons col-sm-1").css("padding","100px 0px");
    	this.select = $("<button/>").appendTo(this.buttons).click(this.selectItems);
    	$("<span>").appendTo(this.select).append("<i>").addClass("fa fa-angle-double-right fa-fw");
    	this.remove = $("<button/>").appendTo(this.buttons).click(this.removeItems);
    	$("<span>").appendTo(this.remove).append("<i>").addClass("fa fa-angle-double-left fa-fw");
    	return this.buttons;
    };
    
    this.createSelectedItemsPanel = function() {
    	// col
    	var col = $("<div/>").addClass("col-sm-5");
    	//   panel
    	var panel = $("<div/>").addClass("panel panel-default").appendTo(col);
    	//      heading
    	var heading = $("<div/>").text(this._selected_items_panel_title).addClass("panel-heading").appendTo(panel);
    	//      body
    	var body = $("<div/>").addClass("panel-body").appendTo(panel);
    	this.selectedItemsList = this.createList().appendTo(body);
    	return col;
    };
    
    this.createList = function(){
    	return $("<select/>").attr("multiple","multiple").addClass("gear-list")
    			.css("width","100%").css("height","100%").css("min-height", "200px");
    };
    
    this.selectItems = function() {
    	this.availableItemsList.find("option:selected").attr("data.gear.selected","true").appendTo(this.selectedItemsList);
    };

    this.removeItems = function() {
    	this.selectedItemsList.find("option:selected").attr("data.gear.selected","false").appendTo(this.availableItemsList);
    };
    
    this.createOption = function(object){
    	return $("<option/>").populateGearProps(object).text(object.label);
    };
    
    this.setData = function(items){
    	this.availableItemsList.empty();
    	this.selectedItemsList.empty();
		for (i = 0; i < items.length; i++) { 
			var item = items[i];
			if(item.selected) {
				this.selectedItemsList.append(this.createOption(item).attr("data.gear.selected","true").data(item));
			} else {
				this.availableItemsList.append(this.createOption(item).attr("data.gear.selected","false").data(item));
			}
		}
    };
    
    this.selected = function(){
    	var data = [];
    	this.selectedItemsList.find("option").each(function(){
    		data.push($(this).data());
    	});
    	console.log(data);
    	return data;
    };
});

var Properties = Class.extend(function(){
	
	this.widthClass = "col-xs-6";
	
	this.constructor = function(dom){
		this.$DOM = dom;
	};
	
	/*
					<div class="form-group">
						<label for="firstName" class="control-label col-xs-4">First
							Name *</label>
						<div class="col-xs-5">
							<input type="text" class="form-control" id="firstName"
								name="firstName" placeholder="First Name" required="required" />
						</div>
					</div>
	 */
	this.refresh = function() {
		this.$DOM.empty();
		if($.isDefined(this.data)) {
			for(var i = 0; i < this.data.length; i++) {
				var pair = this.data[i];
				var formGroup = $("<div/>").addClass("form-group").appendTo(this.$DOM);
				$("<label/>").addClass("control-label col-lg-5").text(pair.label).appendTo(formGroup);
				var inputHolder = $("<div/>").addClass("col-lg-5").appendTo(formGroup);
				$("<p/>").addClass("form-control-static").text(pair.value).appendTo(inputHolder);
			}
		}
	};
	
	this.setData = function(data){
		this.data = data;
		this.refresh();
	};
});
