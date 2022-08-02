import React from 'react';
import { Tag } from 'antd';
import { SyncOutlined, CheckCircleOutlined, CloseCircleOutlined } from '@ant-design/icons';
import { ACTION_STATUS } from '@/utils/consts';

const statusMap = {
	[ACTION_STATUS.WAIT]: <Tag icon={<SyncOutlined spin />} color="processing">待处理</Tag>,
	[ACTION_STATUS.AGREE]: <Tag icon={<CheckCircleOutlined />} color="success">已同意</Tag>,
	[ACTION_STATUS.DISAGREE]: <Tag icon={<CloseCircleOutlined />} color="error">不同意</Tag>,
}
export default ({ status }) => {
	return statusMap[status] || '';
}