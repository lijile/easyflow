import React, { useEffect, useState } from 'react';
import { connect, useModel } from 'umi';
import { Descriptions, Card, Steps, Skeleton, Button } from 'antd';
import { ClockCircleOutlined, CheckCircleOutlined, CloseCircleOutlined, FormOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import InstanceStatus from '@/components/InstanceStatus';
import ApplyForm from './ApplyForm';
import OpinionForm from './components/OpinionForm';
import ChangeNodeForm from './components/ChangeNodeForm';
import { ACTION_STATUS } from '@/utils/consts';

const stepProps = {
	[ACTION_STATUS.WAIT]: { status: 'process', icon: <ClockCircleOutlined /> },
	[ACTION_STATUS.AGREE]: { status: 'finish' },
	[ACTION_STATUS.DISAGREE]: { status: 'error' }
}

const Detail = ({ instance, nodeList, actions, applyForm, dispatch, location }) => {
	const { initialState = {}, setInitialState } = useModel('@@initialState');
	const instanceCode = location?.query?.instance_code;
	const [opinion, setOpinion] = useState();
	const [changeNode, setChangeNode] = useState();

	const getWaitTaskCode = () => {
		const { currentUser = {} } = initialState;
		const node = nodeList.find(node => node.actionStatus === ACTION_STATUS.WAIT && node.username === currentUser.username);
		if (node) {
			return node.taskCode;
		}
	}

	useEffect(() => {
		dispatch({
			type: 'flow/detail',
			payload: {
				instanceCode,
			}
		})
	}, []);

	return instance ?
		(
			<PageContainer
				header={{
					title: instance.moduleName,
					extra: actions.map(action => {
						switch (action) {
							case 'DISAGREE':
								return <Button key="disagree" onClick={() => setOpinion(ACTION_STATUS.DISAGREE)} icon={<CloseCircleOutlined />} danger>不同意</Button>
							case 'AGREE':
								return <Button key="agree" onClick={() => setOpinion(ACTION_STATUS.AGREE)} type="primary" icon={<CheckCircleOutlined />}>同意</Button>
							case 'CHANGE_NODE':
								return <Button key="change_node" onClick={() => setChangeNode(true)} icon={<FormOutlined />}>改签</Button>
						}
					}),
				}}
				content={
					<Descriptions>
						<Descriptions.Item label="申请人">{instance.fullname}</Descriptions.Item>
						<Descriptions.Item label="状态"><InstanceStatus status={instance.status} /></Descriptions.Item>
					</Descriptions>
				}
			>
				<ApplyForm instance={instance} applyForm={applyForm} />
				<Card title="进度" style={{ marginTop: 20 }}>
					<Steps direction="vertical">
						{
							nodeList.map(node => (
								<Steps.Step key={node.taskCode}
									title={node.nodeName}
									subTitle={node.fullname}
									description={
										node.note &&
										<p>Note：{node.note}</p>
									}
									{...(stepProps[node.actionStatus] || {})} />
							))
						}
					</Steps>
				</Card>
				{
					opinion &&
					<OpinionForm
						opinion={opinion}
						onCancel={() => setOpinion()}
						onOk={(values) => {
							dispatch({
								type: 'flow/approve',
								payload: {
									instanceCode,
									taskCode: getWaitTaskCode(),
									form: values,
								}
							})
							setOpinion();
						}} />
				}
				{
					changeNode &&
					<ChangeNodeForm
						taskCode={getWaitTaskCode()}
						nodeList={nodeList}
						onCancel={() => setChangeNode(false)}
						onOk={(values) => {
							dispatch({
								type: 'flow/changeNode',
								payload: {
									instanceCode,
									...values,
								}
							})
							setChangeNode(false);
						}} />
				}
			</PageContainer>
		) : <Skeleton />
}
export default connect(({ flow }) => ({
	...flow,
}))(Detail);