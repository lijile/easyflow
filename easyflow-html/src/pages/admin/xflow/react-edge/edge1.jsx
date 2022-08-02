import React from 'react';
import { Tooltip } from 'antd';
import './edge1.less';

const Edge1 = ({ data }) => {
  const { conditionScript } = data.info;
  return (
    <div className="edge1-container">
      <Tooltip title={conditionScript}>
        <div className="edge1-content">{conditionScript}</div>
      </Tooltip>
    </div>
  );
};
export default Edge1;
