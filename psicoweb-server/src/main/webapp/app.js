'use strict';

var app = angular.module('myApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'angular-md5']);

// Declare app level module which depends on views, and components
app.config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

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
                .when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'LoginController'
                })

                .otherwise({redirectTo: '/home'});
    }]);

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
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        };

        return RegistroFactory;
    }]);

app.factory('LoginFactory', ['$http', function ($http) {

        var urlAutenticacion = 'rest/autenticacion';
        var LoginFactory = {};

        LoginFactory.autenticar = function (credenciales, paramSerializer) {
            console.log(paramSerializer(credenciales));
            return $http({
                url: urlAutenticacion,
                method: 'POST',
                data: paramSerializer(credenciales),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        };

        return LoginFactory;
    }]);
