package tufastly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tufastly.model.RouteOptimizationRequestBody;
import tufastly.model.RouteOptimizationResponseBody;
import tufastly.service.RouteOptimizationService;

@Controller
public class RouteOptimizationController {

    @Autowired
    RouteOptimizationService service;

    @RequestMapping(value = "/route_optimization", method = RequestMethod.POST)
    @ResponseBody
    public RouteOptimizationResponseBody getOptimizedRoute(@RequestBody RouteOptimizationRequestBody requestBody, @RequestParam("orderInfoId") int orderInfoId) {
        return service.getRouteResponseBody(requestBody, orderInfoId);
    }
}
