app.controller('GestionTurnosController', ['$scope', '$auth', '$http', function ($scope, $auth, $http) {

        var urlTurnos = '/psicoweb-server/rest/turnos';

        $scope.mostrarCartel = function () {
            alert('¿Seguro que quiere cancelar el turno?');
        };

        $scope.cargarTurnos = function () {
            $http({
                method: 'GET',
                url: urlTurnos,
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function (response) {
                $scope.turnos = response.data;
            }).catch(function (response) {
                console.log(response.status + ': Error en el pedido de turnos asignados');
            });
        };

    }]);