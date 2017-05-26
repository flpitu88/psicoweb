app.controller('RegistroController', ['$scope', '$location',
    'md5', '$httpParamSerializerJQLike', '$auth', function ($scope, $location, md5, $httpParamSerializerJQLike, $auth) {

        $scope.postearRegistro = function () {

            var usuario = {
                nombre: $scope.nombre,
                apellido: $scope.apellido,
                dni: $scope.dni,
                fechaNacimiento: $scope.fechaNacimiento,
                email: $scope.email,
                password: md5.createHash($scope.password || ''),
                administrador: false
            };

            $auth.signup(usuario,
                    {
                        headers: {
                            'Accept': 'text/plain',
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(function () {
                        $location.path("/home");
                    }).catch(function (response) {
                console.log(response);
            });
        };

        $scope.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yyyy',
            maxDate: new Date(2020, 5, 22),
            minDate: new Date(),
            startingDay: 1
        };

        // Disable weekend selection
        function disabled(data) {
            var date = data.date,
                    mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }

        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.format = 'dd/MM/yyyy';

        $scope.popup1 = {
            opened: false
        };

        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var afterTomorrow = new Date();
        afterTomorrow.setDate(tomorrow.getDate() + 1);

        $scope.events = [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];

    }]);
