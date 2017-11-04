$(document).ready(function() {
	/* Service */
	var service = new AssociateService();

	/* Controllers */
	$.refreshListing = function(){
		table.clear().draw();
		service
		.getAssociates()
		.done(function(data){
			for (i = 0; i < data.length; i++) { 
				var associate = data[i];
				table.row.add([associate.name, 
					associate.email, 
					associate.phone, 
					associate
					]).draw(false);
			}

			table.row(':eq(0)', { page: 'current' }).select();
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		});
	};
	$.openAddAssociateDialog = function(){
		associatesForm.refresh();
		table.button( 'addAssociate:name' ).nodes().attr('href','#associateDialog').attr('data-toggle', 'modal')
		$("#associateDialogTitle").text("Add Associate Info");
	};
	$.openEditAssociateDialog = function(){
		var rows = table.rows({selected: true});
		table.button( 'editAssociate:name' ).nodes().attr('href','#').attr('data-toggle', 'modal')
		if(rows.count() == 0) {
			alert("Please select an associate to edit.");
		} else {
			associatesForm.refresh();
			table.button( 'editAssociate:name' ).nodes().attr('href','#associateDialog').attr('data-toggle', 'modal')
			associatesForm.setData($.selectedAssociate());
			$("#associateDialogTitle").text("Edit Associate Info");
		}


		$("#associateDialogTitle").text("Edit Associate Info");
	};
	$.saveAssociate = function(associate){
		console.log(" $$$$ " + JSON.stringify(associate));
		service.saveAssociate(associate).done(function(){
			$.refreshListing();
		});
	};
	$.deleteAssociate = function(){
		var rows = table.rows({selected: true});
		if(rows.count() == 0) {
			alert("Please select an associate to delete.");
		} else {
			service.deleteAssociate({
				associateId : (rows.data()[0][3]["id"])
			}).done(function(data) {
				$.refreshListing();
			});
		}
	};
	$.setInfo = function(associate){
		props.setData([
			{label : "Name", value: associate.name},
			{label : "Email", value: associate.email},
			{label : "Phone", value: associate.phone},
			{label : "Address", value: associate.address},
			{label : "Client", value: associate.client ? "Yes" : "No"},
			{label : "Vendour", value: associate.vendour ? "Yes" : "No"},
			{label : "Notes", value: associate.notes}
			]);
//		$("#associate-info").text("Component Under Construction");

	};
	$.setInvoices = function(associate){};
	$.setOrders = function(associate){};

	$.selectedAssociate = function() {
		var rows = table.rows({selected: true});
		var associate = rows.data()[0][3];
		return associate;
	};



	/* UI */
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
				action: $.openAddAssociateDialog,
				name: 'addAssociate',
				titleAttr: 'Add Associate'
			},
			{
				text: '<i class="fa fa-pencil fa-fw"></i>',
				action: $.openEditAssociateDialog,
				name: 'editAssociate',
				titleAttr: 'Edit Associate'
			},
			{
				text: '<i class="fa fa-trash-o fa-fw"></i>',
				action: $.deleteAssociate,
				titleAttr: 'Delete Associate'
			}
			]
	});




	table.on( 'select', function ( e, dt, type, indexes ) {
		if ( type === 'row' ) {
			$(".associate-properties").removeClass("hidden");
		}
		table.columns.adjust().draw();
		$.setInfo($.selectedAssociate());
	} );

	$.refreshListing();

	$(".fa-chevron-up").click(function() {
		$("#associates-table-row").hide();
		$(".fa-chevron-down").removeClass("hidden");
		$(this).addClass("hidden");
	});
	$(".fa-chevron-down").click(function() {
		$("#associates-table-row").show();
		$(".fa-chevron-down").addClass("hidden");
		$(".fa-chevron-up").removeClass("hidden");
	});

	/*
	 * Associate Form
	 */
	var associatesForm = new Form($("#associatesForm"));
	associatesForm.onSubmit($.saveAssociate);

	var props = new Properties($("#associate-info"));
});
