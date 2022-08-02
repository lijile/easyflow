export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/flow',
    name: 'flow',
    icon: 'bulb',
    routes: [
      {
        path: '/flow/my-task',
        name: 'myTask',
        component: './flow/MyTask',
      },
      {
        path: '/flow/detail',
        name: 'detail',
        hideInMenu: true,
        component: './flow/Detail',
      },
    ],
  },
  {
    path: '/leave/list',
    name: 'leave.list',
    icon: 'table',
    component: './leave/ApplyList',
  },
  {
    path: '/admin',
    name: 'admin',
    icon: 'dashboard',
    routes: [
      {
        path: '/admin/definition/list',
        name: 'definition.list',
        component: './admin/DefinitionList',
      },
      {
        path: '/admin/definition/detail',
        name: 'definition.detail',
        component: './admin/DefinitionDetail',
        hideInMenu: true,
      },
    ],
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
