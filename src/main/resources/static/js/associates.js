$(document).ready(function() {

	var service = new AssociateService();

	var table = $('#associates-table').DataTable({
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
					$("#associateDialogTitle").text("Add Associate");
				},
				name: 'addAssociate',
				titleAttr: 'Add Associate'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: function () {
					$("#associateDialogTitle").text("Edit Associate");
				},
				name: 'editAssociate',
				titleAttr: 'Edit Associate'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: function () {
					var rows = table.rows({selected: true});
					if(rows.count() == 0) {
						alert("Please select an associate to delete.");
					} else {
						service.deleteAssociate({
							associateId : (rows.data()[0][3])
						}).done(function(data) {
							alert(data.message);
						});
					}
				},
				titleAttr: 'Delete Associate'
			}
			]
	});

	table.button( 'addAssociate:name' ).nodes().attr('href','#associateDialog').attr('data-toggle', 'modal')
	table.button( 'editAssociate:name' ).nodes().attr('href','#associateDialog').attr('data-toggle', 'modal')
	
	
	service
	.getAssociates()
	.done(function(data){
		for (associate in data) {
			table.row.add([data[associate].name, data[associate].email, data[associate].phone, data[associate].id]).draw(false);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.status);
	});


	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".associate-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	} );
});
