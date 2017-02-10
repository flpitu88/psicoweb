var app = angular.module('psicoweb', ['ngRoute', 'ngResource']);

app.config(function ($routeProvider) {

    $routeProvider.when('/', {
        templateUrl: 'views/home.html',
        controller: 'HomeController'
    })

            .when('/registro', {
                templateUrl: 'views/registro.html',
                controller: 'RegistroController'
            })

            .when('/home', {
                templateUrl: 'views/home.html',
                controller: 'HomeController'
            })

            .otherwise({
                redirectTo: '/'
            });
});

app.factory('RegistroFactory', ['$http', function ($http) {

        var urlUsuarios = 'rest/usuarios';
        var RegistroFactory = {};

        RegistroFactory.registrar = function (usuario, paramSerializer) {
            console.log(paramSerializer(usuario));
            return $http({
                url: urlUsuarios,
                method: 'POST',
                data: paramSerializer(usuario),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        };

        return RegistroFactory;
    }]);