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

        $scope.showModalConfirmacion = function () {
            $scope.aCancelar = {};
            $scope.$modalInstance = $uibModal.open({
                templateUrl: 'views/confirmacionModal.html',
                controller: 'ConfirmarCancelacionModalController'
            });
        };

    }]);

app.controller('ConfirmarCancelacionModalController', function ($scope, $uibModalInstance) {

    $scope.confirmarEliminacion = function () {
        console.log('confirme la eliminacion');
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.close();
    };

});
