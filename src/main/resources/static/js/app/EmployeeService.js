'use strict';

angular.module('HsbcDbSync').factory('EmployeeService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllEmployees: loadAllEmployees,
                getAllEmployeeDerby:getAllEmployeeDerby,
                getAllEmployees: getAllEmployees,
                createEmployee: createEmployee,
                dbSync:dbSync
            };

            return factory;

            function loadAllEmployees() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllEmployees(){
                return $localStorage.users;
            }

            function getAllEmployeeDerby(){
                return $localStorage.users;
            }


            function createEmployee(user) {
                console.log('Creating User');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllEmployees();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating User : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function dbSync() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.Employee_Sync)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.derbyEmp = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);