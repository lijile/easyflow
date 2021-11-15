-- 初始化测试用户，初始密码easyflow
insert into sys_user(username, password, fullname, phone, email) values('xialuo', '9ec8f3a4a450276f4c4091f50d02102b', '夏洛', '13800138000', 'xialuo@qq.com');
insert into sys_user(username, password, fullname, phone, email) values('zhangsan', '9ec8f3a4a450276f4c4091f50d02102b', '张三', '13800138000', 'zhangsan@qq.com');
insert into sys_user(username, password, fullname, phone, email) values('lisi', '9ec8f3a4a450276f4c4091f50d02102b', '李四', '13800138000', 'lisi@qq.com');
insert into sys_user(username, password, fullname, phone, email) values('wangwu', '9ec8f3a4a450276f4c4091f50d02102b', '王五', '13800138000', 'wangwu@qq.com');

-- 流程定义
insert into flow_definition(definition_code, definition_name) values('leave_common', '常规请假流程');

-- 流程定义节点（分支1）
insert into flow_definition_node(definition_code, parent_code, node_code, node_name, condition_script, rel_class, priority) values('leave_common', null, 'dept_manager_1', '部门经理', '["事假", "年假"].indexOf(type)>=0', 'org.lecoder.easyflow.modules.core.node.DeptManager', 10);
insert into flow_definition_node(definition_code, parent_code, node_code, node_name, condition_script, rel_class, priority) values('leave_common', 'dept_manager_1', 'hr_manager_1', '人事部经理', 'days >= 3', 'org.lecoder.easyflow.modules.core.node.HrManager', 10);
insert into flow_definition_node(definition_code, parent_code, node_code, node_name, condition_script, rel_class, priority) values('leave_common', 'hr_manager_1', 'general_manager_1', '总经理', 'days >= 7', 'org.lecoder.easyflow.modules.core.node.GeneralManager', 10);

-- 流程定义节点（分支2）
insert into flow_definition_node(definition_code, parent_code, node_code, node_name, condition_script, rel_class, priority) values('leave_common', null, 'dept_manager_2', '部门经理', null, 'org.lecoder.easyflow.modules.core.node.DeptManager', 0);
insert into flow_definition_node(definition_code, parent_code, node_code, node_name, condition_script, rel_class, priority) values('leave_common', 'dept_manager_2', 'hr_manager_2', '人事部经理', 'days >= 5', 'org.lecoder.easyflow.modules.core.node.HrManager', 0);