app.controller('LoginController', ['$scope', '$location',
    'md5', '$httpParamSerializerJQLike', '$auth', function ($scope, $location, md5, $httpParamSerializerJQLike, $auth) {

        $scope.autenticar = function () {

            var credenciales = {
                email: $scope.email,
                password: md5.createHash($scope.password || '')
            };

            $auth.login($.param(credenciales),
                    {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            'Accept': 'application/json'}
                    })
                    .then(function (response) {
                        console.log('El token es: ' + response.data.token);
                        $auth.setToken(response.data.token);
                        console.log('El usuario ha iniciado sesion');
                        $auth.administrador = response.data.administrador;
                        $location.path("/home");
                    }).catch(function (response) {
                console.log('No se ha podido iniciar sesion');
                console.log('Error: ' + response.status);
            });
        };

    }]);