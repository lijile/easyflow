import React, { useEffect } from 'react';
import { Card, Divider, Table } from 'antd';
import { connect, Link } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import QueryFilter from './components/QueryFilter';
import InstanceStatus from '@/components/InstanceStatus';
import NodeStatus from '@/components/NodeStatus';
import { formatDate } from '@/utils/basic';

const MyTask = ({ condition, pagination, records, dispatch }) => {

	const columns = [
		{
			title: '流程编号',
			render: (text, record) => <Link to={`/flow/detail?instance_code=${record.instanceCode}`}>{record.instanceCode}</Link>,
		},
		{
			title: '模块',
			dataIndex: 'moduleName',
		},
		{
			title: '申请人',
			dataIndex: 'fullname',
		},
		{
			title: '申请状态',
			render: (text, record) => <InstanceStatus status={record['status']} />
		},
		{
			title: '审批状态',
			render: (text, record) => <NodeStatus status={record['actionStatus']} />
		},
		{
			title: '申请时间',
			render: (text, record) => formatDate(record['applyTime']),
		},
		{
			title: '操作',
			render: (text, record) => <Link to={`/flow/detail?instance_code=${record.instanceCode}`}>查看</Link>,
		},
	];

	useEffect(() => {
		dispatch({
			type: 'flow/list',
			payload: {
				pageNo: 1,
				pageSize: 10,
				...condition,
			},
		});
	}, []);

	return (
		<PageContainer>
			<Card>
				<QueryFilter
					initialValues={condition}
					onSearch={(values) => {
						const form = values;
						dispatch({ type: 'flow/updateState', payload: { condition: form } });
						dispatch({
							type: 'flow/list',
							payload: form,
						});
					}}
				/>
				<Divider />
				<Table
					rowKey="instanceCode"
					pagination={pagination}
					columns={columns}
					dataSource={records}
					onChange={(p) =>
						dispatch({
							type: 'flow/list',
							payload: {
								pageNo: p.current,
								pageSize: p.pageSize,
								...condition,
							},
						})
					}
				/>
			</Card>
		</PageContainer>
	)
}
export default connect(({ flow }) => ({
	...flow,
}))(MyTask);