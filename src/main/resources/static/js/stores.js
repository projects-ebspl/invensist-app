$(document).ready(function() {

	var service = new StoreService();

	var table = $('#stores-table').DataTable({
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
					$("#storeDialogTitle").text("Add Store");
					$("#passwords").show();
				},
				name: 'addStore',
				titleAttr: 'Add Store'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: function () {
					$("#storeDialogTitle").text("Edit Store");
					$("#passwords").hide();
				},
				name: 'editStore',
				titleAttr: 'Edit Store'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: function () {
					var rows = table.rows({selected: true});
					if(rows.count() == 0) {
						alert("Please select a store to delete.");
					} else {
						service.deleteStore({
							storeId : (rows.data()[0][2])
						}).done(function(data) {
							alert(data.message);
						});
					}
				},
				titleAttr: 'Delete Store'
			}
			]
	});

	table.button( 'addStore:name' ).nodes().attr('href','#storeDialog').attr('data-toggle', 'modal')
	table.button( 'editStore:name' ).nodes().attr('href','#storeDialog').attr('data-toggle', 'modal')
	
	
	service
	.getStores()
	.done(function(data){
		for (store in data) {
			table.row.add([data[store].name, data[store].type, data[store].id]).draw(false);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.status);
	});


	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".store-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	} );
});
