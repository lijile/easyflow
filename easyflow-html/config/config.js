// https://umijs.org/config/
import { defineConfig } from 'umi';
import defaultSettings from './defaultSettings';
import proxy from './proxy';
import routes from './routes';
const { REACT_APP_ENV } = process.env;

const isDev = process.env.NODE_ENV === 'development';
const basePath = isDev ? '/' : '/public/';
export default defineConfig({
  base: basePath,
  publicPath: basePath,
  // window.publicPath 全局变量输出
  runtimePublicPath: true,
  history: {
    type: "hash"
  },
  hash: false,
  antd: {},
  outputPath: '../src/main/resources/public',
  dva: {
    hmr: true,
  },
  layout: {
    // https://umijs.org/zh-CN/plugins/plugin-layout
    locale: true,
    siderWidth: 208,
    ...defaultSettings,
  },
  // https://umijs.org/zh-CN/plugins/plugin-locale
  locale: {
    // default zh-CN
    default: 'zh-CN',
    antd: true,
    // default true, when it is true, will use `navigator.language` overwrite default
    baseNavigator: true,
  },
  targets: {
    ie: 11,
  },
  // umi routes: https://umijs.org/docs/routing
  routes,
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  theme: {
    'primary-color': defaultSettings.primaryColor,
  },
  // esbuild is father build tools
  // https://umijs.org/plugins/plugin-esbuild
  // 导致ie11无效字符
  esbuild: isDev && {},
  title: false,
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
  // Fast Refresh 热更新
  fastRefresh: {},
  nodeModulesTransform: {
    // ie11 语法错误
    // all和none，前者速度较慢，但可规避常见的兼容性等问题，后者反之。
    type: isDev ? 'none' : 'all',
  },
  // ie11时需要注释掉，否则报错缺少 ')'
  mfsu: {},
  webpack5: {},
});
