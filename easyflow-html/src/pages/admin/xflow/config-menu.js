import { createCtxMenuConfig } from '@antv/xflow';
import { MenuItemType } from '@antv/xflow';
import { IconStore, XFlowNodeCommands, XFlowEdgeCommands } from '@antv/xflow';
import { DeleteOutlined, EditOutlined, PlusOutlined } from '@ant-design/icons';

/** 注册菜单依赖的icon */
IconStore.set('DeleteOutlined', DeleteOutlined);
IconStore.set('EditOutlined', EditOutlined);
IconStore.set('PlusOutlined', PlusOutlined);

export const useMenuConfig = ({ onCreate, onUpdate, onDelete, onRoot }) => {
  const CREATE_NODE = {
    id: XFlowNodeCommands.ADD_NODE.id,
    label: '新建节点',
    iconName: 'PlusOutlined',
    onClick: async ({ target, commandService }) => {
      if (onCreate) {
        onCreate(target);
      }
    },
  };

  const UPDATE_NODE = {
    id: XFlowNodeCommands.UPDATE_NODE.id,
    label: '修改节点',
    iconName: 'EditOutlined',
    onClick: async ({ target, commandService, aa }) => {
      if (onUpdate) {
        onUpdate(target);
      }
    },
  };

  const DELETE_NODE = {
    id: XFlowNodeCommands.DEL_NODE.id,
    label: '删除节点',
    iconName: 'DeleteOutlined',
    onClick: async ({ target, commandService }) => {
      commandService.executeCommand(XFlowNodeCommands.DEL_NODE.id, {
        nodeConfig: { id: target.data.id },
      });
      if (onDelete) {
        onDelete(target);
      }
    },
  };

  const ROOT_NODE = {
    id: XFlowNodeCommands.UPDATE_NODE.id,
    label: '修改名称',
    iconName: 'EditOutlined',
    onClick: async ({ target, commandService }) => {
      if (onRoot) {
        onRoot(target);
      }
    },
  };

  const EMPTY_MENU = {
    id: 'EMPTY_MENU_ITEM',
    label: '暂无可用',
    isEnabled: false,
  };

  return createCtxMenuConfig((config) => {
    config.setMenuModelService(async (data, model, modelService, toDispose) => {
      const { type } = data;
      const id = data.data?.cell?.id;
      switch (type) {
        case 'node':
          model.setValue({
            id: 'root',
            type: MenuItemType.Root,
            submenu:
              id === 'root1' ? [ROOT_NODE, CREATE_NODE] : [CREATE_NODE, UPDATE_NODE, DELETE_NODE],
          });
          break;
        default:
          model.setValue({
            id: 'root',
            type: MenuItemType.Root,
            submenu: [EMPTY_MENU],
          });
          break;
      }
    });
  })();
};
