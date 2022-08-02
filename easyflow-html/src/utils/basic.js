import moment from 'moment';
/**
 * 设置cookie
 * @param name
 * @param value
 * @param expires
 * @param path
 */
export function setCookie(name, value, expires = 60 * 60 * 1000, path = '/') {
  const expiresDate = new Date();
  expiresDate.setTime(expiresDate.getTime() + expires);
  document.cookie = `${name}=${value};expires=${expiresDate};path=${path}`;
}

/**
 * 获取cookie值
 * @param name
 * @return {string|null}
 */
export function getCookie(name) {
  const reg = new RegExp(`(^| )${name}=([^;]*)(;|$)`);
  const arr = document.cookie.match(reg);
  if (arr) return unescape(arr[2]);
  return null;
}

export function formatDate(date, format = 'YYYY-MM-DD') {
  if (date) {
    return moment(date).format(format);
  }
  return date;
}

export function toMoment(date) {
  if (date) {
    return moment(date);
  }
  return date;
}

export function toDate(m, format = 'YYYY-MM-DD') {
  if (m) {
    return new Date(m.format(format));
  }
  return m;
}
