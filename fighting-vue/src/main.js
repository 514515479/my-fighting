import Vue from 'vue'
import App from './App.vue'
// 引入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';

// 引入mavon-editor是一款基于vue的markdown编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// 引入图标样式
import 'font-awesome/css/font-awesome.min.css'
// 引入全局公共样式
import './assets/css/style.css'

// 引入封装好的msg
import msg from './utils/msg'
// 引入路由
import router from './router'
// 引入Vuex
import store from './store'
// 引入封装好的axios
import axios from './http'

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(mavonEditor);
// 将axios挂载到Vue上
Vue.prototype.$axios = axios;
// 将msg挂载到Vue上
Vue.prototype.$msg = msg;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
