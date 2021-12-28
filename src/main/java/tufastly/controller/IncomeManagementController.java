package tufastly.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tufastly.model.admin.AdminOrderInfo;
import tufastly.service.admin.IncomeManagementService;

@Controller
@CrossOrigin
public class IncomeManagementController {

    @Autowired
    private IncomeManagementService incomeManagementService;

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String getGeneral() {
        ObjectMapper mapper = new ObjectMapper();
        AdminOrderInfo object = incomeManagementService.getGeneral();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);// Java object to JSON string, default compact-print
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
