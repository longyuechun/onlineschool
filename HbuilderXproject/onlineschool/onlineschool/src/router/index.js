import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import searchResult from '../views/searchResult.vue'
import video from '../views/video.vue'
import mine from '../views/mine.vue'
import category from '../views/category.vue'
import pay from '../views/pay.vue'
import perinfo from '../views/perinfo.vue'
import login from '../views/login.vue'
Vue.use(VueRouter)

const routes = [{
		path: '/login',
		name: 'login',
		component: login
	},
	{
		path: '/',
		name: 'Home',
		component: Home
	},
	{
		path: '/category',
		name: 'category',
		component: category
	},
	{
		path: '/searchResult',
		name: 'searchResult',
		component: searchResult
	},
	{
		path: '/video',
		name: 'video',
		component: video,
		meta: { requireAuth: true}
	},
	{
		path: '/pay',
		name: 'pay',
		component: pay
	},
	{
		path: '/selfInfo',
		name: 'selfInfo',
		component: mine,
	},
	{
		path: '/about',
		name: 'About',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import( /* webpackChunkName: "about" */ '../views/About.vue')
	},
	{
		path: '/perinfo',
		name: 'perinfo',
		component: perinfo
	},
	{
		path: '/register',
		name: 'Register',
		component: () => import('../views/Register.vue'),
		children: [{
				path: 'registerView',
				name: 'registerView',
				component: () => import('../components/registerView.vue')
			},
			{
				path: 'forgotView',
				name: 'forgotView',
				component: () => import('../components/forgotView.vue')
			}
		]
	},
	{
		path: '/upload',
		name: 'upload',
		meta: {
			requireAuth: true
		},
		component: () => import('../views/upload.vue'),
		children: [{
				path: 'videoupload',
				name: 'videoupload',
				meta: {
					requireAuth: true
				},
				component: () => import('../components/videoupload.vue')
			},
			{
				path: 'videomanagement',
				name: 'videomanagement',
				meta: {
					requireAuth: true
				},
				component: () => import('../components/videoManagement.vue')
			},
			{
				path: 'videoData',
				name: 'videoData',
				meta: {
					requireAuth: true
				},
				component: () => import('../components/videoData.vue')

			}
		]
	},


]

const router = new VueRouter({
	mode: 'history',
	routes,
})
//导航守卫
//使用 router。beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
	if(to.meta.requireAuth){
		let token = localStorage.getItem('token')
		if(token){
			next()
		}else{
			next({
				path: '/login',
				query: {
					redirect: to.fullPath
				}
			})
		}
	}else{
		next()
	}
});
export default router
