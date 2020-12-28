<template>
	<div class="menu-wrapper">
		<el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
			<el-radio-button :label="false">展开</el-radio-button>
			<el-radio-button :label="true">收起</el-radio-button>
		</el-radio-group>
		<el-menu 
		default-active="/upload" 
		class="el-menu-vertical-demo" 
		router="true" @open="handleOpen" @close="handleClose":collapse="isCollapse">
		
			<el-menu-item index="/upload/videoupload">
				<i class="el-icon-location"></i>
				<span slot="title">投搞</span></el-menu-item>
				
			<el-menu-item index="/upload/videomanagement">
				<i class="el-icon-message"></i>
				<span slot="title">稿件管理</span></el-menu-item>
				<!-- 
			<el-menu-item index="/upload/videoData">
				<i class="el-icon-document"></i>
				<span slot="title">数据中心</span></el-menu-item> -->
		</el-menu>

	</div>
</template>

<script>
	export default {
		name: "siderMenuforUpload",
		data() {
			return {
				isCollapse: true
			};
		},
	};
</script>

<style></style>
