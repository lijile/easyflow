import * as api from '@/services/admin';
import { history } from 'umi';

export default {
  namespace: 'admin',
  state: {
    definitionList: [],
  },
  effects: {
    *listDefinition({ payload }, { call, put }) {
      const res = yield call(api.listDefinition);
      const { data } = res;
      yield put({
        type: 'updateState',
        payload: {
          definitionList: data,
        },
      });
    },
    *saveDefinition({ payload }, { call, put }) {
      const res = yield call(api.saveDefinition, payload);
      const { data } = res;
      history.push(`/admin/definition/detail?definition_code=${data.definition.definitionCode}`);
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
