app.controller('SacarTurnoController', ['$scope', '$location', '$auth', '$http', function ($scope, $location, $auth, $http) {

        var urlTurnos = '/psicoweb-server/rest/turnos';
        var urlMotivosConsulta = '/psicoweb-server/rest/motivosConsulta';
        var motivoSeleccionadoId;

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
                console.log(response.status + 'Error en el pedido de dias disponibles: ');
            });
        }

        function obtenerMotivoPorId(idBuscado) {
            console.log('Entro a obtener motivo de id: ' + idBuscado);
            for (var i = 0; i < $scope.motivos.length; i++) {
                console.log('Este motivo es: ' + JSON.stringify($scope.motivos[i]));
                if ($scope.motivos[i].id === idBuscado) {
                    console.log('El motivo seleccionado es: ' + JSON.stringify($scope.motivos[i]));
                    return $scope.motivos[i];
                }
            }

        }

        function obtenerMotivosDeConsulta() {
            console.log('Entro a ver los motivos de consulta');
            $http({
                method: 'GET',
                url: urlMotivosConsulta,
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                console.log('Los motivos son: ' + response.data);
                console.log(JSON.stringify(response.data));
                $scope.motivos = response.data;
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de motivos de consulta: ');
            });
        }

        $scope.$watch("horaElegida", function (newValue, oldValue) {
            obtenerMotivosDeConsulta();
        });

        $scope.$watch("diaElegido", function (newValue, oldValue) {
            $scope.horas = null;
            console.log('oldValue vale: ' + oldValue);
            console.log('newValue vale: ' + newValue);
            $http({
                method: 'GET',
                url: urlTurnos + '/horas/disponibles/' + newValue,
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                $scope.horas = response.data;
            }).catch(function (response) {
                console.log(response.status + 'Error en el pedido de horas disponibles: ');
            });
        });

        $scope.registrarTurno = function () {

            console.log('motivo: ' + motivoSeleccionadoId);

            var turnoElegido = {
                dia: $scope.diaElegido,
                hora: $scope.horaElegida,
                usuario: '',
                motivo: obtenerMotivoPorId(motivoSeleccionadoId)
            };

            console.log('El turno elegido es: ' + JSON.stringify(turnoElegido));

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
                console.log(response.status + 'Error al registrar turno elegido: ');
            });
        };

        $scope.hayDiaYHorarioSeleccionado = function () {
            return ($scope.diaElegido !== undefined && $scope.horaElegida !== undefined);
        };

        $scope.isCompleto = function () {
//            return (!$scope.hayDiaYHorarioSeleccionado() || $scope.motivoElegido === undefined);
            return false;
        };

        $scope.radioChanged = function (item) {
            console.log('en radio changed item.id vale: ' + item);
            motivoSeleccionadoId = item;
        };
    }]);