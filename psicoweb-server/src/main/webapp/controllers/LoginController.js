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
                            'Accept': 'text/plain'}
                    })
                    .then(function (response) {
                        $auth.setToken(response.data);
                        console.log('El usuario ha iniciado sesion');
                        $location.path("/home");
                    }).catch(function (response) {
                console.log('No se ha podido iniciar sesion');
            });
        };

    }]);