import React, { useState, useMemo, } from 'react';
import { connect } from 'umi';
/** 图核心组件 & 类型定义 */
import { XFlow, XFlowCanvas, XFlowNodeCommands, XFlowEdgeCommands, } from '@antv/xflow';
/** 图的各种扩展交互组件 */
import {
  CanvasMiniMap,
  CanvasScaleToolbar,
  CanvasSnapline,
  CanvasContextMenu,
} from '@antv/xflow';
/** 图的配置项 */
import { useGraphConfig } from './xflow/config-graph';
import { useMenuConfig } from './xflow/config-menu';

import DefinitionNodeForm from './components/DefinitionNodeForm';
import DefinitionForm from './components/DefinitionForm';
import './index.less';
import '@antv/xflow/dist/index.css';

import {
  getDefinitionDetail,
  saveDefinitionNode,
  updateDefinitionNode,
  updateDefinition,
  deleteDefinitionNode,
} from '@/services/admin';

function buildEdge(node) {
  const source = node.parentCode ? node.parentCode : 'root1';
  const { conditionScript } = node;
  return {
    id: 'edge_id_' + node.id,
    source,
    target: node.nodeCode,
    renderKey: conditionScript && 'EDGE1',
    edgeContentWidth: 100,
    edgeContentHeigt: 30,
    info: node,
  };
}

function buildNode(node, data = {}, isCreate = false) {
  const config = {
    ...data,
    id: node.nodeCode,
    width: data.width || 150,
    height: data.height || 40,
    renderKey: 'NODE2',
    info: node,
  };
  if (isCreate && data.y) {
    config.y = data.y + 150;
  }
  return config;
}

function buildRoot(definitionName, data = {}) {
  return {
    ...data,
    id: 'root1',
    width: data.width || 150,
    height: data.height || 40,
    renderKey: 'NODE1',
    info: { text: definitionName },
  }
}

function DefinitionDetail({ location }) {
  /** XFlow应用实例 */
  const appMemo = useMemo(
    () => ({
      app: null,
    }),
    [],
  );

  const definitionCode = location?.query?.definition_code;
  const [nodeForm, setNodeForm] = useState();
  const [definitionForm, setDefinitionForm] = useState();
  /** 画布配置 */
  const graphConfig = useGraphConfig();

  /** 画布渲染数据 */
  const [graphData, setGraphData] = useState(undefined);

  const config = {
    onCreate: async (target) => {
      setNodeForm({
        form: {
          parentCode: target.data.info.nodeCode,
        },
        data: target.data,
      });
    },
    onUpdate: (target) => {
      setNodeForm({
        form: target.data.info,
        data: target.data,
      });
    },
    onDelete: async (target) => {
      const res = await deleteDefinitionNode({ definitionCode, nodeCode: target.data.info.nodeCode });
      const { data } = res;
      if (data && data.length) {
        data.forEach(async node => {
          await appMemo.app.executeCommand(XFlowEdgeCommands.ADD_EDGE.id, {
            edgeConfig: buildEdge(node),
          });
        })
      }
    },
    onRoot: (target) => {
      setDefinitionForm({
        form: { definitionCode, definitionName: target.data.info.text },
        data: target.data,
      });
    },
  };
  /** 右键菜单配置 */
  const menuConfig = useMenuConfig(config);

  /** XFlow初始化完成的回调 */
  const onLoad = async (app) => {
    appMemo.app = app;

    const res = await getDefinitionDetail({ definitionCode });
    const { data } = res;
    const { definition, nodeList } = data;

    const nodes = nodeList.map(buildNode);
    nodes.unshift(buildRoot(definition?.definitionName));
    const edges = nodeList.map(buildEdge);

    const newGraphData = { nodes, edges };
    setGraphData(newGraphData);
  }

  return (
    <>
      <XFlow
        className="xflow-user-container"
        graphData={graphData}
        onLoad={onLoad}
        graphLayout={{
          layoutType: 'dagre',
          layoutOptions: {
            type: 'dagre',
            rankdir: 'TB',
            nodesep: 60,
            ranksep: 40,
          },
        }}
        isAutoCenter={true}
      >
        <XFlowCanvas config={graphConfig}>
          <CanvasScaleToolbar position={{ top: 12, left: 12 }} />
          <CanvasMiniMap
            miniMapClz="xflow-custom-minimap"
            nodeFillColor="#ccc"
            minimapOptions={{
              width: 200,
              height: 120,
            }}
            position={{ top: 12, right: 12 }}
          />
          <CanvasSnapline color="#1890ff" />
          <CanvasContextMenu config={menuConfig} />
        </XFlowCanvas>
      </XFlow>
      {
        nodeForm &&
        <DefinitionNodeForm
          definitionNode={nodeForm.form}
          onCancel={() => setNodeForm()}
          onOk={async (form) => {
            if (nodeForm.form.nodeCode) {
              const res = await updateDefinitionNode({ ...nodeForm.form, ...form, definitionCode, });
              await appMemo.app.executeCommand(XFlowNodeCommands.UPDATE_NODE.id, {
                nodeConfig: buildNode(res?.data, nodeForm.data),
              });
              await appMemo.app.executeCommand(XFlowEdgeCommands.UPDATE_EDGE.id, {
                edgeConfig: buildEdge(res?.data),
              });
            } else {
              const res = await saveDefinitionNode({ ...nodeForm.form, ...form, definitionCode, });
              await appMemo.app.executeCommand(XFlowNodeCommands.ADD_NODE.id, {
                nodeConfig: buildNode(res?.data, nodeForm.data, true),
              });
              await appMemo.app.executeCommand(XFlowEdgeCommands.ADD_EDGE.id, {
                edgeConfig: buildEdge(res?.data),
              });
            }
            setNodeForm();
          }} />
      }
      {
        definitionForm &&
        <DefinitionForm
          definition={definitionForm.form}
          onCancel={() => setDefinitionForm()}
          onOk={async (form) => {
            const res = await updateDefinition({ ...form, definitionCode, });
            await appMemo.app.executeCommand(XFlowNodeCommands.UPDATE_NODE.id, {
              nodeConfig: buildRoot(form.definitionName, definitionForm.data),
            });
            setDefinitionForm();
          }} />
      }
    </>
  );
};

export default connect(({ admin }) => ({
  ...admin,
}))(DefinitionDetail);
