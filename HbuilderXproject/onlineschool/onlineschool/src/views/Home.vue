<template>
	<div class="home">
		<el-container class="container">
			<el-header class="header">
				<Top />
			</el-header>
			<el-main class="main">
				<el-divider></el-divider>
				<swiper :swiperList="swiperList" />
				<h2>时下热门</h2>
				<el-divider></el-divider>
				<cards></cards>
				
			</el-main>
			<el-footer class="footer">
				<el-divider></el-divider>
				<bottom></bottom>
			</el-footer>
		</el-container>

	</div>
</template>

<script>
	import swiper from "../components/swiper.vue"
	import Top from "../components/Top.vue"
	import cards from "../components/cards.vue"
	import bottom from "../components/bottom.vue"
	
	export default {
		name: 'Home',
		components: {
			swiper,
			Top,
			cards,
			bottom
		},
		data() {
			
			return {
				swiperList: []
			}
		},
		created() {
			this.getswiperList()
		},
		methods:{
			getswiperList(){
				var thiz = this
				this.$axios.get('http://localhost:8090/onlineschool/findallswiper')
				.then(function(res){
					console.log(res.data)
					thiz.swiperList = res.data.data
				})
			}
		}

	}
</script>
<style scoped="scoped">
	h2 {
		text-align: left;
		margin-left: 1.25rem;
	}

	.header {
		top: 10px;
	}

	.main {
		margin-left: 0.625rem;
		margin-right: 0.625rem;

	}

	.footer {
		margin-top: 0.625rem;
		margin-left: 0.625rem;
		margin-right: 0.625rem;

	}
</style>
