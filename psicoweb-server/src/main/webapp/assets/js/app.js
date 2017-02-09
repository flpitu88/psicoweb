'use strict';

app = angular.module('psicoweb', [
    'ngRoute',
    'ngResource',
    'angular-md5'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl'
    })
            .when('/registro', {
                templateUrl: 'views/registro.html',
                controller: 'RegistroController'
            });
});

app.config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
});

app.factory('RegistroFactory', ['$http', function ($http) {

        var urlLogin = 'rest/usuarios';
        var RegistroFactory = {};

        RegistroFactory.registrar = function (usuario, paramSerializer) {
            console.log(paramSerializer(usuario));
            return $http({
                url: urlLogin,
                method: 'POST',
                data: paramSerializer(usuario),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        };
        return RegistroFactory;
    }]);
