import { request } from '@/utils/request';

export async function queryApplyList(params, options) {
  return request('/leave/apply-list', {
    method: 'GET',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}

export async function submit(form, options) {
  return request('/leave/submit', {
    method: 'POST',
    showLoading: true,
    data: form,
    ...(options || {}),
  });
}
