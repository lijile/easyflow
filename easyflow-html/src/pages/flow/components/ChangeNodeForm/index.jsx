import React, { useState, useMemo, useRef } from 'react';
import { Modal, Form, Select, Spin } from 'antd';
import { request } from 'umi';
import debounce from 'lodash/debounce';
import { SERVER_PREFIX } from '@/utils/consts';

export default ({ taskCode, nodeList, onCancel, onOk }) => {
	const [form] = Form.useForm();

	const [fetching, setFetching] = useState(false);
	const [options, setOptions] = useState([]);
	const fetchRef = useRef(0);
	const debounceFetcher = useMemo(() => {
		const loadOptions = (value) => {
			fetchRef.current += 1;
			const fetchId = fetchRef.current;
			setOptions([]);
			setFetching(true);
			request('/sys/user/search', {
				prefix: SERVER_PREFIX,
				credentials: 'include',
				params: {
					keyword: value,
				}
			}).then(res => {
				if (fetchId !== fetchRef.current) {
					return;
				}

				setOptions(res.data.map(user => ({ value: user.username, label: user.fullname })));
				setFetching(false);
			})
		};

		return debounce(loadOptions, 1000);
	}, []);

	return (
		<Modal
			maskClosable={false}
			onCancel={onCancel}
			onOk={form.submit}
			visible={true}
			title="改签">
			<Form
				form={form}
				initialValues={{
					taskCode,
				}}
				onFinish={(values) => {
					onOk(values);
				}}>
				<Form.Item label="目标节点" name="taskCode" rules={[{ required: true, message: '请选择目标节点' }]}>
					<Select>
						{
							nodeList.filter(node => node.status === 0).map(node => <Select.Option key={node.taskCode} value={node.taskCode}>{node.nodeName} - {node.fullname}</Select.Option>)
						}
					</Select>
				</Form.Item>
				<Form.Item label="改派对象" name="username" rules={[{ required: true, message: '请选择改派对象' }]}>
					<Select
						showSearch={true}
						filterOption={false}
						onSearch={debounceFetcher}
						notFoundContent={fetching ? <Spin size="small" /> : null}
						options={options}
					/>
				</Form.Item>
			</Form>
		</Modal>
	)
}