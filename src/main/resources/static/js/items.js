$(document).ready(function() {
	$.refreshItemTable = function() {
		table.rows().remove();
		service
		.getItems()
		.done(function(data){
			for (i = 0; i < data.length; i++) { 
				var item = data[i];
				table.row.add([item.itemCode, 
				               item.description, 
				               item.itemCost, 
				               item.assemblyCost, 
				               item.itemType,
				               item.id,
				               item
							   ]).draw(false);
			}
			table.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("refreshItemTable:" + jqXHR.status);
		});
	};
	
	$.refreshUsersTable = function(itemId) {
		usersTable.rows().remove();
		service
		.getUsersForItem(itemId)
		.done(function(data){
			for (i = 0; i < data.length; i++) { 
				var user = data[i];
				usersTable.row.add([user.firstName, 
							   user.lastName, 
							   user.email, 
							   user.phone, 
							   user
							   ]).draw(false);
			}
			usersTable.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("refreshUsersTable:" + jqXHR.errorThrown);
		});
	};
	$.selectedItem = function() {
		var rows = table.rows({selected: true});
		var item = rows.data()[0][6];
		return item;
	};
	
	$.addItem = function() {
		itemsForm.refresh();
		table.button( 'addItem:name' ).nodes().attr('href','#itemDialog').attr('data-toggle', 'modal')
		$("#itemDialogTitle").text("Add Item");
		itemsForm.newItem = true;
	};
	
	$.editItem = function() {
		var rows = table.rows({selected: true});
		table.button( 'editItem:name' ).nodes().attr('href','#').attr('data-toggle', 'modal')
		if(rows.count() == 0) {
			alert("Please select an Item to edit.");
		} else {
			itemsForm.refresh();
			table.button( 'editItem:name' ).nodes().attr('href','#itemDialog').attr('data-toggle', 'modal')
			var rows = table.rows({selected: true});
			var item = rows.data()[0][6];
			itemsForm.newItem = false;
			itemsForm.setData(item);
			$("#itemDialogTitle").text("Edit Item");			
		}
	};
	
	$.deleteItem = function() {
		var rows = table.rows({selected: true});
		if(rows.count() == 0) {
			alert("Please select an item to delete.");
		} else {
			if(confirm("Are you sure you want to delete ?")) {
				service.deleteItem({
					itemId : (rows.data()[0][5])
				}).done(function(result) {
					$.refreshItemTable();
				});
			}
		}
	};
	
	var service = new ItemService();

	/*
	 * Item Form
	 */
	var itemsForm = new Form($("#itemsForm"));

	itemsForm.onSubmit(function(data) {
		
		service.addItem(data).done(function(result){
			$.refreshItemTable();
		});
	});
	
	var table = $('#items-table').DataTable({
		select: {
			style: 'single'
		},
		scrollY: '30vh',
		scrollX: '100vh',
		scrollCollapse: false,
		dom: 'Bfrtip',
		buttons: [
			{
				text: '<i class="fa fa-plus fa-fw"></i>',
				action: $.addItem,
				name: 'addItem',
				titleAttr: 'Add Item'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: $.editItem,
				name: 'editItem',
				titleAttr: 'Edit Item'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: $.deleteItem,
				titleAttr: 'Delete Item'
			}
			]
	});

	table.button( 'addItem:name' ).nodes().attr('href','#itemDialog').attr('data-toggle', 'modal')
	table.button( 'editItem:name' ).nodes().attr('href','#itemDialog').attr('data-toggle', 'modal')
	
	
	service
	.getItems()
	.done(function(data){
		for (item in data) {
			table.row.add([data[item].itemCode, data[item].description, data[item].itemCost, 
				data[item].assemblyCost, data[item].itemType, data[item].id], data[item]).draw(false);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.status);
	});


	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".item-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	});
});
