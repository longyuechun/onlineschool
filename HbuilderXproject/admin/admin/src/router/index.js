import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Power from '../components/Power.vue'
import Video from '../components/Video.vue'
import Swiper from '../components/Swiper.vue'
import WatchVideo from '../components/WatchVideo.vue'

Vue.use(VueRouter)

const router = new VueRouter({
	routes: [{
			path: '/',
			hidden: true,
			redirect: '/login'
		},
		{
			path: '/login',
			hidden: true,
			component: Login
		},
		{
			path: '/home',
			component: Home,
			children: [{
				path: '/power',
				component: Power
			}, {
				path: '/video',
				component: Video,
			}, {
				path: '/swiper',
				component: Swiper
			}, {
				path: '/watchvideo',
				component: WatchVideo
			}]
		}
	]
})

export default router
