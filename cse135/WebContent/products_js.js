function productAction(id, actionType) {
	var params = new Array();
	var pid = null;

	var elements = ["sku", "name", "price", "category"];

	$(elements).each(function(index, val){
		var key = val;
		var ele = key;
		
		if (id !== undefined && id !== null) ele = ele + "_" + id;

	    var element = $("#" + ele);
	    var value = element.val();
		
		params.push(key + "=" + value);
	});

	makeRequest(params, actionType, pid);
}

function makeRequest(params, actionType, pid){

	$.post("test.php", { 
		name: "John", 
		time: "2pm" 
	});


$.ajax({
	  type: 'POST',
	  url: "process_ajax_request.jsp?" + params.join("&"),
	  beforeSend:function(){
		//Update Stats
		$('#status').html('Request Sent');
	  },
	  success:function(result){
	  
	  var response = $.parseJSON(result);
	  
	  console.log(response);
	  console.log(actionType);
	  switch (actionType){
		case "insert":
			var html = '<tr id="' + response.pid + '">'+ '<td>' + response.id + '</td><td><input id="pid_' + response.id + '" value="' + response.pid + '" name="pid" size="15"/></td><td><input id="first_' + response.id + '" value="' + response.first + '" name="first" size="15"/></td><td><input id="middle_' + response.id + '" value="' + response.middle + '" name="middle" size="15"/></td><td><input id="last_' + response.id + '" value="' + response.last + '" name="last" size="15"/></td><td><input onclick="studentAction(\'' + response.id + '\',\'update\');" type="button" value="Update"></td><td><input onclick="studentAction(\'' + response.id + '\',\'delete\');" type="button" value="Delete"/></td></tr>';

			$('#students').append(html);
			$('#response').html("Insert Complete");
			break;
			
		case "update":
			$('#response').html(result.trim());
			break;
			
		case "delete":
			$('#response').html(result.trim());
			$('#'+pid).remove();
			break;
		}
	  },
	  error:function(){
		// Failed request
		$('#status').html('Oops! Error.');
	  }
	});
	
}