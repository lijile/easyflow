import { request } from '@/utils/request';

export async function queryList(params, options) {
  return request('/core/flow/my-task', {
    method: 'GET',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}

export async function queryFlowDetail(params, options) {
  return request('/core/flow/detail', {
    method: 'GET',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}

export async function approve(taskCode, data, options) {
  return request('/core/flow/approve', {
    method: 'POST',
    showLoading: true,
    params: { taskCode },
    data: { ...data },
    ...(options || {}),
  });
}

export async function changeNode(params, options) {
  return request('/core/flow/changeNode', {
    method: 'POST',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}
