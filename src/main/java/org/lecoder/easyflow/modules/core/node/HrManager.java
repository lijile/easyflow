package org.lecoder.easyflow.modules.core.node;

import org.lecoder.easyflow.modules.core.annotation.Node;
import org.lecoder.easyflow.modules.core.dto.NodeTaskDTO;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;

import java.util.Arrays;
import java.util.List;

/**
 * 人力经理节点
 *
 * @author: lijile
 * @date: 2021/10/25 16:27
 * @version: 1.0
 */
@Node("人力经理")
public class HrManager implements BaseNode {
    @Override
    public List<NodeUserDTO> assign(NodeTaskDTO nodeTask) {
        NodeUserDTO hrManager = new NodeUserDTO();
        hrManager.setUsername("lisi");
        hrManager.setFullname("李四");
        return Arrays.asList(hrManager);
    }
}
