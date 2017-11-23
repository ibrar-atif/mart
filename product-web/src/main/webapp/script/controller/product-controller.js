
dirApp.controller(
'ProductController',
function($rootScope, $q, $scope, $state, $stateParams,$http) { 
	
	$scope.productEntry = false;
	$scope.productList = [];
	
	
	
	
$scope.addNewProduct = function(newProduct){
		
    	$http.post('http://localhost:8080/product-web/' + 'product/addnew',newProduct)
    	.then(
    			function(response){ 
    				
    				$scope.total = undefined;
    				alert("Product Added "+response.data.name );
    				var index = $scope.productList.indexOf(newProduct);
    				if(index>-1){
    					$scope.productList.splice(index,1);
    				}
    				$scope.productList.push(response.data);
    				$scope.newProduct={};
    			},
    			function(errResponse){
    				return $q.reject(errResponse);
    			}
    	); 	
    
	}

$scope.removeProduct = function(product){
	
	$http.delete('http://localhost:8080/product-web/' + 'product/removeproduct/'+product.sid)
	.then(
			function(response){    				    				
				$scope.total = undefined;
				alert("Product Removed "+product.name );
				var index = $scope.productList.indexOf(product);
				if(index>-1){
					$scope.productList.splice(index,1);
				}
			},
			function(errResponse){
				return $q.reject(errResponse);
			}
	); 	

}
$scope.gettotoal = function(){
	$http.get('http://localhost:8080/product-web/' + 'product/gettotal')
	.then(
			function(response){    				    				
				
				$scope.total = response.data;
			},
			function(errResponse){
				return $q.reject(errResponse);
			}
	); 
}



$scope.editProduct = function(product){
	$scope.newProduct = product;
}
$scope.showProductEntry = function(){
	$scope.productEntry = true;
	$scope.newProduct = {};
	$scope.productList =[];
	$scope.total=undefined;
	
	$http.get('http://localhost:8080/product-web/' + 'product/neworder/')
	.then(
			function(response){
				
			},
			function(errResponse){
				return $q.reject(errResponse);
			}
	); 	


}
	
});