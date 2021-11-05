package org.lecoder.easyflow.modules.core.node;

import org.lecoder.easyflow.modules.core.dto.NodeTaskDTO;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;

import java.util.Arrays;
import java.util.List;

/**
 * 部门经理节点
 *
 * @author: lijile
 * @date: 2021/10/25 16:27
 * @version: 1.0
 */
public class DeptManager extends BaseNode {
    @Override
    public List<NodeUserDTO> assign(NodeTaskDTO nodeTask) {
        NodeUserDTO deptManager = new NodeUserDTO();
        deptManager.setUsername("zhangsan");
        deptManager.setFullname("张三");
        return Arrays.asList(deptManager);
    }
}
