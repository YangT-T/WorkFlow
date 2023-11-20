import com.workflow.service.MyIdentityService;
import com.workflow.utils.BpmnParser;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullProcess {
    private String deploymentName="leave";
    private String bpmnPath="bpmn/leave.bpmn20.xml";
    private String jpgPath="bpmn-png/test1";

    private String deploymentId="1";
    @Test
    public void step1(){
        // 在后端部署流程
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name(deploymentName)
                .addClasspathResource(bpmnPath)
//                .addClasspathResource(jpgPath)
                .deploy();
        deploymentId=deploy.getId();
        System.out.println("Process "+deploy.getName()+" has been deployed");
    }

    @Test
    // 选择要启动的流程
    // 根据id判断必填字段
    public void step2(){
        // 选择Id
        String deploymentId;
//        http://localhost:8080/bpmnPage/initial?deploymentId=1
    }

    @Test
    // 以上一步用户输入的map启动流程
    public void step3(){
        String deploymentId;
//        http://localhost:8080/bpmn/initial/submit?deploymentId=1
    }


    @Test
    // 其他模块调用生成用户组关系模块
    public void step4(){
        MyIdentityService myIdentityService = new MyIdentityService();
//        myIdentityService.createRelationship("alex","students");
//        myIdentityService.createRelationship("bob","students");
//        myIdentityService.createRelationship("alice","students");
        myIdentityService.createRelationship("bb","manager");
        myIdentityService.createRelationship("aa","manager");
        myIdentityService.createRelationship("yh","boss");
    }


    @Test
    // 用户登录
    public void step5(){

    }

    @Test
    // 用户查看/操作流程
    public void step6(){
        // 超链接？
    }

    @Test
    // 用户完成流程前需要填写Flow的必填字段(自动根据bpmn解析)
    public void step7(){
//        http://localhost:8080/bpmnPage/taskFlow?taskId=2506
    }

    @Test
    // 传入必填字段完成请求
    public void step8(){
//        http://localhost:8080/bpmn/flow/submit?taskId=2506&assigneeId=jack
    }
}
