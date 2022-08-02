import { request as umiRequest } from 'umi';
import { message } from 'antd';
import { SERVER_PREFIX } from '@/utils/consts';

export async function request(url, options = {}) {
  const hide = options.showLoading && message.loading('loading...', 0);
  const res = await umiRequest(url, {
    prefix: SERVER_PREFIX,
    credentials: 'include',
    ...options,
  });
  if (hide) {
    hide();
  }
  return res;
}
