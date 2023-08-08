function openAddModal() {
	$("#modal").css("display", "flex");
}

function closeAddModal() {
	$("#modal").css("display", "none");
}

function openEditModal(id, name, desc, price, qty) {
	$("#modalEdit").css("display", "flex");
	$("#productId").val(id);
	$("#editname").val(name);
	$("#editdescription").val(desc);
	$("#editprice").val(price);
	$("#editquantity").val(qty);
}

function closeEditModal() {
	$("#modalEdit").css("display", "none");
}

