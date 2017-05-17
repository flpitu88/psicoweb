'use strict';

var app = angular.module('myApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'angular-md5', 'satellizer']);

// Declare app level module which depends on views, and components
app.config(['$locationProvider', '$routeProvider', '$authProvider', function ($locationProvider, $routeProvider, $authProvider) {
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
                .when('/logout', {
                    templateUrl: 'views/home.html',
                    controller: 'HomeController'
                })
                .when('/novedades', {
                    templateUrl: 'views/novedades.html',
                    controller: 'NovedadesController'
                })
                .when('/sacarTurno', {
                    templateUrl: 'views/sacarTurno.html',
                    controller: 'SacarTurnoController'
                })
                .when('/gestionTurnos', {
                    templateUrl: 'views/gestionTurnos.html',
                    controller: 'GestionTurnosController'
                })
                .when('/administrarTurnos', {
                    templateUrl: 'views/administrarTurnos.html',
                    controller: 'AdministrarTurnosController'
                })

                .otherwise({redirectTo: '/home'});

        $authProvider.loginUrl = "psicoweb-server/rest/autenticacion";
        $authProvider.signupUrl = "psicoweb-server/rest/usuarios";
        $authProvider.tokenName = "token";
        $authProvider.tokenHeader = 'Authorization';
        $authProvider.tokenType = 'Bearer';
        $authProvider.storageType = 'localStorage';
    }]);