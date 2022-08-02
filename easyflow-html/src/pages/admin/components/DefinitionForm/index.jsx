import React from 'react';
import { Modal, Input, Form, } from 'antd';

export default ({ definition, onCancel, onOk }) => {
	const [form] = Form.useForm();
	return (
		<Modal
			maskClosable={false}
			onCancel={onCancel}
			onOk={form.submit}
			visible={true}
			title="流程节点信息">
			<Form
				form={form}
				initialValues={
					definition
				}
				onFinish={(values) => {
					onOk(values);
				}}
				labelCol={{ span: 4 }}
				wrapperCol={{ span: 20 }}
			>
				<Form.Item label="流程名称" name="definitionName" rules={[{ required: true, message: '请填写流程名称' }]}>
					<Input />
				</Form.Item>
			</Form>
		</Modal>
	)
}