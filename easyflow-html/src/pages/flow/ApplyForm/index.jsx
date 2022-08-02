import React from 'react';
import { Card, Empty } from 'antd';
import Leave from './Leave';

const EmptyCard = () => (
	<Card title="申请单">
		<Empty style={{ background: 'white' }} />
	</Card>
)

const ApplyForm = ({ instance, applyForm }) => {
	if (!applyForm) {
		return <EmptyCard />;
	}
	switch (instance?.moduleId) {
		case 'leave':
			return <Leave applyForm={applyForm} />
	}
	return <EmptyCard />;
}
export default ApplyForm;