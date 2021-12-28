package tufastly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tufastly.model.PreviewRequestBody;
import tufastly.model.PreviewResponseBody;
import tufastly.service.PreviewService;

@Controller
public class PreviewController {

    @Autowired
    PreviewService service;

    @RequestMapping(value = "/preview/robot", method = RequestMethod.POST)
    @ResponseBody
    public PreviewResponseBody getRobotPreview(@RequestBody PreviewRequestBody requestBody) {
        return service.getRobotPreviewResponseBody(requestBody);
    }

    @RequestMapping(value = "/preview/drone", method = RequestMethod.POST)
    @ResponseBody
    public PreviewResponseBody getDronePreview(@RequestBody PreviewRequestBody requestBody) {
        return service.getDronePreviewResponseBody(requestBody);
    }
}
