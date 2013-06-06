function productAction(id, actionType) {
	var params = {sku: "", name: "", price: "", category: ""};

	var elements = ["sku", "name", "price", "category"];

	$(elements).each(function(index, val){
		var key = val;
		var ele = key;
		
		if (id !== undefined && id !== null) ele = ele + "_" + id;

	    var element = $("#" + ele);
	    var value = element.val();
		
		params[key] = value;
	});

	makeRequest(params, actionType, id);
}

function makeRequest(params, actionType, id){
	switch (actionType){
		case "update":
			$.post("editproduct?id="+id, { 
				sku: params.sku, 
				name: params.name, 
				price: params.price, 
				category: params.category },
				function(data){
					// $('#'+id' td input')[0].value = params.sku;
					// $('#'+id' td input')[1].value = params.name;
					// $('#'+id' td input')[2].value = params.price;
					// $('#'+id' td select').val(params.category);
					$('#response').html("Update Complete");
				});

			break;
		case "delete":
			$.get("deleteproduct?id="+id, {  },
				function(data){
					$('#'+id).fadeOut(400);
					$('#response').html("Deletion Complete");
				});
			break;
		case "insert":
			$.post("product", { 
				sku: params.sku, 
				name: params.name, 
				price: params.price, 
				category: params.category },
				function(result){
					var response = $.parseJSON(result);
					console.log(response);
	  				console.log(actionType);

					if (response.success) {
						var selects = $('#category option').clone();
						console.log(response.id);
						console.log('cat: ' + response.category);
						var html = '<tr id="'+response.id+'"><td> '+response.id+' </td><td><input id="sku_'+response.id+'" size="15" name="sku" value="'+response.sku+'"></td><td><input id="name_'+response.id+'" size="15" name="name" value="'+response.name+'"></td><td><input id="price_'+response.id+'" size="15" name="price" value="'+response.price+'"></td><td><select id="category_'+response.id+'" name="category"></select></td><td><input type="button" id="update_'+response.id+'" value="Update" ><input type="button" id="delete_'+response.id+'" value="Delete"></td></tr>';
		

						$('#products_table').append(html);
						$('#update_'+response.id).attr("onclick", "productAction("+response.id+", 'update');return false;");
						$('#delete_'+response.id).attr("onclick", "productAction("+response.id+", 'delete');return false;");
						$('#category_'+response.id).append(selects);
						$('#category_'+response.id).val(response.category);
	
						console.log(id);
						$('#response').html("Insertion Complete");
					}
				});
			break;
	}


/*$.ajax({
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
	});*/
	
}