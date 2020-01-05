
var wafepaApp = angular.module("wafepaApp", ['ngRoute'])

wafepaApp.controller("activitiesCtrl", function($scope, $http, $location){
	var baseUrl = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function(){
		var promise = $http.get(baseUrl);
		promise.then(
			function success(res){
				
				$scope.activities = res.data;
			},
			function error(res){
				
				alert("Neuspesno dobavljanje!");
			}
		);
		console.log("Test");
	}
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.goToAdd = function(){
		$location.path("/activities/add");
	}
	
	$scope.doDelete = function(id){
		$http.delete(baseUrl + "/" + id).then(
			function success(res){
				getActivities();
			},
			function error(res){
				alert("Neuspesno bisanje aktivnosti");
			}
		);
	}
});


wafepaApp.controller("editActivityCtrl", function($scope, $http, $routeParams, $location){
	

	
	var url = "/api/activities/" + $routeParams.id;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(odg){
				alert("Neuspesno dobavljanje.");
			}
		);
	}
	
	getActivity();
	
	$scope.doEdit = function(){
		$http.put(url, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Neuspesno snimanje.")
			}
		);
	}
	
});


wafepaApp.controller("addActivityCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	$scope.doAdd = function(){
	
		$http.post(url, $scope.newActivity).then(
			function success(res){
				$location.path("/activities");
			},
			function error(res){
				alert("Neuspesno snimanje");
			}
		);
	}
	
});


wafepaApp.controller("recordsCtrl", function($scope, $http, $location){
	
	var url = "/api/records";
	var activitiesUrl = "/api/activities";
	
	
	$scope.records = [];
	$scope.activities = [];
	
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	$scope.newRecord.activityId = "";

	
	$scope.searchParams = {};
	$scope.searchParams.activityName = "";
	$scope.searchParams.minDuration = "";
	$scope.searchParams.intensity = "";

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getRecords = function(){
		
		var config = { params: {}};
		
		if($scope.searchParams.activityName != ""){
			config.params.activityName = $scope.searchParams.activityName;
		}
		if($scope.searchParams.minDuration != ""){
			config.params.minDuration = $scope.searchParams.minDuration;
		}
		if($scope.searchParams.intensity != ""){
			config.params.intensity = $scope.searchParams.intensity;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Neuspesno dobavljanje rezultata.");
			}
		);
	}
	
	getRecords();
	
	var getActivities = function(){
		var promise = $http.get(activitiesUrl);
		promise.then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(res){
				alert("Neuspesno dobavljanje aktivnosti.");
			}
		);
	}
	
	getActivities();
	
	
	
	$scope.doAdd = function(){
		
		$http.post(url, $scope.newRecord).then(
			function success(res){
				getRecords();
				
				
				$scope.newRecord.time = "";
				$scope.newRecord.duration = "";
				$scope.newRecord.intensity = "";
				$scope.newRecord.activityId = "";
				
			},
			function error(){
				alert("Neuspesno snimanje rezultata");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/records/edit/" + id);
	}
	
	$scope.doSearch = function(){
	
		$scope.pageNum = 0;
		getRecords();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getRecords();
	}
});



wafepaApp.controller("editRecordCtrl", function($scope, $http, $routeParams, $location){
	
	
	var recordUrl = "/api/records/" + $routeParams.id;
	var activitiesUrl = "/api/activities";

	
	$scope.record = {};
	$scope.record.time = "";
	$scope.record.duration = "";
	$scope.record.intensity = "";

	$scope.record.activityId = "";	
	
	
	$scope.activities = [];
	
	
	
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
				
			
			},
			function error(){
				alert("Neuspesno dobavljanje aktivnosti.");
			}
		);
	}
	
	
	
	var getRecord = function(){
		$http.get(recordUrl).then(
			function success(res){
				$scope.record = res.data;
			},
			function error(){
				alert("Neuspesno dobavljanje.");
			}
		);
	}
	
	
	getActivities();
	getRecord();
	
	$scope.doEdit = function(){
		$http.put(recordUrl, $scope.record).then(
			function success(){
				$location.path("/records");
			},
			function error(){
				alert("Doslo je do greske.");
			}
		);
	}
});

wafepaApp.controller('calc', function($scope, $http, $routeParams, $location) {
	    $scope.visina;
	    $scope.tezina;
	    $scope.calculate = function () {
	      $scope.bm = $scope.tezina/(($scope.visina)*$scope.visina);
	    }
	    
	    
	    
	    var url = "/api/bmi";
	    
	
		
		$scope.records = [];
	
		
		
		$scope.newBmi = {};
		$scope.newBmi.date = "";
		$scope.newBmi.bmi = "";
		$scope.newBmi.note = "";
	

		
		$scope.searchParams = {};
		

		$scope.pageNum = 0;
		$scope.totalPages = 1;
		
		var getRecords = function(){
			
			var config = { params: {}};
			

			
			config.params.pageNum = $scope.pageNum;
			
			var promise = $http.get(url, config);
			promise.then(
				function success(res){
					$scope.records = res.data;
					$scope.totalPages = res.headers("totalPages");
				},
				function error(){
					alert("Neuspesno dobavljanje.");
				}
			);
		}
		
		
		getRecords();
		
		
		
		
		
		$scope.doAdd = function(){
			console.log($scope.newBmi);
			$http.post(url, $scope.newBmi).then(
				function success(res){
					getRecords();
					
					
					$scope.newBmi.date = "";
					$scope.newBmi.bmi = "";
					$scope.newBmi.note = "";
					$location.path("/bmi-rezultati");
					
				},
				function error(){
					alert("Neuspesno snimanje bmi.");
				}
			);
		}
		
		
		
	
		$scope.changePage = function(direction){
			$scope.pageNum = $scope.pageNum + direction;
			getRecords();
		}
		
		
		$scope.doDelete = function(id){
			$http.delete(url + "/" + id).then(
				function success(res){
					getRecords();
				},
				function error(res){
					alert("Neuspesno brisanje.")
				}
			);
			console.log("Test");
		}
	});

wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
			
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.when('/records/edit/:id', {
			templateUrl : '/app/html/edit-record.html'
		})
		.when('/bmi-rezultati', {
			templateUrl : '/app/html/bmi-rezultati.html'
		})
		.when('/bmi', {
			templateUrl : '/app/html/bmi.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);