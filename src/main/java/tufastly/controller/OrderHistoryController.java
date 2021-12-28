package tufastly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tufastly.model.OrderInfo;
import tufastly.service.OrderHistoryService;

import java.util.List;

@Controller
public class OrderHistoryController {

    @Autowired
    OrderHistoryService service;

    @RequestMapping(value = "/my_orders/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderInfo> getOrderHistory(@PathVariable(value = "customerId") int customerId) {
        return service.getOrderHistory(customerId);
    }
}
