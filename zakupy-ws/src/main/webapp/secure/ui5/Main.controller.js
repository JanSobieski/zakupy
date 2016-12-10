var dataList = null;
var oModel = null;
var oTable = null;

sap.ui.controller("ui5.Main", {
	
	onSave : function () {
		   var list = encodeURIComponent(oModel.getJSON());
		   var url = '/zakupy-ws/jaxrs/zakupy/saveList?clientId=' + document.getElementById('clientId').value + '&list=' + list;
		   var oModel2 = new sap.ui.model.json.JSONModel();
		   oModel2.loadData( url, {}, false );
     },	
	onAddRow : function () {
		dataList.push({ware: "", bought: false});
        oModel.setData(dataList);
		oModel.refresh();

		jQuery.sap.delayedCall(10, this, function() {
			var data = oModel.getData();
			var l = data.length - 1;
	
			oTable.setSelectedIndex(l);
			var row = oTable.getRows()[l];
			var cell = row.getCells()[0]; 
			cell.focus();
		});
     },	
	
	onDeleteRows : function () {
		var idx = oTable.getSelectedIndex();
        if (idx !== -1) {
          var m = oTable.getModel();
          var data = m.getData();
          var removed = data.splice(idx, 1);
          m.setData(data);
		}
     },	

     onLogout : function () {
    	window.location.replace("../j_spring_security_logout");
        
     }	
/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf test001.Main
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf test001.Main
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf test001.Main
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf test001.Main
*/
//	onExit: function() {
//
//	}

});