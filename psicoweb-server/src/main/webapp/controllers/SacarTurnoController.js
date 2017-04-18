app.controller('SacarTurnoController', ['$scope', '$location', '$auth', '$http', function ($scope, $location, $auth, $http) {

        var urlTurnos = '/psicoweb-server/rest/turnos';
        var urlMotivosConsulta = '/psicoweb-server/rest/motivosConsulta';
        var motivoSeleccionadoId;
        $scope.nuevoMotivo = "";

        $scope.inicializarDatos = function () {
            obtenerTurnosDisponibles();
        };

        function obtenerTurnosDisponibles() {
            $http({
                method: 'GET',
                url: urlTurnos + '/dias/disponibles',
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                $scope.dias = response.data;
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de dias disponibles');
            });
        }

        function obtenerMotivoPorId(idBuscado) {
            var motivoSelec = {};
            if (idBuscado === "nm") {
                motivoSelec['id'] = null;
                motivoSelec['motivo'] = $scope.nuevoMotivo;
            } else {
                for (var i = 0; i < $scope.motivos.length; i++) {
                    if (parseInt($scope.motivos[i].id) === parseInt(idBuscado)) {
                        motivoSelec['id'] = idBuscado;
                        motivoSelec['motivo'] = $scope.motivos[i].motivo;
                    }
                }
            }
            return motivoSelec;
        }

        function obtenerMotivosDeConsulta() {
            $http({
                method: 'GET',
                url: urlMotivosConsulta,
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                $scope.motivos = response.data;
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de motivos de consulta');
            });
        }

        $scope.$watch("horaElegida", function (newValue, oldValue) {
            obtenerMotivosDeConsulta();
            motivoSeleccionadoId = undefined;
        });

        $scope.$watch("diaElegido", function (newValue, oldValue) {
            $scope.horas = null;
            motivoSeleccionadoId = undefined;
            $scope.horaElegida = undefined;
            $http({
                method: 'GET',
                url: urlTurnos + '/horas/disponibles/' + newValue,
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                $scope.horas = response.data;
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de horas disponibles');
            });
        });

        $scope.registrarTurno = function () {

            var turnoElegido = {
                dia: $scope.diaElegido,
                hora: $scope.horaElegida,
                usuario: '',
                motivo: obtenerMotivoPorId(motivoSeleccionadoId)
            };

            $http({
                method: 'POST',
                url: urlTurnos,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: turnoElegido
            }).then(function (response) {
                console.log(response.status + ' OK: El turno seleccionado se ha registrado correctamente');
                $location.path("/home");
            }).catch(function (response) {
                console.log(response.status + ': Error al registrar turno elegido');
            });
        };

        $scope.hayDiaYHorarioSeleccionado = function () {
            return ($scope.diaElegido !== undefined && $scope.horaElegida !== undefined);
        };

        $scope.isCompleto = function () {
            return (!$scope.hayDiaYHorarioSeleccionado() || motivoSeleccionadoId === undefined);
        };

        $scope.radioChanged = function (item) {
            motivoSeleccionadoId = item;
        };

        $scope.seleccionoOtroMotivo = function () {
            return !(motivoSeleccionadoId === "nm");
        };

    }]);