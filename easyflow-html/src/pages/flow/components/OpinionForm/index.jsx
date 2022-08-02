import React from 'react';
import { Modal, Form, Input, Select } from 'antd';
import { ACTION_STATUS } from '@/utils/consts';

export default ({ opinion, onCancel, onOk }) => {
	const [form] = Form.useForm();
	return (
		<Modal
			maskClosable={false}
			onCancel={onCancel}
			onOk={form.submit}
			visible={true}
			title="批阅">
			<Form
				form={form}
				initialValues={{
					opinion: opinion,
				}}
				onFinish={(values) => {
					onOk(values);
				}}>
				<Form.Item label="意见" name="opinion">
					<Select>
						<Select.Option value={ACTION_STATUS.AGREE}>同意</Select.Option>
						<Select.Option value={ACTION_STATUS.DISAGREE}>不同意</Select.Option>
					</Select>
				</Form.Item>
				<Form.Item label="说明" name="note">
					<Input.TextArea />
				</Form.Item>
			</Form>
		</Modal>
	)
}