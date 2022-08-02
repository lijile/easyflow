// 服务器地址前缀
const isDev = process.env.NODE_ENV === 'development';
export const SERVER_PREFIX = isDev ? 'http://localhost:8080' : '';

export const LEAVE_TYPES = ['年假', '事假', '产假', '病假'];
export const MODULE_LIST = [
  {
    moduleId: 'leave',
    moduleName: '请假',
  },
];

export const ACTION_STATUS = {
  WAIT: 0,
  AGREE: 1,
  DISAGREE: 2,
};

export const INSTANCE_STATUS = {
  IN_PROGRESS: 0,
  FINISHED: 1,
  TERMINATED: 2,
};
