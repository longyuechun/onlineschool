<template>
	<div class="search">
		<div>
			<el-row inline-block>
				<el-col :span="14">
					<el-input placeholder="请输入内容" v-model="input"/>
				</el-col>
				<el-col :span="6" >
					<el-button class="btn" icon="el-icon-search" @click= "btnsearch()"/>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'search',
		data() {
			return {
				input: ''
			}
		},
		methods: {
			btnsearch() {
				this.$router.push({
					path: 'searchResult',
					query: {
						data: this.input
					}
				});
			}

		}
	}
</script>

<style scoped="scoped">
	.btn {
		background-color: #EBF8FC;
	}
</style>
