import * as api from '@/services/flow';

export default {
  namespace: 'flow',
  state: {
    instance: {},
    nodeList: [],
    actions: [],
    applyForm: {},

    condition: {},
    pagination: {
      current: 1,
      pageSize: 10,
      total: 0,
      showSizeChanger: true,
    },
    records: [],
  },
  effects: {
    *list({ payload }, { call, put, select }) {
      const res = yield call(api.queryList, payload);
      const { pagination } = yield select((state) => state.flow);
      const { data } = res;
      yield put({
        type: 'updateState',
        payload: {
          pagination: {
            ...pagination,
            current: data.current,
            total: data.total,
            pageSize: data.size,
            showTotal: (total) => `总共 ${total} 条`,
          },
          records: data.records,
        },
      });
    },
    *detail({ payload }, { call, put }) {
      const res = yield call(api.queryFlowDetail, payload);
      const { data } = res;
      yield put({
        type: 'updateState',
        payload: {
          ...data,
        },
      });
    },
    *approve({ payload }, { call, put }) {
      const { instanceCode, taskCode, form } = payload;
      const res = yield call(api.approve, taskCode, form);
      if (res) {
        yield put({
          type: 'detail',
          payload: {
            instanceCode,
          },
        });
      }
    },
    *changeNode({ payload }, { call, put }) {
      const { instanceCode, taskCode, username } = payload;
      const res = yield call(api.changeNode, { taskCode, username });
      if (res) {
        yield put({
          type: 'detail',
          payload: {
            instanceCode,
          },
        });
      }
    },
  },
  reducers: {
    updateState(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
