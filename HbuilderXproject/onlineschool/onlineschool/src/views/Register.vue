<template>
	<div class="reg">
		<el-card class="box-card">
			<div>
				<img src="../assets/img/logo.png" />
			</div>
			<div class="title">
				<router-link :to="{name:'forgotView'}" style="color: #42B983; margin-left: 10%; "><el-button type="text" style="border-style: none; background-color: aliceblue;">忘记密码</el-button></router-link>
				<router-link :to="{name:'registerView'}" style="color: #42B983;margin-left: 20%;"><el-button type="text" style="border-style: none; background-color: aliceblue;">注册账号</el-button></router-link>
			</div>
			<router-view />
		</el-card>
	</div>
</template>

<script>
</script>

<style scoped="scoped">
	.reg {
		width: 50%;
		align-content: center;
		height: 100%;
		margin-left: 25%;
	}

	.title {
		width: 100%;
		color: coral;
		background-color: aliceblue;
		height: auto;
	}

	img {
		width: 40%;
		margin-left: 10%;
		height: 30%;
	}
</style>
