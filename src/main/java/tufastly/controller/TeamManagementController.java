package tufastly.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tufastly.model.admin.AdminTeamInfo;
import tufastly.model.admin.AdminTeamPosition;
import tufastly.service.admin.TeamManagementService;

@Controller
@CrossOrigin
public class TeamManagementController {

    @Autowired
    private TeamManagementService teamManagementService;


    @RequestMapping(value = "/admin/teams", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String getGeneral() {
        ObjectMapper mapper = new ObjectMapper();
        AdminTeamInfo object = teamManagementService.getGeneral();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @RequestMapping(value = "/admin/positiondata", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String getPosition() {
        ObjectMapper mapper = new ObjectMapper();
        AdminTeamPosition object = teamManagementService.getTeamPosition();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
