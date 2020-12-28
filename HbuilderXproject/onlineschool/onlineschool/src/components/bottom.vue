<template>
	<div class="footer">
		<el-row>
			<el-col :span="8"><img src="../assets/img/logo.png" style="width: 100%;">
			</el-col>
			<el-col :span="8">
					<el-row>
						<h2>关于我们</h2>
					</el-row>
					<el-row>
						<span><a>开发人员</a></span></el-row>
					<el-row>
						<span><a> 详细信息</a></span></el-row>
				
			</el-col>
		</el-row>
		<el-row>
			<h6 style="text-align: left; margin-left: 20px;">杏林网校滴滴答答黑黑</h6>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: 'footer'
	}
</script>

<style scoped="scoped">
	span,h2,h6{
		color: #f98a29;
	}
	.footer {
	}
</style>
