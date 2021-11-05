package org.lecoder.easyflow.modules.core.node;

import org.lecoder.easyflow.modules.core.dto.NodeTaskDTO;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;

import java.util.Arrays;
import java.util.List;

/**
 * 总经理节点
 *
 * @author: lijile
 * @date: 2021/10/25 16:27
 * @version: 1.0
 */
public class GeneralManager extends BaseNode {
    @Override
    public List<NodeUserDTO> assign(NodeTaskDTO nodeTask) {
        NodeUserDTO generalManager = new NodeUserDTO();
        generalManager.setUsername("wangwu");
        generalManager.setFullname("王五");
        return Arrays.asList(generalManager);
    }
}
