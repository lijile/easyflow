import React from 'react';
import './node1.less';

const Node1 = ({ data }) => {
  return (
    <div className="node1-container">
      <div>{data.info.text}</div>
    </div>
  );
};
export default Node1;
