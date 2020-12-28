<template>
	<div>
		<el-card class="card">
			<el-row>
				<el-col :span="12"><img src="../assets/img/logo.png" style="width: 100% ;margin-top: 1.875rem;" /></el-col>
				<el-col :span="12">
					<el-row>
						<el-form ref="ruleForm" :rules="rules" :model="form">
							<el-form-item label="邮箱" class="item" prop="email">
								<el-input v-model="form.email" clearable auto-complete="new-email" placeholder="请输入邮箱" style="width: 50%; margin-right: 10%;" />
							</el-form-item>
							<el-form-item label="密码" class="item" prop="password">
								<el-input v-model="form.password" clearable auto-complete="new-password" show-password placeholder="请输入密码"
								style="width: 50%; margin-right: 10%;" />
								</el-input>
							</el-form-item>
						</el-form>
						<div align="center">
							<el-button type="primary" @click="login()">登陆</el-button>
						</div>
					</el-row>
					<el-row style="margin-top: 1.875rem;">
						<div>
							<span>还没有账户?<router-link tag="a" target="_blank" :to="{ path: '/register/registerView' }" style="color: #42B983;">去注册</router-link>
							</span>
							<span style="padding-left: 20%;">
								<router-link tag="a" target="_blank" :to="{ path: '/register/forgotView' }" style="color: #42B983;">忘记密码</router-link>
							</span>
						</div>
					</el-row>
				</el-col>
			</el-row>
		</el-card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				form: [],
				rules: {
					email: [{
						required: true,
						message: "必填",
						trigger: "blur"
					}, {
						type: "email",
						message: "请输入正确的邮箱！",
						trigger: "blur"
					}],
					password: [{
						required: true,
						message: "必填",
						trigger: "blur"
					}]
				},
			}
		},
		methods: {
			getloginuid(){
				this.$axios.post('http://localhost:8090/onlineschool/finduinfobyuid')
				.then(function(res){
					console.log(res.data)
				})
			},
			login() {
				var thiz = this
				if (this.form.email == "") {
					thiz.$message({
						message: '请输入邮箱',
						type: 'error'
					})
				} else if (this.form.password == "") {
					thiz.$message({
						message: '请输入密码',
						type: 'error'
					})
				} else if(this.form.email && this.form.password){
					this.$axios.post('http://localhost:8090/onlineschool/login', {
						uEmail: this.form.email,
						uPassword: this.form.password
					}).then(function(res) {
						console.log(res.data)
						if (res.data.code == 500){
							thiz.$message({
								message: '账户邮箱或密码错误',
								type: 'error'
							})
						}else if(res.data.code == 0){
							thiz.$message({
								message: '登陆成功',
								type: 'success'
							})
							console.log(res.data.data.token)
							localStorage.setItem('token',res.data.data.token)
							thiz.$router.push({
								path: '/'
							});
						}
					}).catch(function(error){
						console.log(error)
						thiz.$message({
							message: "账户邮箱或密码错误",
							type: 'error'
						})
					})
				}

			}
		}
	}
</script>

<style scoped="scoped">
	.card {
		width: 70%;
		height: auto;
		margin-left: 15%;
		margin-top: 6.25rem;
	}
</style>
