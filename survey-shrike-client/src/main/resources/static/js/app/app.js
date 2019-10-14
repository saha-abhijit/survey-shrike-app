var surveyApp = angular.module("surveyApp", ['ngRoute', 'ngResource']);
surveyApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/login.html',
            controller: 'loginController'
        }).when('/home', {
        templateUrl: 'views/businessAdminHome.html',
        controller: 'businessAdminHomeController'
    });
});

surveyApp.controller('loginController', function ($rootScope, $scope, $location, authorizationService) {
    $scope.userAuthPayload = {
      email: '',
      password: ''
    };

    $scope.authorize = function () {
        authorizationService.authorizeUser($scope.userAuthPayload).then(function(success) {
            $rootScope.username = success.email;
            $location.path('/home');
        }, function(failure) {
            $location.path('/');
        });
    };
});

surveyApp.factory('authorizationService', function ($http, $q) {
    var dataFactory = {};
    var authUrl = 'http://localhost:8084';

    dataFactory.authorizeUser = function (userAuthPayload) {
        var url = authUrl + '/api/v1/auth/authorize';
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: url,
            data: userAuthPayload,
            responseType: 'application/json'
        }).then(function (success) {
            deferred.resolve(success.data);
        }, function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    return dataFactory;
});

surveyApp.controller('businessAdminHomeController', function ($rootScope, $scope, businessAdminHomeService) {
    $scope.loggedInUser = {
        firstName: null,
        lastName: null,
        email: null
    };

    $scope.business = {
        businessId: null,
        name: null,
        address: null,
        phoneNumber: null
    };

    $scope.surveyPayload = {
        surveyId: null,
        name: null,
        questions: []
    };

    $scope.surveys = [];

    $scope.surveyOpened = false;

    $scope.init = function () {
        businessAdminHomeService.getLoggedInUserDetails($rootScope.username).then(function (response) {
            $scope.loggedInUser.firstName = response.firstName;
            $scope.loggedInUser.lastName = response.lastName;
            $scope.loggedInUser.email = response.email;
            businessAdminHomeService.getBusinessByUser($scope.loggedInUser.email).then(function (success) {
                $scope.business.businessId = success.businessId;
                $scope.business.name = success.name;
                $scope.business.address = success.address;
                $scope.business.phoneNumber = success.phoneNumber;
                businessAdminHomeService.getSurveysByBusiness($scope.business.businessId).then(function (response) {
                    $scope.surveys = response;
                }, function (failure) {
                    console.log('Notify to show failure');
                })
            }, function (failure) {
                console.log('Notify to show failure');
            })
        }, function (failure) {
            console.log('Notify to show failure');
        });
    };

    $scope.openSurvey = function(surveyId) {
        businessAdminHomeService.getSurveyDetails(surveyId).then(function(response) {
            $scope.surveyPayload.surveyId = response.surveyId;
            $scope.surveyPayload.name = response.name;
            $scope.surveyPayload.questions = response.questions;
            $scope.surveyOpened = true;
        });
    };

    $scope.init();
});

surveyApp.factory('businessAdminHomeService', function ($http, $q) {
    var dataFactory = {};
    var authUrl = 'http://localhost:8084';
    var serviceUrl = 'http://localhost:8089';

    dataFactory.getLoggedInUserDetails = function (email) {
        var url = baseUrl + '/api/v1/auth/user';
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: url,
            params: {email: email},
            dataType: 'application/json'
        }).then(function (success) {
            deferred.resolve(success.data);
        }, function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    dataFactory.getBusinessByUser = function (email) {
        var url = baseUrl + '/api/v1/auth/user';
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: url,
            params: {email: email},
            dataType: 'application/json'
        }).then(function (success) {
            deferred.resolve(success.data);
        }, function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    dataFactory.getSurveysByBusiness = function (businessId) {
        var url = serviceUrl + '/api/v1/survey/business';
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: url,
            params: {businessId: businessId},
            dataType: 'application/json'
        }).then(function (success) {
            deferred.resolve(success.data);
        }, function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    dataFactory.getSurveyDetails = function (surveyId) {
        var url = serviceUrl + '/api/v1/survey';
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: url,
            params: {surveyId: surveyId},
            dataType: 'application/json'
        }).then(function (success) {
            deferred.resolve(success.data);
        }, function (error) {
            deferred.reject(error);
        });
        return deferred.promise;
    };

    return dataFactory;
});

surveyApp.controller('surveyManagementController', function ($scope) {
    $scope.question = {
        questionId: null,
        questionType: null,
        questionLine: null,
        options: [],
        order: null
    };

    $scope.option = {
        optionId: null,
        optionTitle: null,
        order: null
    };

    $scope.surveyOpened = false;
    $scope.questionOpened = false;

    $scope.surveyPayload = {
        surveyId: null,
        name: null,
        businessPayload: {
            businessId: null,
            name: null,
            address: null,
            phoneNumber: null
        },
        questions: []
    };

    $scope.openSurvey = function () {
        $scope.surveyOpened = true;
    };

    $scope.addQuestion = function () {
        $scope.questionOpened = true;
    };
});

