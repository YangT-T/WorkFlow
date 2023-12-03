import com.workflow.entities.MyProcessEntity;
import com.workflow.service.MyProcessService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestProcess {
    @Test
    public void testProcess(){
        MyProcessService myProcessService = new MyProcessService();
        List<MyProcessEntity> allProcessInfo = myProcessService.getAllProcessInfo();
        System.out.println(allProcessInfo);
    }
}
