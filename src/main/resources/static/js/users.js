$(document).ready(function() {
	
	$.refreshUserTable = function() {
		service
		.getUsers()
		.done(function(data){
			for (user in data) {
				table.row.add([data[user].firstName, data[user].lastName, data[user].email, data[user].phone, data[user].id]).draw(false);
			}
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.errorThrown);
		});
	};
	
	$.getSelectedUsers = function() {
		table.rows({selected: true});
	};

	$.addUser = function() {
		usersForm.refresh();
		$("#userDialogTitle").text("Add User");
		$("#passwords").show();
	};
	
	$.editUser = function() {
		var rows = table.rows({selected: true});
		table.button( 'editUser:name' ).nodes().attr('href','#').attr('data-toggle', 'modal')
		if(rows.count() == 0) {
			alert("Please select a user to edit.");
		} else {
			usersForm.refresh();
			table.button( 'editUser:name' ).nodes().attr('href','#userDialog').attr('data-toggle', 'modal')
			$("#userDialogTitle").text("Edit User");
			$("#passwords").hide();
		}
	};
	
	$.deleteUser = function() {
		var rows = table.rows({selected: true});
		if(rows.count() == 0) {
			alert("Please select a user to delete.");
		} else {
			service.deleteUser({
				userId : (rows.data()[0][4])
			}).done(function(data) {
				alert(data.message);
			});
		}
	};
	
	var service = new UserService();
	
	/*
	 * User Form
	 */
	var usersForm = new Form($("#usersForm"));

	usersForm.onSubmit(function(data) {
		service.saveUser(data).done(function(result){
			$.refreshUserTable();
		});
	});
	
	usersForm.customValidationHandler(function(data){
		var valid = true;
		if(data.password != data.confirmPassword) {
			$("#usersForm").find(".password").addClass("has-error");
			$("#usersForm").find(".password-confirm-password-shoud-be-same").removeClass("hidden");
			valid = false;
		} else {
			$("#usersForm").find(".password").removeClass("has-error");
			$("#usersForm").find(".password-confirm-password-shoud-be-same").addClass("hidden");
		}
		
		var roleValid = data.user || data.planner || data.admin;
		if(roleValid) {
			$("#usersForm").find(".at-least-one-role").addClass("hidden");
			$("#usersForm").find(".role").removeClass("has-error");
		} else {
			$("#usersForm").find(".at-least-one-role").removeClass("hidden");
			$("#usersForm").find(".role").addClass("has-error");
		}
		
		return valid && roleValid;
	});
	
	

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
				action: $.addUser,
				name: 'addUser',
				titleAttr: 'Add User',
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: $.editUser,
				name: 'editUser',
				titleAttr: 'Edit User'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: $.deleteUser,
				titleAttr: 'Delete User'
			}
			]
	});

	table.button( 'addUser:name' ).nodes().attr('href','#userDialog').attr('data-toggle', 'modal')
	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".user-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	});
	
	
	$.refreshUserTable();
	
});
