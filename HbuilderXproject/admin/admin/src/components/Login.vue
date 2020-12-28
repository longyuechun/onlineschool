<template>
	<div class="login_container">
		<div class="login_box">
			<!-- 头像区域 -->
			<div class="img_box">
				<img src="../assets/login-rulai.png" alt="" />
			</div>
			<!-- 登录表单区域 -->
			<el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="80px" class="login_form">
				<el-form-item label="工号:" prop="ano">
					<el-input v-model="loginForm.ano"></el-input>
				</el-form-item>
				<el-form-item label="密码:" prop="apassword">
					<el-input v-model="loginForm.apassword" type="password"></el-input>
				</el-form-item>
				<!-- 按钮区域 -->
				<el-form-item label-width="110px">
					<el-button type="primary" @click="login">登录</el-button>
					<el-button type="info" @click="resetLoginForm">重置</el-button>
				</el-form-item>
			</el-form>

		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				//登录表单的数据绑定对象
				loginForm: {
					ano: '',
					apassword: ''
				},
				//表单验证规则
				loginFormRules: {
					ano: [{
						required: true,
						message: "请输入用户名",
						trigger: "blur"
					}],
					apassword: [{
						required: true,
						message: "请输入密码",
						trigger: "blur"
					}]
				}
			};
		},
		methods: {
			//重置按钮点击事件
			resetLoginForm() {
				this.$refs.loginFormRef.resetFields();
			},
			//登录按钮
			login() {
				this.$refs.loginFormRef.validate(async valid=>{
					if(!valid) return;
					const {data:res} = await this.$axios.post("http://localhost:8090/onlineschool/adminlogin",this.loginForm);
					if(res.code!==0) return this.$message.error("登录失败!工号或密码错误!");
					this.$message.success("登录成功!");
					console.log(res);
					this.$router.push('/home');
				});
			}
		}
	};
</script>

<style lang="less" scoped>
	.login_container {
		background-color: beige;
		height: 100%;
	}

	.login_box {
		width: 450px;
		height: 300px;
		background-color: aliceblue;
		border-radius: 3px;
		position: absolute;
		left: 33%;
		top: 25%;
	}

	.img_box {
		height: 130px;
		width: 130px;
		border: 1px solid #eee;
		border-radius: 50%;
		padding: 5px;
		box-shadow: 0 0 10px;
		position: absolute;
		left: 50%;
		transform: translate(-50%, -50%);

		img {
			width: 100%;
			height: 100%;
			border-radius: 50%;
			background-color: #eee;
		}
	}

	.login_form {
		position: absolute;
		bottom: 0;
		left: 10%;
	}
</style>
