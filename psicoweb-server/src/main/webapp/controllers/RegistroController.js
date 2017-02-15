app.controller('RegistroController', ['$scope', '$location',
    'md5', '$httpParamSerializerJQLike', '$auth', function ($scope, $location, md5, $httpParamSerializerJQLike, $auth) {

        $scope.postearRegistro = function () {

            var usuario = {
                nombre: $scope.nombre,
                apellido: $scope.apellido,
                dni: $scope.dni,
                fechaNacimiento: $scope.fechaNacimiento,
                email: $scope.email,
                password: md5.createHash($scope.password || ''),
                administrador: false
            };

            $auth.signup(usuario,
                    {
                        headers: {
                            'Accept': 'text/plain',
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(function () {
                        $location.path("/home");
                    }).catch(function (response) {
                console.log(response);
            });
        };

    }]);
