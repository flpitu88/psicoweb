app.controller('HomeController', ['$scope', '$location', '$auth', function ($scope, $location, $auth) {

        $scope.cerrarSesion = function () {

            $auth.removeToken();

            $auth.logout()
                    .then(function () {
                        // Desconectamos al usuario y lo redirijimos
                        $location.path("/");
                    });
        };


    }]);
