<template>
	<div>
		<el-row>
			<el-col :span="4">
				<img alt="Vue logo" src="../assets/img/logo.png" style="width: 250px;height: 68px;">
			</el-col>
		</el-row>
		<el-container>
			<el-header>
				<font color="#6C818C">
					<h3>杏林币充值</h3>
				</font>
			</el-header>
			<div style="text-align: left; margin-left: 18px;margin-top: 8px;">
				<font size="Extra Small" color="#99A2AA">充值金额</font>
			</div>
			<el-row style="margin-top: 8px;">
				<el-button plain  style="width: 150px;height: 80px;" @click="gopay()">
					<font size="5sp">6</font> 杏林币<br><br>
					￥ 6.00
				</el-button>
				
				<el-button plain style="width: 150px;height: 80px; margin-left: 20px;">
					<font size="5sp">18</font> 杏林币<br><br>
					￥ 18.00
				</el-button>
				<el-button plain style="width: 150px;height: 80px; margin-left: 20px">
					<font size="5sp">68</font> 杏林币<br><br>
					￥ 68.00
				</el-button>
				<el-button plain style="width: 150px;height: 80px; margin-left: 20px">
					<font size="5sp">233</font> 杏林币<br><br>
					￥ 233.00
				</el-button>
			</el-row>
			<el-row type="flex" style="margin-top: 20px;margin-left: 18px;"><br>
				<el-button plain style="width: 150px;height: 80px;">
					<font size="5sp">648</font> 杏林币<br><br>
					￥ 648.00
				</el-button>
				<el-button plain style="width: 150px;height: 80px; margin-left: 20px">
					<font size="5sp">998</font> 杏林币<br><br>
					￥ 998.00
				</el-button>
			</el-row><br>
			<el-row type="flex" style="margin-left: 18px;">
				<el-button type="text" @click="open()">使用说明</el-button>
			</el-row>
		</el-container>
	</div>
</template>

<script>
	export default {
		data(){
			return{
				url:''
			}
		},
		methods: {
			open() {
				this.$alert('杏林币是购买杏林网校平台上付费视频的预付费凭证，只可用于购买杏林网校平台上的视频/服务，杏林币和人民币的兑换比例为：1杏林币=1.00元人民币', '杏林币使用说明', {
					confirmButtonText: '知道了',
					callback: action => {
						this.$message({
							type: 'info',
							message: `action: ${ action }`,
						});
					}
				});
			},
			gopay(){
				var thiz = this
				this.$axios.post(' http://localhost:8090/onlineschool/order/alipay',{
					money:"6"
				}).then(function(res){
					console.log(res.data)
					thiz.url = res.data
					const div = document.createElement('div')
					div.innerHTML = thiz.url//此处form就是后台返回接收到的数据
					document.body.appendChild(div)
                    document.forms[0].submit()
				})
			}
		}
	}
</script>

<style scoped="scoped">
	.el-container {
		width: 700px;
		height: 400px;
		border: #C9CED2 solid 1.2px;
		margin-top: 30px;
		margin-left: 350px;
	}

	.el-header {
		width: 697px;
		height: 5px;
		background-color: #EEF2F4;
		text-align: left;
		margin-top: 0;
	}
</style>
