<template>
	<el-container class="home-container">
		<el-header height="100px">
			<img src="../assets/rulai.png" style="width: 400px; height: 100px; float: left;" />
			<img src="../assets/exit.jpg" style="width: 40px; height: 40px; margin-left: 820px;" @click="exit" />
		</el-header>
		<el-container>
			<el-aside width="200px">
				<!-- 侧边栏菜单 -->
				<el-menu text-color="#000000" active-text-color="blue" background-color="cornsilk" :router="true" default-active="">
					<el-menu-item index="Power">管理员管理</el-menu-item>
					<el-menu-item index="Video">审核视频</el-menu-item>
					<el-menu-item index="Swiper">轮播页</el-menu-item>
					<el-menu-item index="Service" disabled>客服</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				<router-view>

				</router-view>
			</el-main>
		</el-container>
		
	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		created() {
		},
		methods: {
			exit() {
				this.$router.push('/login')
			},
			getadminbyano() {
				this.$axios.post("http://localhost:8090/onlineschool/findainfobyno",this.updateForm)
					.then(function(res) {
						console.log(res.data)
					}).catch(function(err) {
						console.log(err)
					})
			},
		}
	}
</script>

<style lang="less" scoped>
	.home-container {
		height: 100%;
	}

	.el-header {
		display: flex;
		padding-left: 0;
		align-items: center;
	}

	.el-aside {
		background-color: cornsilk;
	}

	.el-main {
		background-color: azure;
	}
	.el-input{
		width: 350px;
	}
</style>
