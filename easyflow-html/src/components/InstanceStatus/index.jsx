import React from 'react';
import { Badge } from 'antd';
import { INSTANCE_STATUS } from '@/utils/consts';

const statusMap = {
	[INSTANCE_STATUS.IN_PROGRESS]: <Badge status="processing" text="进行中" />,
	[INSTANCE_STATUS.FINISHED]: <Badge status="success" text="已完成" />,
	[INSTANCE_STATUS.TERMINATED]: <Badge status="error" text="已终止" />,
}
export default ({ status }) => {
	return statusMap[status] || '';
}