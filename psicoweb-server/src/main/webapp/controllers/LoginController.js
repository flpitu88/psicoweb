app.controller('LoginController', ['$scope', '$location', 'LoginFactory',
    'md5', '$httpParamSerializerJQLike', function ($scope, $location, LoginFactory, md5, $httpParamSerializerJQLike) {

        $scope.autenticar = function () {
            
            var credenciales = {
                email: $scope.email,
                password: md5.createHash($scope.password || '')
            };

            LoginFactory.autenticar(credenciales, $httpParamSerializerJQLike)
                    .success(function (data) {
                        $location.path("/home");
                    })
                    .error(function (data) {
                        console.log(data);
                    });
        };

    }]);