Application.$controller("TotalProd_PartialPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };


    /*
    * This method is called before fetching the records.
    */
    $scope.LV_ProductDataonBeforeListRecords = function(variable, dataFilter, options) {
        var data = variable._downgradeInputData(dataFilter);

        //Please make your changes to the existing old callbacks.
        var response = $scope.LV_ProductDataonBeforeUpdate(variable, data, options);
        if (response === false) {
            return false;
        }
        return variable._upgradeInputData(response, data);
    };

    $scope.LV_ProductDataonBeforeUpdate = function(variable, data) {
        var cat = $scope.activePageName == 'AllProducts' ? '' : $scope.activePageName;
        data.category = {
            'value': cat
        }
        $scope.$root.setStatusData(data, _.get($scope, ['pageParams', 'GetTotalStock']));
        $scope.Variables.LV_ProductData.orderBy = _.get($scope, ['pageParams', 'Sorting']);

    };


    $scope.anchor_viewClick = function($event, $isolateScope, item, currentItemWidgets) {
        $scope.Widgets.dialog_prodSpec.open($scope);
    };

}]);

Application.$controller("dialog_prodSpecController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.dialog_prodSpecClose = function($event, $isolateScope) {
            $scope.Widgets.AllProducts.selecteditem = undefined;
        };

    }
]);