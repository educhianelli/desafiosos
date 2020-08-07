$(document).ready(function() {
	var tabela = $("#tabela-ativos").DataTable({
		processing : true,
		serverSide : true,
		responsive : true,
		lengthMenu : [ 10, 15, 20, 25 ],
		ajax : {
			url : "/tabela/server",
			data : "data"
		},
		columns : [ {
			data : 'id'
		}, {
			data : 'nome'
		}, {
			data : 'marca.nome'
		}, {
			data : 'descricao'
		}, {
			data : 'tombo'
		} ],
		dom : 'Bfrtip',
		buttons : [ {
			text : 'Editar',
			attr : {
				id : 'btn-editar',
				type : 'button'
			},
			enabled : false
		}, {
			text : 'Excluir',
			attr : {
				id : 'btn-excluir',
				type : 'button'
			},
			enabled : false
		} ]
	});

	// marcar/desmarcar botões ao clicar na ordenação
	$("#tabela-ativos thead").on('click', 'tr', function() {
		tabela.buttons().disable();
	});

	// marcar/desmarcar linhas clicadas
	$("#tabela-ativos tbody").on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
			tabela.buttons().disable();
		} else {
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			tabela.buttons().enable();
		}
	});

	// Botão para abrir modal de edição
	$("#btn-editar").on('click', function() {
		if (isSelectRow()) {

			var id = getPatrimonioId();
			$.ajax({
				method : "GET",
				url : "/patrimonios/" + id,
				beforeSend : function() {
					// removendo as mensagens
					$("span").closest('.error-span').remove();
					// remover as bordas vermelhas
					$(".is-invalid").removeClass("is-invalid");
					// abre o modal
					$("#modal-form").modal('show');
				},
				success : function(data) {
					$("#edt_nomePatrimonio").val(data.nome);
					$("#edt_marca").val(data.marca.id);
					$("#edt_descricaoPatrimonio").val(data.descricao);

				},
				error : function() {
					alert("Ops... algum erro ocorreu, tente novamente.");
				}
			});
		}

	});

	$("#btn-edit-modal").on("click", function() {
		var patrimonio = {};
		patrimonio.nome = $("#edt_nomePatrimonio").val();
		patrimonio.marca = $("#edt_marca").val();
		patrimonio.descricao = $("#edt_descricaoPatrimonio").val();

		$.ajax({

			method : "PUT",
			url : "/patrimonios/"+getPatrimonioId(),
			data : patrimonio,	
			sucess : function() {
				$("#modal-form").modal("hide");
				tabela.ajax.reload();
			}

		});
	});

	// Botão para abrir modal de exclusão
	$("#btn-excluir").on('click', function() {
		if (isSelectRow()) {
			$("#modal-delete").modal('show');
		}
	});

	// Botão para confir
	$("#btn-del-modal").on('click', function() {
		var id = getPatrimonioId();

		PatrimonioRemoteProxy.excluirPatrimonio(id, {

			callback : sucessoExclusao
		});

		/*
		 * $.ajax({ method : "GET", url : "/patrimonios/" + id, success :
		 * function() { $("#modal-delete").modal('hide');
		 * tabela.buttons().disable(); tabela.ajax.reload(); }, error :
		 * function() { alert("Erro"); } });
		 */
	});

	function sucessoExclusao() {
		$("#modal-delete").modal('hide');
		tabela.buttons().disable();
		tabela.ajax.reload();
		$('#sucessoExclusaoMsg').show();
		setTimeout(function() {
			$('#sucessoExclusaoMsg').hide();
		}, 2500);

	}

	function getPatrimonioId() {
		return tabela.row(tabela.$('tr.selected')).data().id;
	}
	function isSelectRow() {
		var trow = tabela.row(tabela.$('tr.selected'));
		return trow.data() !== undefined;
	}

});

/*
 * 
 * var dadosCache = {};
 * 
 * function init() { dwr.engine.setActiveReverseAjax(true);
 * dwr.engine.setNotifyServerOnPageUnload(true); } function listarPatrimonio() {
 * 
 * PatrimonioRemoteProxy.listarPatrimonio(tabelaPatrimonio); }
 * 
 * function tabelaPatrimonio(data) { dwr.util.removeAllRows("tablebody", {
 * filter : function(tr) { return (tr.id != "pattern"); } }); for (var i = 0; i <
 * data.length; i++) { patrimonio = data[i]; console.log(data[i]); codigo =
 * patrimonio.idPatrimonio; dwr.util.cloneNode("pattern", { idSuffix :
 * patrimonio.idPatrimonio }); dwr.util.setValue("tableId" + codigo,
 * patrimonio.idPatrimonio); dwr.util.setValue("tableNome" + codigo,
 * patrimonio.nomePatrimonio);
 * 
 * dwr.util.byId("pattern" + codigo).style.display = ""; dadosCache[codigo] =
 * patrimonio; } }
 */
