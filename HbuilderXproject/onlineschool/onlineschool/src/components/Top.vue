<template>
	<div class="top">
		<el-row>
			<el-col :span="4">
				<img src="../assets/img/logo.png" style="width: 100%;height: 70%;" />
			</el-col>
			<el-col :span="6" :offset="4">
				<search style="width: 100%;margin-top: 10px;"/>
			</el-col>
			<el-col :span="7">
				<navbar></navbar>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import navbar from "./navbar.vue"
	import search from "./search.vue"
	export default {
		name: 'Top',
		components: {
			navbar,
			search
		}
	}
</script>

<style>
	.top {
		width: 100%;
		margin-left: 1.25rem;
		margin-right: 1.25rem;
		position: sticky;
	}
</style>
