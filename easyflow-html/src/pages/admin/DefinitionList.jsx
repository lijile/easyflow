import React, { useEffect, useState, } from 'react';
import { Table, Button, Modal, } from 'antd';
import { connect, Link } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import { PlusOutlined, ExclamationCircleOutlined, } from '@ant-design/icons';
import DefinitionForm from './components/DefinitionForm';

const DefinitionList = ({ definitionList, dispatch }) => {

	const [createModal, setCreateModal] = useState();

	const columns = [
		{
			title: 'ID',
			dataIndex: 'id',
		},
		{
			title: '流程定义',
			render: (text, record) => <Link to={`/admin/definition/detail?definition_code=${record.definitionCode}`}>{record.definitionCode}</Link>,
		},
		{
			title: '流程名称',
			dataIndex: 'definitionName',
		},
		{
			title: '操作',
			render: (text, record) =>
				<a href="javascript:;"
					onClick={() => {
						Modal.confirm({
							title: '确认删除该流程?',
							icon: <ExclamationCircleOutlined />,
							onOk() {
								dispatch({
									type: 'admin/deleteDefinition',
									payload: {
										definitionCode: record.definitionCode,
									}
								})
							},
						});
					}}>删除</a>
		},
	];

	useEffect(() => {
		dispatch({
			type: 'admin/listDefinition',
			payload: {},
		});
	}, []);

	return (
		<PageContainer extra={
			[
				<Button type="primary" icon={<PlusOutlined />} key="create" onClick={() => setCreateModal(true)}>新建流程</Button>
			]}>
			<Table
				rowKey="definitionCode"
				columns={columns}
				dataSource={definitionList}
				pagination={false}
			/>
			{
				createModal &&
				<DefinitionForm
					onCancel={() => setCreateModal(false)}
					onOk={(form) => {
						dispatch({
							type: 'admin/saveDefinition',
							payload: form,
						});
						setCreateModal(false);
					}} />
			}
		</PageContainer>
	)
}
export default connect(({ admin }) => ({
	...admin,
}))(DefinitionList);