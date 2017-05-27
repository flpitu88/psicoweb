app.controller('AdministrarTurnosController',
        ['$scope', '$http', '$base64',
            function ($scope, $http, $base64) {

                var urlTurnos = '/psicoweb-server/rest/turnos/filtrados';
                var urlInformes = '/psicoweb-server/rest/informes';
                var urlUsuarios = '/psicoweb-server/rest/usuarios';

                $scope.inicializarDatos = function () {
                    obtenerPacientes();
                };

                function obtenerPacientes() {
                    $http({
                        method: 'GET',
                        url: urlUsuarios,
                        headers: {
                            'Accept': 'application/json'
                        }
                    }).then(function (response) {
                        $scope.pacientes = response.data;
                    }).catch(function (response) {
                        console.log(response.status + ': Error en el pedido de pacientes');
                    });
                }

                function base64ABlob(b64String) {
                    var byteCharacters = atob(b64String);
                    var byteArrays = [];

                    for (var offset = 0; offset < byteCharacters.length; offset += 512) {
                        var slice = byteCharacters.slice(offset, offset + 512);

                        var byteNumbers = new Array(slice.length);
                        for (var i = 0; i < slice.length; i++) {
                            byteNumbers[i] = slice.charCodeAt(i);
                        }

                        var byteArray = new Uint8Array(byteNumbers);

                        byteArrays.push(byteArray);
                    }

                    var blob = new Blob(byteArrays, {type: 'application/pdf'});
                    return blob;
                }

                $scope.obtenerTurnos = function () {

                    var filtroInforme = {
                        fechaDesde: $scope.fechaDesde,
                        fechaHasta: $scope.fechaHasta,
                        paciente: $scope.idPaciente
                    };

                    console.log('Entre a obtener turnos');
                    console.log('fechaDesde vale: ' + filtroInforme.fechaDesde);
                    console.log('fechaHasta vale: ' + filtroInforme.fechaHasta);
                    console.log('paciente vale: ' + filtroInforme.paciente);

                    $http({
                        method: 'PUT',
                        url: urlTurnos,
                        data: filtroInforme,
                        headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                        }
                    }).then(function (response) {
                        console.log('Recibi los turnos filtrados');
                        $scope.turnos = response.data;
                        console.log('$scope.turnos vale: ' + $scope.turnos);
                    }).catch(function (response) {
                        console.log(response.status + ': Error en el pedido de pacientes');
                    });
                };

                $scope.generarInforme = function () {
                    var filtroInforme = {
                        fechaDesde: $scope.fechaDesde,
                        fechaHasta: $scope.fechaHasta,
                        paciente: $scope.idPaciente
                    };

                    $http({
                        method: 'PUT',
                        url: urlInformes,
                        data: filtroInforme,
                        headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'text/plain'
                        }
                    }).then(function (response) {
                        console.log('Recibi el pdf solicitado');
                        var contentType = 'application/pdf';
                        var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
                        if (urlCreator) {
                            var blob = base64ABlob(response.data);
                            var url = urlCreator.createObjectURL(blob);
                            var a = document.createElement("a");
                            document.body.appendChild(a);
                            a.style = "display: none";
                            a.href = url;
                            a.download = "informeTurnos.pdf"; //you may assign this value from header as well 
                            a.click();
                            window.URL.revokeObjectURL(url);
                        }
                    }).catch(function (response) {
                        console.log(response.status + ': Error al recibir el informe de turnos');
                    });
                };

//        Configuracion de los date pickers
//

                $scope.format = 'dd/MM/yyyy';

                $scope.dateOptions = {
                    dateDisabled: disabled,
                    formatYear: 'yyyy',
                    startingDay: 1
                };

                // Disable weekend selection
                function disabled(data) {
                    var date = data.date,
                            mode = data.mode;
                    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
                }

                $scope.open1 = function () {
                    $scope.popup1.opened = true;
                };

                $scope.popup1 = {
                    opened: false
                };

                $scope.open2 = function () {
                    $scope.popup2.opened = true;
                };

                $scope.popup2 = {
                    opened: false
                };

                var tomorrow = new Date();
                tomorrow.setDate(tomorrow.getDate() + 1);
                var afterTomorrow = new Date();
                afterTomorrow.setDate(tomorrow.getDate() + 1);

            }]);