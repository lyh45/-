import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router/index.js'
import VueCookie from 'vue-cookie'
import App from './App.vue';

Vue.use(ElementUI);
Vue.use(VueCookie);

new Vue({
  el: '#app',
  render: h => h(App),
  router
});