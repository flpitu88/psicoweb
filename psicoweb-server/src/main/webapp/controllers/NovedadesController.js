app.controller('NovedadesController', ['$scope', '$location', '$auth', '$http', function ($scope, $location, $auth, $http) {

        var tituloError = 'Titulo No Encontrado';
        var contenidoError = 'Contenido No Encontrado';
        var urlNoticias = '/psicoweb-server/rest/noticias';

        var tituloComienzo = 'Comencemos';
        var contenidoComienzo = 'En esta seccion se colocaran todos los nuevos articulos para que pueda leer';

        $scope.tab = 0;
        $scope.tituloNoticia = tituloComienzo;
        $scope.contenidoNoticia = contenidoComienzo;

        $scope.isSet = function (tabNum) {
            return $scope.tab === tabNum;
        };

        $scope.obtenerNoticiaNro = function (noticiaNro) {

            $scope.tab = noticiaNro;

            if (noticiaNro === 0) {
                $scope.tituloNoticia = tituloComienzo;
                $scope.contenidoNoticia = contenidoComienzo;
            } else {

                $http({
                    method: 'GET',
                    url: urlNoticias + '/' + noticiaNro,
                    headers: {
                        'Accept': 'application/json'
                    }
                }).then(function successCallback(response) {
                    $scope.tituloNoticia = response.data.titulo;
                    $scope.contenidoNoticia = response.data.contenido;
                }, function errorCallback(response) {
                    console.log('Error en el pedido ' + response.status);
                    $scope.tituloNoticia = tituloError;
                    $scope.contenidoNoticia = contenidoError;
                });
            }
        };
    }]);
