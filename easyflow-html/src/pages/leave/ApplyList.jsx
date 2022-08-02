import React, { useEffect, useState } from 'react';
import { Card, Divider, Table } from 'antd';
import { connect, Link } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import QueryFilter from './components/QueryFilter';
import ApplyForm from './components/ApplyForm';
import InstanceStatus from '@/components/InstanceStatus';

const ApplyList = ({ condition, pagination, records, dispatch }) => {

  const [createModalVisible, setCreateModalVisible] = useState();

  const columns = [
    {
      title: '流程编号',
      render: (text, record) => <Link to={`/flow/detail?instance_code=${record.instanceCode}`}>{record.instanceCode}</Link>,
    },
    {
      title: '请假类型',
      dataIndex: 'type',
    },
    {
      title: '状态',
      render: (text, record) => <InstanceStatus status={record['status']} />
    },
    {
      title: '请假时间',
      render: (text, record) => `${record['startDate']} 至 ${record['endDate']}`,
    },
    {
      title: '请假天数',
      dataIndex: 'leaveDay',
    },
    {
      title: '操作',
      render: (text, record) => <Link to={`/flow/detail?instance_code=${record.instanceCode}`}>查看</Link>,
    },
  ];

  useEffect(() => {
    dispatch({
      type: 'leave/list',
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
            dispatch({ type: 'leave/updateState', payload: { condition: form } });
            dispatch({
              type: 'leave/list',
              payload: form,
            });
          }}
          onCreate={() => setCreateModalVisible(true)}
        />
        <Divider />
        <Table
          rowKey="instanceCode"
          pagination={pagination}
          columns={columns}
          dataSource={records}
          onChange={(p) =>
            dispatch({
              type: 'leave/list',
              payload: {
                pageNo: p.current,
                pageSize: p.pageSize,
                ...condition,
              },
            })
          }
        />
      </Card>
      {
        createModalVisible &&
        <ApplyForm
          onOk={(values) => {
            dispatch({
              type: 'leave/submit',
              payload: values,
            });
            setCreateModalVisible(false);
          }}
          onCancel={() => setCreateModalVisible(false)} />
      }
    </PageContainer>
  );
};
export default connect(({ leave }) => ({
  ...leave,
}))(ApplyList);
