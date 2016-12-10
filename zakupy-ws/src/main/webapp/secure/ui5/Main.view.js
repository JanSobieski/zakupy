sap.ui.jsview("ui5.Main", {  // this View file is called Main.view.js
   
   getControllerName: function() {
      return "ui5.Main";     // the Controller lives in Address.controller.js
   },

   createContent: function(oController) {
	   oTable = new sap.ui.table.Table({
		    selectionMode : sap.ui.table.SelectionMode.MultiToggleSelect,
		    selectionBehavior: sap.ui.table.SelectionBehavior.Row
		  });
	   
	   oTable.addColumn(new sap.ui.table.Column({
		    label: new sap.ui.commons.Label({text: "Przedmiot"}),
		    template: new sap.ui.commons.TextField({value:"{ware}"})
		  }));
	   oTable.addColumn(new sap.ui.table.Column({
		    label: new sap.ui.commons.Label({text: "Kupione"}),
		    template: new sap.ui.commons.CheckBox({checked: '{bought}'})
		  })); 
	   
//	   dataList = [
//	                      {ware: "masło", bought: true},
//	                      {ware: "jabłka", bought: false}
//	                    ];

	   oModel = new sap.ui.model.json.JSONModel();

	   //var url = 'http://localhost:8080/zakupy-ws/jaxrs/zakupy/get-list?clientId=' + document.getElementById('clientId').value;
	   var url = '/zakupy-ws/jaxrs/zakupy/get-list?clientId=' + document.getElementById('clientId').value;
	   oModel.loadData( url, {}, false );
	   dataList = oModel.getData();

         
	   
	   oTable.setModel(oModel);
	   oTable.bindRows("/");	   

	   
	// Create a BorderLayout instance
	   var oBorderLayout3 = new sap.ui.commons.layout.BorderLayout("BorderLayout3", {width: "600px", height: "500px"});
	   var iCount = 0;

	   var butSave = new sap.ui.commons.Button({
		   	text: 'Zapisz',
		   	press: [oController.onSave, oController]
		   	}
		   );
	   var butAddRow = new sap.ui.commons.Button({
	   	text: 'Dodaj',
	   	press: [oController.onAddRow, oController]
	   	}
	   );
	   var butDeleteRow = new sap.ui.commons.Button({
	   	text: 'Usuń',
	   	press: [oController.onDeleteRows, oController]
			}
	   );
	   var oTextView = new sap.ui.commons.TextView();
	   oTextView.setText("           ");	   
	   
	   var butLogout = new sap.ui.commons.Button({
		   	text: 'Logout',
		   	press: [oController.onLogout, oController]
				}
		   );

	   var horizLayout = new sap.ui.layout.HorizontalLayout( "horizLayout1", { content:[ butSave, butAddRow, butDeleteRow, oTextView ,butLogout ] });

//	   // Create areas of BorderLayout
	   oBorderLayout3.createArea(sap.ui.commons.layout.BorderLayoutAreaTypes.top, horizLayout );
	   oBorderLayout3.setAreaData(sap.ui.commons.layout.BorderLayoutAreaTypes.top, {
	   	size : "15%",
	   	contentAlign : "left",
	   	visible : true
	   	});
	   

	   
	   oBorderLayout3.createArea(sap.ui.commons.layout.BorderLayoutAreaTypes.center, oTable);
	   oBorderLayout3.setAreaData(sap.ui.commons.layout.BorderLayoutAreaTypes.center, {
		   size : "90%",
		   contentAlign : "center",
		   visible : true
	   	});

	   // Attach the BorderLayout to the page
	   return oBorderLayout3;	   
   }

});