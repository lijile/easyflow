import React from 'react';
import { Modal, Form, Input, DatePicker, Select } from 'antd';
import { toDate } from '@/utils/basic';
import { LEAVE_TYPES } from '@/utils/consts';

const ApplyForm = ({ onOk, onCancel }) => {
	const [form] = Form.useForm();
	return (
		<Modal
			maskClosable={false}
			onCancel={onCancel}
			onOk={form.submit}
			visible={true}
			title="创建请假申请">
			<Form
				form={form}
				onFinish={(fieldsValue) => {
					const values = {
						...fieldsValue,
						startDate: toDate(fieldsValue.dateRange[0]),
						endDate: toDate(fieldsValue.dateRange[1]),
					};
					delete values.dateRange;
					onOk(values);
				}}>
				<Form.Item label="假期类型" name="type" rules={[{ required: true, message: '请选择假期类型' }]}>
					<Select>
						{
							LEAVE_TYPES.map(item => <Select.Option key={item}>{item}</Select.Option>)
						}
					</Select>
				</Form.Item>
				<Form.Item label="起止日期" name="dateRange" rules={[{ required: true, message: '请选择起止日期' }]}>
					<DatePicker.RangePicker style={{ width: '100%' }} />
				</Form.Item>
				<Form.Item label="请假理由" name="reason" rules={[{ required: true, message: '请填写请假理由' }]}>
					<Input.TextArea />
				</Form.Item>
			</Form>
		</Modal>
	)
}
export default ApplyForm;