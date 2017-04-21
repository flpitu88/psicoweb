app.controller('GestionTurnosController', ['$scope', '$auth', '$http', '$uibModal', function ($scope, $auth, $http, $uibModal) {

        var urlTurnos = '/psicoweb-server/rest/turnos';

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

        $scope.showModalConfirmacion = function (idTurno) {
            $scope.aCancelar = idTurno;
            $scope.$modalInstance = $uibModal.open({
                templateUrl: 'views/confirmacionModal.html',
                controller: 'ConfirmarCancelacionModalController',
                scope: $scope
            });
        };

    }]);

app.controller('ConfirmarCancelacionModalController', function ($scope, $uibModalInstance, $http) {

    var urlTurnos = '/psicoweb-server/rest/turnos';

    function obtenerIndexDeElementoDeId(idBuscado) {
        for (var i = 0; i < $scope.turnos.length; i++) {
            if (parseInt($scope.turnos[i].id) === parseInt(idBuscado)) {
                return i;
            }
        }
        return -1;
    }

    $scope.confirmarEliminacion = function () {
        $http({
            method: 'DELETE',
            url: urlTurnos + "?id=" + $scope.aCancelar
        }).then(function (response) {
            var index = obtenerIndexDeElementoDeId($scope.aCancelar);
            console.log('index vale: ' + index);
            $scope.turnos.splice(index, 1);
            console.log(response.status + ': El turno se cancelo correctamente');
        }).catch(function (response) {
            console.log(response.status + ': Error en el pedido de cancelacion de turno');
        });

        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.close();
    };

});
