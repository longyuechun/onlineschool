<template>
	<div>
		<el-container>
			<el-header><uploadTop/></el-header>
			<el-container>
				<el-aside width="250px">
				<el-menu 
				:default-active="this.$route.path"  
				router active-text-color="#f98a29"
				class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
				<sideMenuforUpload />
				</el-menu>
				</el-aside>
				<el-main><router-view/></el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	import uploadTop from '../components/uploadTop.vue'
	import sideMenuforUpload from '../components/siderMenuforUpload.vue'
	export default {
		name: 'upload',
		components: {
			uploadTop,
			sideMenuforUpload
		},
		method: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			}
		},
	}
</script>

<style>
	.aside {
		background-color: #008000;
	}
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 200px;
		min-height: 400px;
		margin-top: 1.875rem;
	}
</style>
