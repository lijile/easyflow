import React from 'react';
import { useAppContext } from '@antv/xflow';
import './node2.less';

const Node2 = ({ data }) => {
  return (
    <div className="node2-container">
      <div>{data.info.nodeName}</div>
    </div>
  );
};
export default Node2;
