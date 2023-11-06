import com.workflow.entities.MyGroupEntity;
import com.workflow.entities.MyUserEntity;
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
        String userId="alex";
        String groupId="mentor";
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = defaultProcessEngine.getIdentityService();
        UserQuery userQuery = identityService.createUserQuery().userId(userId);
        System.out.println(userQuery.count());
        GroupQuery groupQuery = identityService.createGroupQuery().groupId(groupId);
        System.out.println(groupQuery.count());

        UserEntityImpl user = new UserEntityImpl();
        user.setId(userId);
        GroupEntityImpl group = new GroupEntityImpl();
        group.setId(groupId);

        Thread.sleep(1000);
        identityService.saveUser(user);
        identityService.saveGroup(group);
        identityService.createMembership(userId,groupId);

        userQuery = identityService.createUserQuery().userId(userId);
        System.out.println(userQuery.count());
        groupQuery = identityService.createGroupQuery().groupId(groupId);
        System.out.println(groupQuery.count());

    }

    @Test
    public void testCast(){
        UserEntityImpl userEntity = new UserEntityImpl();
    }
}
