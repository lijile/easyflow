import { request } from '@/utils/request';

export async function listDefinition(options) {
  return request('/core/admin/list-definition', {
    method: 'GET',
    showLoading: true,
    ...(options || {}),
  });
}

export async function getDefinitionDetail(params, options) {
  return request('/core/admin/get-definition', {
    method: 'GET',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}

export async function saveDefinition(data, options) {
  return request('/core/admin/save-definition', {
    data,
    method: 'POST',
    showLoading: true,
    ...(options || {}),
  });
}

export async function updateDefinition(data, options) {
  return request('/core/admin/update-definition', {
    data,
    method: 'PUT',
    showLoading: true,
    ...(options || {}),
  });
}

export async function deleteDefinition(params, options) {
  return request('/core/admin/delete-definition', {
    method: 'DELETE',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}

export async function saveDefinitionNode(data, options) {
  return request('/core/admin/save-definition-node', {
    data,
    method: 'POST',
    showLoading: true,
    ...(options || {}),
  });
}

export async function updateDefinitionNode(data, options) {
  return request('/core/admin/update-definition-node', {
    data,
    method: 'PUT',
    showLoading: true,
    ...(options || {}),
  });
}

export async function deleteDefinitionNode(params, options) {
  return request('/core/admin/delete-definition-node', {
    method: 'DELETE',
    showLoading: true,
    params: { ...params },
    ...(options || {}),
  });
}
