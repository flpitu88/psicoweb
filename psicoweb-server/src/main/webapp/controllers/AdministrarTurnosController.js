app.controller('AdministrarTurnosController', ['$scope', '$auth', '$http', '$uibModal', function ($scope, $auth, $http, $uibModal) {

        var urlTurnos = '/psicoweb-server/rest/turnos/filtrados';
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

        $scope.obtenerTurnos = function () {

            var filtroInforme = {
                fechaDesde: $scope.fechaDesde,
                fechaHasta: $scope.fechaHasta,
                paciente: $scope.idPaciente
            }

            console.log('Entre a obtener turnos');
            console.log('fechaDesde vale: ' + filtroInforme.fechaDesde);
            console.log('fechaHasta vale: ' + filtroInforme.fechaHasta);
            console.log('paciente vale: ' + filtroInforme.paciente);

            $http({
                method: 'GET',
                url: urlTurnos,
                data: filtroInforme,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                console.log('Recibi los turnos filtrados');
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de pacientes');
            });
        };

//        Configuracion de los date pickers
//

        $scope.format = 'dd/MM/yyyy';

        $scope.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yyyy',
            maxDate: new Date(),
            minDate: new Date(),
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