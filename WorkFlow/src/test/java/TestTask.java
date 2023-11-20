import com.workflow.entities.MyTaskEntity;
import com.workflow.service.MyTaskService;
import com.workflow.utils.BpmnParser;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTask {
    @Test
    public void testComplete(){
        MyTaskService myTaskService = new MyTaskService();
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
//        taskService.
        String taskId="22506";
//        taskService.claim(taskId,"aa");
        Map<String,Object> map=new HashMap<>();
//        map.put("flag","false");
        taskService.complete(taskId,map);
//        myTaskService.completeTask("7506","a");
    }

    @Test
    public void testGetTask(){
        MyTaskService myTaskService = new MyTaskService();
        List<MyTaskEntity> bob = myTaskService.getAll("a");
        System.out.println(bob.size());
    }

    @Test
    public void testBpmnParseTaskFlow(){
        BpmnParser.parseBpmnTaskFlow("1","2511");
    }


}
