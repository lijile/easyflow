package org.lecoder.easyflow.modules.core.node;

import org.lecoder.easyflow.modules.core.dto.NodeTaskDTO;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;

import java.util.List;

/**
 * 基础节点，所有节点定义类必须继承
 *
 * @author: lijile
 * @date: 2021/10/25 15:21
 * @version: 1.0
 */
public abstract class BaseNode {
    public abstract List<NodeUserDTO> assign(NodeTaskDTO nodeTask);
}
