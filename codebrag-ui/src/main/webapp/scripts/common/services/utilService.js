"user strict";

angular.module("codebrag.common.services").factory('utilService', function ($resource) {

    var self = this;
    var dontBlockOnAjaxHeader = { "dontBlockPageOnAjax": "true" };
    var nonArrayGetWithoutBlockOnAjax = { method: "GET", isArray: false, headers: dontBlockOnAjaxHeader };

    self.utilResource = $resource('/rest/uptime', { }, {
        get: nonArrayGetWithoutBlockOnAjax
    });

    var utilService = {};

    utilService.loadUptime = function (successFunction) {
        return self.utilResource.get(successFunction);
    };

    return utilService;
});