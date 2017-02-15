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
                        console.log(response);
                        $auth.setToken(response.data);
                        $location.path("/home");
                    }).catch(function (response) {
                console.log(response);
            });
        };

    }]);