$(document).ready(function() {

	var service = new ItemService();

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
				action: function () {
					$("#itemDialogTitle").text("Add Item");
				},
				name: 'addItem',
				titleAttr: 'Add Item'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: function () {
					$("#itemDialogTitle").text("Edit Item");
				},
				name: 'editItem',
				titleAttr: 'Edit Item'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: function () {
					var rows = table.rows({selected: true});
					if(rows.count() == 0) {
						alert("Please select an item to delete.");
					} else {
						service.deleteItem({
							itemId : (rows.data()[0][5])
						}).done(function(data) {
							alert(data.message);
						});
					}
				},
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
			table.row.add([data[item].code, data[item].description, data[item].itemcost, 
				data[item].assemblycost, data[item].type, data[item].id]).draw(false);
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
	} );
});
