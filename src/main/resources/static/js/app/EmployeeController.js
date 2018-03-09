'use strict';

angular.module('HsbcDbSync').controller('EmployeeController',
    ['EmployeeService', '$scope',  function( EmployeeService, $scope) {

        var self = this;
        self.employee = {};
        self.employees=[];

        self.submit = submit;
        self.getAllEmployee = getAllEmployee;
        self.getAllEmployeeDerby = getAllEmployeeDerby;
        self.reset = reset;
        self.dbSync = dbSync;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.employee.id === undefined || self.employee.id === null) {
                console.log('Saving New User', self.employee);
                createEmployee(self.employee);
            }
        }

        function createEmployee(employee) {
            console.log('About to create user');
            EmployeeService.createEmployee(employee)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.employee={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function getAllEmployeeDerby(){
            return EmployeeService.getAllEmployeeDerby();
        }

        function getAllEmployee(){
            return EmployeeService.getAllEmployees();
        }

        function dbSync(){
            console.log('About to create user');
            EmployeeService.dbSync();
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.employees={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);