package org.lecoder.easyflow.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.service.IFlowApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程接口测试
 *
 * @author: lijile
 * @date: 2021/10/25 16:31
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FlowApiServiceTest {

    @Test
    public void start() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("type", "事假");
        variables.put("days", new BigDecimal(3));
        String instanceCode = flowApiService.start(FlowModuleEnum.LEAVE, "leave_common", variables);
        Assert.assertNotNull(instanceCode);
    }

    @Test
    public void agree() {
        flowApiService.agree("n5b9ow0g0kir1raa1j1j", "");
    }

    @Test
    public void preview() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("type", "事假");
        variables.put("days", new BigDecimal(7));
        List<FlowInstanceNode> instanceNodeList = flowApiService.preview("leave_common", variables);
        Assert.assertTrue(instanceNodeList.size() > 0);
        for (int i = 0; i < instanceNodeList.size(); i++) {
            FlowInstanceNode instanceNode = instanceNodeList.get(i);
            System.out.print("======");
            for (int j = 0; j < i; j++) {
                System.out.print("===");
            }
            System.out.print(instanceNode.getNodeId() + ":");
            System.out.print(instanceNode.getNodeName() + ":");
            System.out.print(instanceNode.getFullname());
            System.out.println();
        }
    }

    @Autowired private IFlowApiService flowApiService;

}
