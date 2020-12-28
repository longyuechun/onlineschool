<template>
	<div class="uploadTop">
		<el-row>
			<el-col :span="4">
				<img src="../assets/img/logo.png" style="width: 100%;height: 70%;" />
			</el-col>
			<el-col :span="6" :offset="4">
				<div class="test">
					<h2>搜索</h2>
				</div>
			</el-col>
			<el-col :span="8">
				<div class="headerpic">
					<el-avatar :size="40" :src="uinfo.upic"></el-avatar>
				</div>
			</el-col>
		</el-row>

	</div>
</template>

<script>
	export default {
		name: 'uploadTop',
		components: {

		},
		data() {
			return {
				uinfo:[],
			}
		},
		mounted() {
			this.getuinfo();
		},
		methods:{
			getuinfo(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/finduinfobyuid')
				.then(function(res){
					console.log(res.data)
					thiz.uinfo= res.data.data
				})
			}
		}
	}
</script>

<style>
	el-avatar {
		height: 100%;
		margin-top: 1.25rem;
		background-color: #008000;

	}

	.uploadTop {
		border-bottom: solid #FF7F50 0.0625rem;
	}

	.test {
		height: 100%;
	}

	.headerpic {
		margin-top: 1.25rem;
	}
</style>
