<template>
	<div>
		<h1>注册账号</h1>
		<div class="container">
			<el-form ref="ruleForm" :rules="rules" :model="formData">
				<el-form-item label="用户名" class="item" prop="username">
					<el-input v-model="formData.username" clearable auto-complete="new-uname" placeholder="请输入用户名" size="medium" style="width: 50%; margin-right: 10%;margin-left: 12px;" />
				</el-form-item>
				<el-form-item label="邮箱" class="item" prop="email">
					<el-input v-model="formData.email" clearable auto-complete="new-email" placeholder="请输入注册邮箱" size="medium" style="width: 50%; margin-right: 5%; margin-left: 25px;" />
				</el-form-item>
				<el-form-item label="密码" class="item" prop="password">
					<el-input v-model="formData.password" clearable auto-complete="new-password" placeholder="请输入密码" @focus.capture.native='changePasswordTip(true)' @blur.capture.native='changePasswordTip(false)' show-password size="medium" style="width: 50%; margin-right: 5%; margin-left: 25px;" />
					<div style="position: absolute">
						<verify-pass-word-tip :password="formData.password" :isShowTip='isShowTip'></verify-pass-word-tip>
					</div>
				</el-form-item>

				<el-form-item label="核对密码" class="item" prop="repassword">
					<el-input v-model="formData.repassword" clearable auto-complete="new-comfirm-password" placeholder="请再次输入密码"show-password size="medium" style="width: 50%; margin-right: 6%;" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary" style="margin-right: 10%;" @click="goRegister(formData.username,formData.password,formData.email)">确定注册</el-button>
					<el-button type="text" style="margin-left: 10%;" @click="reset()">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	import verifyPassWordTip from './verifyPassWordTip'
	import {isvalidUsername,isvalidPass} from '../assets/data/validate.js'
	export default {
		components: {
			verifyPassWordTip
		},
		data() {
			var validatePass = (rule , value, callback) =>{
				if (value === "") {
					callback(new Error("请输入密码"));
				} else if (!isvalidPass(value)) {
					callback(new Error("密码长度在8~24之间 需包含字母、数字下划线"));
				} 
			};
			var validatePass2 = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请输入确认密码"));
				} else if (value !== this.formData.password) {
					callback(new Error("两次输入密码不一致！"));
				} else {
					callback();
				}
			};
			var validUsername = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请输入用户名"));
				} else if (!isvalidUsername(value)) {
					callback(new Error("用户名要求数字、字母、下划线的组合 数字和字母必须存在 长度为4-15个字符"));
				}
			}
			return {
				rules: {
					username: [{
						required: true,
						validator: validUsername,
						trigger: "blur"
					}],
					password: [
						{
							required: true,
							validator: validatePass,
							trigger: "blur"
						}
					],
					repassword: [{
						required: true,
						validator: validatePass2,
						trigger: "blur"
					}],
					email: [{
							required: true,
							message: "必填",
							trigger: "blur"
						},
						{
							type: "email",
							message: "请输入正确的邮箱！",
							trigger: "blur"
						}
					]
				},
				formData: [],
				isShowTip: false,
			}
		},
		methods: {
			/**
			 * 改变密码提示是否显示
			 **/
			changePasswordTip(isShow) {
				if (isShow) {
					this.isShowTip = true;
				} else {
					this.isShowTip = false;
				}
			},
			reset(){
				this.$refs['ruleForm'].resetFields()
			},
			goRegister(username,password,email){
				var that = this
				console.log(username+" "+password+" "+email);
				if(!(username && password && email)){
					this.$message({
						message: '请将信息填写完整',
						type: 'error'
					})
				}else{
					var thiz = this
					this.$axios.post('http://localhost:8090/onlineschool/register',{
						upassword: password,
						uname: username,
						uemail: email
					}).then(function(res){
						console.log(res.data)
						if(res.data.code == 0){
							that.$message({
								message: '注册成功',
								type: 'success'
							})
							thiz.$router.push('/login')
						}else {
							that.$message({
								message: '邮箱已注册',
								type: 'error'
							})
						}
					})
					
				}
			}
		}
	}
</script>

<style scoped="scoped">
	.container {
		width: 80%;
		margin-left: 10%;
		border-style: solid;
		border: #42B983;
	}

	el-input {
		width: 60%;
		margin-left: 30%;
	}

	.item {
		width: 80%;
		margin-left: 20%;
		text-align: left;
	}
</style>
