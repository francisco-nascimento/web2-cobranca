function editDebito(id){
	$.ajax({
		url: "/debito/" + id,
		success: function (data){
			$("#debitoModalHolder").html(data);
			$("#debitoModal").modal('show');
			
		}
	});
}