app.controller('SacarTurnoController', ['$scope', '$location', '$auth', '$http', function ($scope, $location, $auth, $http) {

        var urlTurnos = '/psicoweb-server/rest/turnos';

        $scope.obtenerTurnosDisponibles = function (noticiaNro) {

            $http({
                method: 'GET',
                url: urlTurnos + '/dias/disponibles',
                headers: {
                    'Accept': 'application/json'
                }
            }).then(function successCallback(response) {
                $scope.turnos = response.data;
            }, function errorCallback(response) {
                console.log('Error en el pedido ' + response.status);
            });

        };
    }]);