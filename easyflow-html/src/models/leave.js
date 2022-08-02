import * as api from '@/services/leave';

export default {
  namespace: 'leave',
  state: {
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
      const res = yield call(api.queryApplyList, payload);
      const { pagination } = yield select((state) => state.leave);
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
    *submit({ payload }, { call, put, select }) {
      yield call(api.submit, payload);
      const { condition, pagination } = yield select((state) => state.leave);
      yield put({
        type: 'list',
        payload: {
          ...condition,
          pageNo: pagination.current,
          pageSize: pagination.pageSize,
        },
      });
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
