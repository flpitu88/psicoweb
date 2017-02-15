app.controller('NavbarController', ['$scope', '$auth', function ($scope, $auth) {

        $scope.estaAutenticado = function () {
            console.log('El token es: ' + $auth.getToken());
            console.log('ENTRO A VER SI ESTA AUTENTICADO, y es: ' + $auth.isAuthenticated());
            return $auth.isAuthenticated();
        };

    }]);