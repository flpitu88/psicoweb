app.controller('RegistroController', ['$scope', '$location', 'RegistroFactory',
    'md5', '$httpParamSerializerJQLike', function ($scope, $location, RegistroFactory, md5, $httpParamSerializerJQLike) {

        $scope.postearRegistro = function () {

            var usuario = {
                nombre: $scope.nombre,
                apellido: $scope.apellido,
                dni: $scope.dni,
                fechaNacimiento: $scope.fechaNacimiento,
                email: $scope.email,
                password: md5.createHash($scope.password || '')
            };

            RegistroFactory.registrar(usuario, $httpParamSerializerJQLike)
                    .then(function (value) {
                        console.log(value);
                        $location.path("/home");
                    }, function (reason) {
                        console.log(reason);
                    });
        };

    }]);
