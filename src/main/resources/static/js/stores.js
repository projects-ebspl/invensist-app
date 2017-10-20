$(document).ready(function() {
	$.refreshStoreTable = function() {
		table.rows().remove();
		service
		.getStores()
		.done(function(data){
			for (i = 0; i < data.length; i++) {
				var store = data[i];
				table.row.add([store.name, store.type,store.id, store]).draw(false);
			}
			table.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("refreshStoreTable:" + jqXHR.status);
		});
	};

	
	$.refreshUsersTable = function(storeId) {
		usersTable.rows().remove();
		service
		.getUsersForStore(storeId)
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
	
	$.selectedStore = function() {
		var rows = table.rows({selected: true});
		var store = rows.data()[0][3];
		return store;
	};

	$.addStore = function() {
		
		storesForm.refresh();
		table.button( 'addStore:name' ).nodes().attr('href','#storeDialog').attr('data-toggle', 'modal')
		$("#storeDialogTitle").text("Add Store");
		storesForm.newStore = true;		
	};
	
	/*$.editUser = function() {
		var rows = table.rows({selected: true});
		table.button( 'editStore:name' ).nodes().attr('href','#').attr('data-toggle', 'modal')
		if(rows.count() == 0) {
			alert("Please select a Store to edit.");
		} else {
			storesForm.refresh();
			table.button( 'editUser:name' ).nodes().attr('href','#userDialog').attr('data-toggle', 'modal')
			var rows = table.rows({selected: true});
			var store = rows.data()[0][2];
			storesForm.newStore = false;
			storesForm.setData(store);
			$("#userDialogTitle").text("Edit Store");			
		}
	};
	
	$.deleteStore = function() {
		var rows = table.rows({selected: true});
		if(rows.count() == 0) {
			alert("Please select a store to delete.");
		} else {
			if(confirm("Are you sure you want to delete ?")) {
				service.deleteStore({
					storeId : (rows.data()[0][2].id)
				}).done(function(data) {
					$.refreshUserTable();
				});
			}
		}
	};*/
	
	/*
	 * Store Form
	 */
	var storesForm = new Form($("#storesForm"));
	
	storesForm.onSubmit(function(data) {
		
		service.addStore(data).done(function(result){
			$.refreshUserTable();
		});
	});
	
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
				action: $.addStore,
				name: 'addStore',
				titleAttr: 'Add Store'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: $.editStore,
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
							alert("deleteStore.done()" + data.message);
						});
					}
				},
				titleAttr: 'Delete Store'
			}
			]
	});

	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".store-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
		var rows = table.rows({selected: true});
		var store = rows.data()[0][3];		
		alert("store: " + JSON.stringify(store));
		$.refreshUsersTable(store.id);
	});

	table.button( 'addStore:name' ).nodes().attr('href','#storeDialog').attr('data-toggle', 'modal')
	table.button( 'editStore:name' ).nodes().attr('href','#storeDialog').attr('data-toggle', 'modal')
	
	


	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".store-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
	});
	
	$.refreshStoreTable();
	$(".fa-chevron-up").click(function() {
		$("#stores-table-row").hide();
		$(".fa-chevron-down").removeClass("hidden");
		$(this).addClass("hidden");
	});
	$(".fa-chevron-down").click(function() {
		$("#stores-table-row").show();
		$(".fa-chevron-down").addClass("hidden");
		$(".fa-chevron-up").removeClass("hidden");
	});
	
	var usersTable = $('#users-table').DataTable({
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
				text: 'Manage User Assignments <i class="fa fa-tasks fa-fw"></i>',
				name: 'assignUsers',
				action: $.assignUsers,
				titleAttr: 'Assign users to store',
			}
		]
	});
	
	table.columns.adjust().draw(false);
	
});
