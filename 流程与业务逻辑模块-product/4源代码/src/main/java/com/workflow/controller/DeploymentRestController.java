package com.workflow.controller;

import com.workflow.entities.MyDeploymentEntity;
import com.workflow.service.MyDeploymentService;
import com.workflow.service.MyProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deployment")
public class DeploymentRestController {
    @Autowired
    MyDeploymentService deploymentService;
    @GetMapping("/getAll")
    public List<MyDeploymentEntity> getAllDeployment(){
        return deploymentService.getAllDeploymentService();
    }
    @GetMapping("/createInstance")
    public void createInstance(@RequestParam(name = "id")String id){
        MyProcessService myProcessService = new MyProcessService();
        myProcessService.startInstance(id);
        System.out.println("start process instance, id = "+id);
    }
    @PostMapping("/createInstanceWithMap")
    public void createInstance(@RequestParam(name = "id")String id,@RequestBody Map<String, Object> maps){
        System.out.println(maps);
        System.out.println(id);
        maps=(Map<String, Object>)maps.get("params");
        List<String> keys= (List<String>) maps.get("keys");
        List<String> values= (List<String>) maps.get("values");
        Map<String,Object> properties=new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            properties.put(keys.get(i), values.get(i));
        }
        MyProcessService myProcessService = new MyProcessService();
        myProcessService.startInstancePlus(id,properties);
    }

    @RequestMapping(method = RequestMethod.GET,value="/deploymentShowPage",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] deploymentShowPage(@RequestParam("id") String deploymentId) throws IOException {
        String rootPath= ResourceUtils.getURL("classpath:").getPath();
        String filePath=new MyDeploymentService().getJPGE(deploymentId)+"/";
        File file = new File(rootPath+filePath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

}
