import React from 'react';
import { Card, Descriptions } from 'antd';

export default ({ applyForm }) => {
	return (
		<Card title="申请单">
			<Descriptions>
				<Descriptions.Item label="类型">{applyForm.type}</Descriptions.Item>
				<Descriptions.Item label="日期">{applyForm.startDate} ~ {applyForm.endDate}</Descriptions.Item>
				<Descriptions.Item label="天数">{applyForm.leaveDay} 天</Descriptions.Item>
				<Descriptions.Item label="理由">{applyForm.reason}</Descriptions.Item>
			</Descriptions>
		</Card>
	)
}