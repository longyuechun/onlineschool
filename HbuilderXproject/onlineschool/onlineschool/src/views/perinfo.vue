<template>
	<div>
		<el-row>
			<el-col :span="4">
				<img alt="Vue logo" src="../assets/img/logo.png" style="width: 250px;height: 68px;">
			</el-col>
		</el-row>
		<el-upload class="avatar-uploader" :action="actionurl" :show-file-list="false" :on-success="handleAvatarSuccess"
		:before-upload="beforeAvatarUpload" style="margin-top: 30px;">
			<img v-if="ruleForm.upic" :src="ruleForm.upic" class="avatar" />
			<i v-else class="el-icon-plus avatar-uploader-icon"></i>
		</el-upload><br>

		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="margin-left: 500px; width: 400px;">
			<el-form-item label="用户名:" prop="name">
				<el-input v-model="ruleForm.uname" style="width: 300px;" ></el-input>
			</el-form-item>
			<el-form-item label="性别:" prop="resource">
				<el-radio-group v-model="ruleForm.usex">
					<el-radio label="男"></el-radio>
					<el-radio label="女"></el-radio>
				</el-radio-group>
			</el-form-item>
			<el-form-item label="年龄:" prop="age">
				<el-input v-model="ruleForm.uage" style="width: 300px;"></el-input>
			</el-form-item>
			<el-form-item label="电子邮箱:" prop="email">
				<el-input v-model="ruleForm.uemail" style="width: 300px;" :disabled="true"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="submitForm(ruleForm)">提交</el-button>
				<el-button @click="resetForm(ruleForm)" style="margin-left: 50px;">重置</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				actionurl: "http://localhost:8090/onlineschool/headpicupload",
				ruleForm: [],
				// rules: {
				// 	age: [{
				// 		required: true,
				// 		message: '请输入年龄',
				// 		trigger: 'blur'
				// 	}, {
				// 		max: 2,
				// 		message: '请输入正确的年龄0-99',
				// 		trigger: 'blur'
				// 	}]
				// }
			};
		},
		mounted() {
			this.getinfo();
		},
		methods: {
			getinfo(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/finduinfobyuid')
				.then(function(res){
					thiz.ruleForm = res.data.data
					console.log(thiz.ruleForm)
				})
			},
			handleAvatarSuccess(res,file) {
				this.ruleForm.upic = URL.createObjectURL(file.raw);
				console.log(res.data)
				console.log("头像：")
				console.log(file)
				this.ruleForm.upic = file.response.data
			},
			beforeAvatarUpload(file) {
				const isJPG = file.type === 'image/png' || "image/jpg" || "image/jpeg";
				const isLt2M = file.size / 1024 / 1024 < 2;
				if (!isJPG) {
					this.$message.error('上传头像图片只能是 JPG/JPEG/PNG 格式!');
				}
				if (!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			},
			submitForm(formName) {
				if(!(formName.uage&&formName.usex)){
					this.$message({
						message: '请填写完整',
						type: 'error'
					})
				}else{
					//todo:提交表单给后台
					console.log(formName)
					var thiz = this
					this.$axios.post('http://localhost:8090/onlineschool/updateuinfo',{
						uage: formName.uage,
						usex: formName.usex,
						upic: formName.upic,
						uname: formName.uname,
						uid: formName.uid,
					}).then(function(res){
						console.log(res.data)
						if(res.data.code ==0){
							thiz.$message({
								message: '修改成功',
								type: 'success'
							})
						}
					})
				}
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>

<style scoped="scoped">
	.borders {
		margin-top: 30px;
		margin-left: 350px;
		width: 600px;
		height: 680px;
		border: #C9CED2 solid 1.2px;
	}
	.avatar-uploader-icon {
		border: 1px dashed #d9d9d9;
		border-radius: 100px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
		font-size: 28px;
		color: #8c939d;
		width: 100px;
		height: 100px;
		line-height: 100px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
