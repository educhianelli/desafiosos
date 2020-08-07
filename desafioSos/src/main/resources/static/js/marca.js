function gravarMarca() {

	var marca = {
		nomeMarca : null
	};
	dwr.util.getValues(marca);
	MarcaRemoteProxy.salvar(marca, {

		callback : sucessoValidacao,

		timeout : 5000,

		errorHandler : erroValidacao

	});
	
	limpaFormulario();

	/*
	 * console.log(marca); console.log(marca.nomeMarca);
	 * mensagemValidacao(marca); MarcaRemoteProxy.salvar(marca.nomeMarca);
	 */
}

function erroValidacao() {
	$("#nomeMarca").addClass("is-invalid");
	$('#marcaError').show();
	setTimeout(function() {
		$('#marcaError').hide();
	}, 1800);
}
function sucessoValidacao() {
	$('.chzn-select').trigger("chosen:updated");
	console.log("OK")
	$("#nomeMarca").removeClass("is-invalid");
	$('#marcaSucesso').show();
	setTimeout(function() {
		$('#marcaSucesso').hide();
	}, 1800);
}


function limpaFormulario() {
	dwr.util.setValues({
		nomeMarca : null
	});
}
