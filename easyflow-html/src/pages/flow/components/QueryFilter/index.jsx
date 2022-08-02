import React from 'react';
import { Form, Select, DatePicker, Button } from 'antd';
import { SearchOutlined, PlusOutlined } from '@ant-design/icons';
import { formatDate } from '@/utils/basic';
import { MODULE_LIST } from '@/utils/consts';
import styles from './index.less';

const QueryFilter = ({ initialValues, onSearch }) => {
  const [form] = Form.useForm();
  return (
    <Form
      layout="inline"
      form={form}
      initialValues={initialValues}
      onFinish={() => {
        const values = form.getFieldsValue();
        const { dateRange } = values;
        if (dateRange) {
          values['startDate'] = formatDate(dateRange[0]);
          values['endDate'] = formatDate(dateRange[1]);
          delete values.dateRange;
        }
        onSearch(values);
      }}
    >
      <Form.Item label="模块" name="moduleId">
        <Select className={styles.formitem}>
          {
            MODULE_LIST.map(item => <Select.Option key={item.moduleId}>{item.moduleName}</Select.Option>)
          }
        </Select>
      </Form.Item>
      <Form.Item label="日期" name="dateRange">
        <DatePicker.RangePicker />
      </Form.Item>
      <Form.Item>
        <Button
          icon={<SearchOutlined />}
          type="primary"
          htmlType="submit"
          style={{ marginRight: 20 }}
        >
          搜索
        </Button>
      </Form.Item>
    </Form>
  );
};
export default QueryFilter;
