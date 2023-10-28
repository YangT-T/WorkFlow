import com.workflow.service.MyTaskService;
import org.junit.jupiter.api.Test;

public class TestTask {
    @Test
    public void testComplete(){
        new MyTaskService().completeTask("2505");
    }

}
