function editDebito(id){
	$.ajax({
		url: "/debito/" + id,
		success: function (data){
			$("#debitoModalHolder").html(data);
			$("#debitoModal").modal('show');
			
		}
	});
}

function filtrarDebitos(pagina){
	var nome = $("#nome_search").val();
	var status = $("#status_search").val();
	var dados = {"nomeCliente": nome, "status": status, "page": pagina};
	searchDebito(dados);
}

function searchDebito(dados){
//	alert(JSON.stringify(dados));
	$.ajax({
		url: "/pesquisarDebitos",
		contentType: "application/json; charset=utf-8", 
		type: "POST",
		data: JSON.stringify(dados),
		success: function (data) {
//			alert(data);
			$("#resultList").html(data);			
		}
	});
}

