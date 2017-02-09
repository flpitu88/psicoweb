app.controller('RegistroController', ['$scope', 'RegistroFactory', 'md5', '$httpParamSerializerJQLike', function ($scope, Registro, md5, $httpParamSerializerJQLike) {

        var usuario = {
            'nombre': $scope.nombre,
            'apellido': $scope.apellido,
            'dni': $scope.dni,
            'email': $scope.email,
            'password': md5.createHash($scope.password || '')
        };

        $scope.registrarUsuario = function () {

            Registro.registrar(usuario, $httpParamSerializerJQLike)
                    .success(function (data) {
                        console.log(data);
                    })
                    .error(function (data) {
                        alert(data);
                    });
        };

    }]);