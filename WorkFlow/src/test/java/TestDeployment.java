import com.workflow.entities.ProcessEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDeployment {
    @Test
    public void testDeploymentSingle(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name("deployment_2")
                .addClasspathResource("bpmn/test.bpmn20.xml")
                .addClasspathResource("bpmn-png/test1")
                .deploy();
        System.out.println(deploy.getName());
    }

    @Test
    public void testDeploymentMapFile(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name("入党申请")
                .addClasspathResource("bpmn/mapTest.bpmn20.xml")
                .deploy();
        System.out.println(deploy.getName());
    }

    @Test
    public void getAllDeployment(){
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        RuntimeService runtimeService1 = ProcessEngines.getDefaultProcessEngine().getRuntimeService();

        List<Deployment> list = repositoryService.createDeploymentQuery().list();
    }

    @Test
    public void testDeployThroughMap(){
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
    }
}
