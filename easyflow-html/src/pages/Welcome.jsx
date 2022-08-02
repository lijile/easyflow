import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Card } from 'antd';
import { useIntl } from 'umi';

export default () => {
  const intl = useIntl();
  return (
    <PageContainer>
      <Card>
        <h1>
          {intl.formatMessage({
            id: 'pages.welcome.alertMessage',
            defaultMessage: '欢迎光临！！！',
          })
          }
        </h1>
      </Card>
    </PageContainer>
  );
};
