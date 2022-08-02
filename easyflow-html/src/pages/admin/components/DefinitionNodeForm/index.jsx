import React, { useEffect, useState, useMemo, useRef } from 'react';
import { Modal, Input, Form, Select, Spin } from 'antd';
import { request } from 'umi';
import debounce from 'lodash/debounce';
import { SERVER_PREFIX } from '@/utils/consts';

export default ({ definitionNode, onCancel, onOk }) => {
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
			request('/core/admin/search-rel-class', {
				prefix: SERVER_PREFIX,
				credentials: 'include',
				params: {
					keyword: value,
				}
			}).then(res => {
				if (fetchId !== fetchRef.current) {
					return;
				}

				setOptions(res.data.map(item => ({ value: item.name, label: `${item.simpleName} - ${item.note}` })));
				setFetching(false);
			})
		};

		return debounce(loadOptions, 1000);
	}, []);

	useEffect(() => {
		debounceFetcher(definitionNode.relClass);
	}, [definitionNode, debounceFetcher]);

	return (
		<Modal
			maskClosable={false}
			onCancel={onCancel}
			onOk={form.submit}
			visible={true}
			title="流程节点信息">
			<Form
				form={form}
				initialValues={{
					...definitionNode,
					name: definitionNode.nodeName,
				}}
				onFinish={(values) => {
					onOk({
						...values,
						nodeName: values.name,
					});
				}}
				labelCol={{ span: 4 }}
				wrapperCol={{ span: 20 }}
			>
				<Form.Item label="节点名称" name="name" rules={[{ required: true, message: '请填写节点名称' }]}>
					<Input />
				</Form.Item>
				<Form.Item label="条件脚本" name="conditionScript">
					<Input.TextArea placeholder="如：days > 3" />
				</Form.Item>
				<Form.Item label="关联类" name="relClass" rules={[{ required: true, message: '请选择关联类' }]}>
					<Select
						showSearch={true}
						filterOption={false}
						onSearch={debounceFetcher}
						notFoundContent={fetching ? <Spin size="small" /> : null}
						options={options}
					/>
				</Form.Item>
				<Form.Item label="优先级" name="priority">
					<Input />
				</Form.Item>
			</Form>
		</Modal>
	)
}