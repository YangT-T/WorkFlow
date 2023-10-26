import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;

public class TestDeployment {
    @Test
    public void testDeploymentSingle(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name("普通审批")
                .addClasspathResource("bpmn/test.bpmn20.xml")
                .addClasspathResource("bpmn-png/test1")
                .deploy();
        System.out.println(deploy.getName());
    }
}
