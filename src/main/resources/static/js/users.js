$(document).ready(function() {
	$.refreshUserTable = function() {
		service
		.getUsers()
		.done(function(data){
			for (i = 0; i < data.length; i++) { 
				var user = data[i];
				table.row.add([user.firstName, 
							   user.lastName, 
							   user.email, 
							   user.phone, 
							   user.id,
							   user
							   ]).draw(false);
			}
			table.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.errorThrown);
		});
	};
	
	$.refreshStoresTable = function(userId) {
		storesTable.clear().draw();
		service
		.getStoresForUser(userId)
		.done(function(data){
			for (i = 0; i < data.length; i++) { 
				var store = data[i];
				storesTable.row.add([store.name, 
								store.type, 
								store.email, 
								store.id,
								store
							   ]).draw(false);
			}
			storesTable.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.errorThrown);
		});
	};

	$.selectedUser = function() {
		var rows = table.rows({selected: true});
		var user = rows.data()[0][5];
		return user;
	};

	$.addUser = function() {
		usersForm.refresh();
		table.button( 'addUser:name' ).nodes().attr('href','#userDialog').attr('data-toggle', 'modal')
		$("#userDialogTitle").text("Add User");
		usersForm.newUser = true;
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
			var rows = table.rows({selected: true});
			var user = rows.data()[0][5];
			usersForm.newUser = false;
			usersForm.setData(user);
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
	
	$.assignStores = function() {
		storesTable.button( 'assignStores:name' ).nodes().attr('href','#userStoreDialog').attr('data-toggle', 'modal')
		$("#userStoreDialogTitle").text("Assign Stores");
		service.getStoreSelections($.selectedUser().id).done(function(data){
			selector.setData(data);
		});
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
		if(usersForm.newUser) {
			if(!data.password || !data.confirmPassword || data.password != data.confirmPassword) {
				$("#usersForm").find(".password").addClass("has-error");
				$("#usersForm").find(".password-confirm-password-shoud-be-same").removeClass("hidden");
				valid = false;
			} else {
				$("#usersForm").find(".password").removeClass("has-error");
				$("#usersForm").find(".password-confirm-password-shoud-be-same").addClass("hidden");
			}
		}
		var roleValid = $.isDefined(data.user) || $.isDefined(data.planner) || $.isDefined(data.admin);
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

	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".user-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
		
		var rows = table.rows({selected: true});
		var user = rows.data()[0][5];
		props.setData(user);
		$.refreshStoresTable(user.id);

	});
	
	
	$.refreshUserTable();
	
	
	$(".fa-chevron-up").click(function() {
		$("#users-table-row").hide();
		$(".fa-chevron-down").removeClass("hidden");
		$(this).addClass("hidden");
	});
	$(".fa-chevron-down").click(function() {
		$("#users-table-row").show();
		$(".fa-chevron-down").addClass("hidden");
		$(".fa-chevron-up").removeClass("hidden");
	});

	var props = new Form($("#propsForm"));
	
	var storesTable = $('#stores-table').DataTable({
		select: {
			style: 'single'
		},
		width: "100%",
		height: "inherit",
		scrollable: 'y',		
		dom: 'Bfrtip',
		scrollCollapse: false,
		buttons: [
			{
				text: '<i class="fa fa-plus fa-fw"></i>',
				name: 'assignStores',
				action: $.assignStores,
				titleAttr: 'Assign Stores to user',
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				titleAttr: 'Remove store from user'
			}
			]
	});
	
	storesTable.columns.adjust().draw(false);
	
	
	storesTable.on( 'select', function ( e, dt, type, indexes ) {
		storesTable.columns.adjust().draw(false);
	});

	var selector = new DualSelector($(".dual-selector"));
	
	
});
