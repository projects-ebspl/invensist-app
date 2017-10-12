$(document).ready(function() {
	var table = $('#users-table').DataTable({
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
					alert( 'Add User' );
				},
				titleAttr: 'Add User'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: function () {
					alert( 'Edit User' );
				},
				titleAttr: 'Edit User'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: function () {
					alert( 'Delete User' );
				},
				titleAttr: 'Delete User'
			}
		]
	});
	
	
	new UserService()
	.getUsers()
	.done(function(data){
		for (user in data) {
			table.row.add([data[user].firstName, data[user].lastName, data[user].email, data[user].phone]).draw(false);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.status);
	});


	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".user-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	} );
});
