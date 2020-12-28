import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
