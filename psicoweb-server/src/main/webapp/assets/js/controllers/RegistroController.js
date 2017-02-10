'use strict';

app.controller('RegistroController', ['$scope', '$location', 'RegistroFactory', '$httpParamSerializerJQLike', function ($scope, $location, RegistroFactory, $httpParamSerializerJQLike) {

        $scope.postearRegistro = function () {

            var usuario = {
                nombre: $scope.nombre,
                apellido: $scope.apellido,
                dni: $scope.dni,
                email: $scope.email,
                password: $scope.password
            };

            console.log('ENTRO A POSTEAR REGISTRO!!!');

            RegistroFactory.registrar(usuario, $httpParamSerializerJQLike)
                    .success(function (data) {
                        console.log('salio bien');
                    })
                    .error(function (data) {
                        console.log(data);
                        console.log('salio mal');
                    });
        };

    }]);
