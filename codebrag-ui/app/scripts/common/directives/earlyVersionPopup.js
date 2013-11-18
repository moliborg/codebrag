angular.module('codebrag.common.directives').directive('earlyVersionPopup', function($rootScope) {

    function EarlyVersionPopup($scope) {

        $scope.isVisible = false;

        $scope.close = function() {
            $scope.isVisible = false;
        };

        $scope.openContactFormPopup = function() {
            $scope.close();
            $rootScope.$broadcast('openContactFormPopup');
        };

    }

    return {
        restrict: 'E',
        replace: true,
        scope: {},
        templateUrl: 'views/popups/earlyVersionPopup.html',
        controller: EarlyVersionPopup,
        link: function(scope, el) {
            var $ribbon = el.find('a.ribbon');
            $ribbon.on('click', function() {
                scope.isVisible = true;
                scope.$digest();
            });
        }
    };

});

