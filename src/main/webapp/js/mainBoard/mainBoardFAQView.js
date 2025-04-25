
$(".tabItems").on("click", function(){
	
	
	tab = $(this).attr("data-tabName");
	console.log(tab);
	$("#tabNameInput").val(tab);
	$("#tabForm").submit();
	

});
