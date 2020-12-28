import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import VueRouter from 'vue-router'
import {
	Message
} from 'element-ui'

import Video from 'video.js'
import 'video.js/dist/video-js.css'

Vue.prototype.$video = Video

Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.prototype.$ajax = axios
Vue.prototype.$message = Message
Vue.use(axios);
Vue.use(router)
//request拦截器
Vue.prototype.$axios.interceptors.request.use(
	function(config) {
		if (localStorage.getItem('token')) {
			config.headers['token'] = localStorage.getItem('token')
		}
		return config;
	},
	function(error) {
		return Promise.reject(error)
	}
)
//response 拦截器
Vue.prototype.$axios.interceptors.response.use(
function(res) {
	return Promise.resolve(res);
	// if (res.data.code == 0 || res.data.code == 100 ) {
	// 	return Promise.resolve(res);
	// } else {
	// 	return Promise.reject(res);
	// }
},
function(error) {
	return Promise.reject(error);
});


new Vue({
	router,
	render: h => h(App)
}).$mount('#app')
