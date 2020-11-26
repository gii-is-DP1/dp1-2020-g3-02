/**
 * Abre un modal mediante su identificador.
 * 
 * 
 * @param idModal
 *            Identificador del modal. Por defecto, 'modal'.
 * @returns void
 */
function openModal(idModal) {
	if (idModal === undefined || idModal === null) {
		idModal = "modal";
	}
	$('#' + idModal).modal('show');
}

/**
 * Muestra un mensaje global en un modal con toastr.
 * 
 * @param message
 *            Mensaje a mostrar.
 * @param typeMessage
 *            Tipo de mensaje (error, succes, info, warning).
 * @returns void
 */
function showGlobalMessage(message, typeMessage) {
	// si no viene definido el tipo de mensaje, por defecto será de información
	// (info)
	if (typeMessage === undefined) {
		typeMessage = "info";
	}
	// permitir html, se realiza replace usando expresion regular y g (global)
	// flag.
	message = message.replace(/&lt;/g, '<');
	message = message.replace(/&gt;/g, '>');
	var lvlCfg = {
		info : {},
		warning : {},
		success : {},
		error : {
			"timeOut" : "0",
			"extendedTimeOut" : "0",
		}
	};
	var cfg = lvlCfg[typeMessage];
	toastr.clear();
	toastr[typeMessage](message, null, cfg);
}

/**
 * Construye el mensaje de error obtiendolo del html de la página de repuesta
 * messagesFragment.html y lo muestra mediante toastr
 * 
 * @param html
 *            Página html de mensajes (messagesFragment.html)
 * @param id
 *            Identificador del elemento (Opcional)s
 * @param idTextoValidacion
 *            Identificador del elemento de texto de validacion (Opcional)
 * @param idTextoExito
 *            Identificador del elemento donde se mostrará el mensaje en caso de
 *            éxito (Opcional, si no se indica se usará un Toastr)
 * @returns void
 */
function showGlobalToastrMessage(html, id, idTextoValidacion, idTextoExito) {
	var mensaje = "";
	var tipo = "";
	var esValidacion = false;
	var mensajeExito = $(html).find("#mensajeExito").html();
	var mensajeInfo = $(html).find("#mensajeInfo").html();
	var mensajeWarn = $(html).find("#mensajeWarn").html();
	var mensajeError = $(html).find("#mensajeError").html();
	var mensajeValidacion = $(html).find("#mensajeValidacion").html();

	if (mensajeExito !== undefined && mensajeExito !== '') {
		mensaje = mensajeExito;
		tipo = "success";
	} else if (mensajeInfo !== undefined && mensajeInfo !== '') {
		mensaje = mensajeInfo;
		tipo = "info";
	} else if (mensajeWarn !== undefined && mensajeWarn !== '') {
		mensaje = mensajeWarn;
		tipo = "warning";
	} else if (mensajeError !== undefined && mensajeError !== '') {
		mensaje = mensajeError;
		tipo = "error";
	} else if (mensajeValidacion !== undefined && mensajeValidacion !== '') {
		esValidacion = true;
		mensaje = mensajeValidacion;
		tipo = "error";
	}

	// Funciones para quitar los posibles mensajes de validación anteriores
	var limpiarError = function() {
		if (id) {
			$('#' + id).removeClass('is-invalid');
		}
		if (idTextoValidacion) {
			$('#' + idTextoValidacion).html('');
			$('#' + idTextoValidacion).addClass('d-none');
		}
	};
	var limpiarExito = function() {
		if (idTextoExito) {
			$('#' + idTextoExito).html('');
			$('#' + idTextoExito).addClass('d-none');
		}
	};

	if (idTextoExito && mensajeExito) {
		// mostrar mensaje de éxito pero sin toastr
		limpiarError();
		$('#' + idTextoExito).html(mensaje);
		$('#' + idTextoExito).removeClass('d-none');
	} else if (esValidacion) {
		// mostrar mensaje de validación en el input
		limpiarExito();
		$('#' + idTextoValidacion).html(mensaje);
		$('#' + idTextoValidacion).removeClass('d-none');
		$('#' + id).addClass('is-invalid');
	} else {
		// mostrar mensaje con toastr
		limpiarError();
		limpiarExito();
		if (mensaje !== "" && tipo !== "") {
			showGlobalMessage(mensaje, tipo);
		}
	}
}

/**
 * Carga la url indicada dentro de un elemento determinado.
 * 
 * @param url
 *            URL a cargar.
 * @param dataId
 *            Identificador de los datos a enviar.
 * @param idTarget
 *            Identificador del elemento de la página a recargar.
 * @param moveToTarget
 *            Indica si se posiciona la recarga de la página en el elemento
 *            idTarget. Por defecto, 'true'.
 * @param doLoad
 *            Indica si se visualiza el elemento cargando.
 * @param funcion
 *            Funcion opcional a ejecutar.
 */
function loadInto(url, dataId, idTarget, moveToTarget, doLoad, funcion) {
	console.log('loadInto');
	// si no se especifica el elemento a recargar, por defecto es true
	if (doLoad === undefined || doLoad === null) {
		doLoad = true;
	}

	if (doLoad) {
		// se muestra la capa cargando
		Loading.show();
	}

	// si no se especifica el elemento a recargar, por defecto será content
	if (idTarget === undefined || idTarget === null) {
		idTarget = 'content';
	}

	// si no se especifica la posición a recargar, por defecto será true
	if (moveToTarget === undefined || moveToTarget === null) {
		moveToTarget = true;
	}

	var data = null;
	if (dataId !== undefined || dataId !== null) {
		data = $("#" + dataId).serialize();
	}

	// se realiza la carga
	$("#" + idTarget)
			.load(
					url,
					data,
					function(response, status, xhr) {
						if (status === "error") {
							// control de errores
							if (response.trim() !== ''
									&& response.indexOf("codigoError")) {
								showGlobalMessage(getErrorMessage(response),
										"error");
							} else {
								var codigoError = "C\u00f3digo: " + xhr.status
										+ " <br> ";
								var descripcionError = "Se ha producido un error inesperado. Por favor, int\u00e9ntelo de nuevo, m\u00e1s tarde."
								var mensajeError = codigoError
										+ descripcionError;
								showGlobalMessage(mensajeError, "error");
							}
						}

						if (doLoad) {
							// se oculta la capa cargando
							Loading.hide();
						}

						// se muestran los mensajes
						showGlobalToastrMessage(response);

						if (moveToTarget) {
							// se hace scroll hacia el target
							$(document).scrollTop(
									$("#" + idTarget).offset().top);
						}

						// se lanza la función posterior al load
						if (funcion) {
							funcion();
						}
					});

	// se cancela la recarga del href
	return false;
}

/**
 * Realiza el submit del formulario indicado dentro de un elemento determinado.
 * 
 * @param formId
 *            Identificador del formulario a enviar.
 * @param idTarget
 *            Identificador del elemento de la página a recargar. Por defecto,
 *            'content'.
 * @param moveToTarget
 *            Indica si se posiciona la recarga de la página en el elemento
 *            idTarget. Por defecto, 'true'.
 * @param doLoad
 *            Indica si se visualiza el elemento cargando. Por defecto, true.
 * @param funcion
 *            Funcion opcional a ejecutar.
 */
function submitInto(formId, idTarget, moveToTarget, doLoad, funcion) {
	// si no se especifica el elemento a recargar, por defecto es true
	if (doLoad === undefined || doLoad === null) {
		doLoad = true;
	}

	if (doLoad) {
		// se muestra la capa cargando
		Loading.show();
	}

	// si no se especifica el elemento a recargar, por defecto será content
	if (idTarget === undefined || idTarget === null) {
		idTarget = "content";
	}

	// si no se especifica la posición a recargar, por defecto será true
	if (moveToTarget === undefined || moveToTarget === null) {
		moveToTarget = true;
	}

	var $form = $("#" + formId);
	// se realiza el submit
	$
			.post(
					$form.attr("action"),
					$form.serialize(),
					function(html, status, xhr) {
						if (status === "error") {
							// control de errores
							if (xhr.responseText.trim() !== ''
									&& xhr.responseText.indexOf("codigoError")) {
								showGlobalMessage(
										getErrorMessage(xhr.responseText),
										"error");
							} else {
								var codigoError = "C\u00f3digo: " + xhr.status
										+ " <br> ";
								var descripcionError = "Se ha producido un error inesperado. Por favor, int\u00e9ntelo de nuevo, m\u00e1s tarde."
								var mensajeError = codigoError
										+ descripcionError;
								showGlobalMessage(mensajeError, "error");
							}
						} else {
							// se actualiza el elemento
							$("#" + idTarget).html(html);

							// se muestran los mensajes
							showGlobalToastrMessage(html);
						}
					})
			.fail(
					function(jqXHR, textStatus, errorThrown) {
						// control de errores
						if (jqXHR.responseText.trim() !== ''
								&& jqXHR.responseText.indexOf("codigoError")) {
							showGlobalMessage(
									getErrorMessage(jqXHR.responseText),
									"error");
						} else {
							showGlobalMessage(
									"Se ha producido un error inesperado. Por favor, int\u00e9ntelo de nuevo, m\u00e1s tarde.",
									"error");
						}
					}).always(function() {
				if (doLoad) {
					// se oculta la capa cargando
					Loading.hide();
				}

				if (moveToTarget) {
					// se hace scroll hacia el target
					$(document).scrollTop($("#" + idTarget).offset().top);
				}

				// se lanza la función posterior al submit
				if (funcion) {
					funcion();
				}
			});

	// se cancela la recarga del href o la ejecución del submit
	return false;
}