function openAddModal() {
	document.getElementById("modal").style.display = "flex";
}



function closeAddModal() {
	$("#modal").css("display", "none");
}

var currentDate = new Date().toISOString().split('T')[0];
$('#PredictedDeliveryDate').val(currentDate);

