import com.workflow.entities.MyGroupEntity;
import com.workflow.entities.MyUserEntity;
import com.workflow.service.MyIdentityService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;

public class CreateGroup {
    @Test
    public void testIdentity() throws InterruptedException {
        MyIdentityService myIdentityService = new MyIdentityService();
        myIdentityService.createRelationship("yh","boss");
        myIdentityService.createRelationship("aa","manager");
        myIdentityService.createRelationship("bb","manager");
    }

    @Test
    public void nitian(){
        IdentityService identityService = ProcessEngines.getDefaultProcessEngine().getIdentityService();
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
//        user.setDisplayName("江南一点雨");
        user.setPassword("123");
        user.setFirstName("java");
        user.setLastName("boy");
        user.setEmail("javaboy@qq.com");
        user.setRevision(0);
        identityService.saveUser(user);
    }


    @Test
    public void testCast(){
        UserEntityImpl userEntity = new UserEntityImpl();
    }
}
