import com.workflow.service.MyIdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;

public class TestInitialize {
    @Test
    public void initialize(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name("leave")
                .addClasspathResource("bpmn/leave.bpmn20.xml")
                .deploy();

        repositoryService.createDeployment().name("simple")
                .addClasspathResource("bpmn/simple.bpmn20.xml")
                .deploy();

        repositoryService.createDeployment().name("checkSheet")
                .addClasspathResource("bpmn/checkSheet.bpmn20.xml")
                .deploy();

        repositoryService.createDeployment().name("interview")
                .addClasspathResource("bpmn/interview.bpmn20.xml")
                .deploy();

        MyIdentityService myIdentityService = new MyIdentityService();
        myIdentityService.createRelationship("hr1","HR");
        myIdentityService.createRelationship("hr2","HR");
        myIdentityService.createRelationship("manager1","manager");
        myIdentityService.createRelationship("manager2","manager");
        myIdentityService.createRelationship("manager3","manager");
        myIdentityService.createRelationship("boss1","boss");
    }
}
