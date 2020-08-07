function init() {
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setNotifyServerOnPageUnload(true);
}
window.onload = init;
var dadosCache = {};

function salvar() {
	var patrimonio = {
		nomePatrimonio : null,
		marca : 0,
		descricaoPatrimonio : null
	};
	dwr.util.getValues(patrimonio);
	PatrimonioRemoteProxy.salvar(patrimonio, {

		callback : sucessoValidacaoPatrimonio,

		timeout : 5000,

		errorHandler : errorHandlerPatrimonio

	});
}
function errorHandlerPatrimonio() {
	var nomePatrimonio = $("#nomePatrimonio").val();
	var marcaId = $("#marca").val()
	console.log(nomePatrimonio)
	if (nomePatrimonio.length < 1)
		erroValidacaoNomePatrimonio();
	else if (marcaId == 0)
		erroValidacaoMarca();
}
function erroValidacaoMarca() {
	$("#nomePatrimonio").removeClass("is-invalid");
	$("#marca").addClass("is-invalid");
	$('#marcaErro').show();
	setTimeout(function() {
		$('#marcaErro').hide();
	}, 2500);
}
function erroValidacaoNomePatrimonio() {
	$("#nomePatrimonio").addClass("is-invalid");
	$("#marca").removeClass("is-invalid");

	$('#nomePatrimonioErro').show();
	setTimeout(function() {
		$('#nomePatrimonioErro').hide();
	}, 2500);
}
function sucessoValidacaoPatrimonio() {
	limparFormAdd();

	$("#marca").removeClass("is-invalid");
	$("#nomePatrimonio").removeClass("is-invalid");
	$('#patrimonioSucesso').show();
	setTimeout(function() {
		$('#patrimonioSucesso').hide();
	}, 2500);
}
function limparFormAdd() {
	dwr.util.setValues({
		nomePatrimonio : null,
		marca : 0,
		descricaoPatrimonio : null
	});

}

