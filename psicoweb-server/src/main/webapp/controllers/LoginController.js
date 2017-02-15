app.controller('LoginController', ['$scope', '$location', 'LoginFactory',
    'md5', '$httpParamSerializerJQLike', function ($scope, $location, LoginFactory, md5, $httpParamSerializerJQLike) {

        $scope.autenticar = function () {

            var credenciales = {
                email: $scope.email,
                password: md5.createHash($scope.password || '')
            };

            LoginFactory.autenticar(credenciales, $httpParamSerializerJQLike)
                    .then(function (value) {
                        console.log(value);
                        $location.path("/home");
                    }, function (reason) {
                        console.log(reason);
                    });
        };

    }]);