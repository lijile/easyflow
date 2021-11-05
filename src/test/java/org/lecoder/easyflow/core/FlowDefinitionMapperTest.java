package org.lecoder.easyflow.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 流程定义服务测试
 *
 * @author: lijile
 * @date: 2021/10/25 10:27
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FlowDefinitionMapperTest {

    @Test
    public void test() {
        FlowDefinition flowDefinition = flowDefinitionMapper.selectById(1);
        Assert.assertNotNull(flowDefinition);
    }

    @Autowired private FlowDefinitionMapper flowDefinitionMapper;

}
