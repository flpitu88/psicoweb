app.controller('NavbarController', ['$scope', '$auth', '$location', function ($scope, $auth, $location) {

        $scope.estaAutenticado = function () {
            return $auth.isAuthenticated();
        };

        $scope.esAdministrador = function () {
            if ($auth.administrador === undefined
                    || $auth.administrador === null
                    || $auth.administrador === false){
                return false;
            } else {
                return true;
            }
        };

        $scope.cerrarSesion = function () {
            $auth.logout()
                    .then(function () {
                        console.log('El usuario cerro sesión');
                        $location.path("/home");
                    }).catch(function () {
                console.log('No se ha podido cerrar sesion');
            });
        };

    }]);