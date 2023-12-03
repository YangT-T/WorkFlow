package com.workflow.controller.modelController;

import com.workflow.service.MyDeploymentService;
import org.junit.jupiter.api.parallel.Resources;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class DeploymentController {
    @GetMapping("/deploymentPage")
    public String deploymentPage(){
        return "deployment";
    }

//    @RequestMapping(method = RequestMethod.GET,value="/deploymentShowPage",produces = MediaType.IMAGE_PNG_VALUE)
//    @ResponseBody
//    public byte[] deploymentShowPage(@RequestParam("id") String deploymentId) throws IOException {
//        String rootPath= ResourceUtils.getURL("classpath:").getPath();
//        String filePath=new MyDeploymentService().getJPGE(deploymentId)+"/";
//        File file = new File(rootPath+filePath);
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }
//    @GetMapping("/deploy/fillPropertyPage")
//    public String
}
