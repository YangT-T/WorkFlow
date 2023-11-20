import com.workflow.utils.BpmnParser;
import com.workflow.utils.StringParser;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestParse {
    @Test
    public void testParseAll(){
        String deploymentId="2501";
        List<String> strings = BpmnParser.parseBpmnAllTask(deploymentId);
        System.out.println(strings);
    }

    @Test
    public void testStringParse(){
        String s="${a}${kk}";
        System.out.println(StringParser.parseStringAttr(s));
    }
    @Test
    public void testParseTask(){
        String deploymentId="2501";
        String taskId="";
        List<String> strings=BpmnParser.parseBpmnTaskFlow(deploymentId,taskId);
        System.out.println(strings);
    }

    @Test
    public void testBpmnTaskFlow(){
        String deploymentId="2501";
        String taskKey="usertask1";
        List<String> strings=BpmnParser.parseBpmnTaskFlow(deploymentId,taskKey);
        System.out.println(strings);
    }
}
