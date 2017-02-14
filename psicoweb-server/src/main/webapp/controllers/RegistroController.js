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
                    .success(function (data) {
                        $location.path("/home");
                    })
                    .error(function (data) {
                        console.log(data);
                    });
        };

    }]);
