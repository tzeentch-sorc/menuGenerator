import Vue from 'vue';
import App from './App.vue';
import { router } from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'vue-material/dist/theme/default.css'
import {VueMasonryPlugin} from 'vue-masonry';
import VueObserveVisibility from "vue-observe-visibility";
import BackToTop from  'vue-backtotop'


Vue.config.productionTip = false;

Vue.use(VueMasonryPlugin)
Vue.use(VueObserveVisibility)
Vue.use(VeeValidate);
Vue.use(VueMaterial);
Vue.use(BackToTop);

Vue.use(Vuex);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
