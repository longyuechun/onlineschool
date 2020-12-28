<template>
	<div>
		<el-upload drag action="http://localhost:8090/onlineschool/videoUpload" :on-preview="handlePreview" :on-success="onSuccess" accept=".mp4" class="upload-demo">
			<i class="el-icon-upload"></i>
			<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
		</el-upload>
		<el-form :rules="rules" :model="videoForm">
			<el-form-item style="text-align: left;">
				<h2>基本信息</h2>
			</el-form-item>
			<el-form-item label="标题" required prop="videoForm.title">
				<el-input placeholder="请输入稿件标题" v-model="videoForm.title" maxlength="10" show-word-limit />
			</el-form-item>
			<el-row>
				<el-col :span="8">

					<el-from-item label="分类" required prop="videoForm.type">
						<el-select v-model="videoForm.type" filterable placeholder="请选择分类" style="width: 100%">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
						</el-select>
					</el-from-item>
				</el-col>
				<el-col :span="10" :offset="4">
					<el-form-item label="课程价值" prop="videoForm.radio">
						<el-radio-group  v-model="videoForm.radio" @change="getValue()">
							<el-radio :label="0">免费</el-radio>
							<el-radio :label="60">60币</el-radio>
							<el-radio :label="120">120币</el-radio>
						</el-radio-group>
					</el-form-item>
				</el-col>
			</el-row>
			<el-form-item label="简介" prop="videoForm.info">
				<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="填写更全面的内容,让更多的人找到你的视频吧!" v-model="videoForm.info" />
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="submit(videoForm)">提交</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: 'videoupload',
		data() {
			var validTitle = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请输入标题"));
				}
			};
			var validType = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请选择分类"))
				}
			}
			return {
				rules: {
					title: [{
						required: true,
						validator: validTitle,
						trigger: "blur"
					}],
					type: [{
						required: true,
						validator: validType,
						trigger: "blur"
					}]

				},
				options: [{
					value: '基础医学',
					label: '基础医学',
				}, {
					value: '医学影像学',
					label: '医学影像学'
				}, {
					value: '临床医学',
					label: '临床医学'
				},{
					value: '口腔医学',
					label: '口腔医学'
				},{
					value: '运动康复学',
					label: '运动康复学'
				},{
					value: '药学',
					label: '药学'
				}],
				videoForm: {
					title: '',
					type: '',
					info: '',
					radio: '',
					picurl: '',
					videourl: ''
				},
				
			}
		},
		methods: {
			getValue(){ console.log(this.videoForm.radio)},
			submit(videoForm) {
				//todo: 请求有问题
				console.log("视频图片地址啊啊啊")
				console.log(videoForm.urls )
				console.log(videoForm.title + " " + videoForm.type + " " + videoForm.info+this.videoForm.radio+ this.videoForm.info);
				if(!videoForm.picurl||!videoForm.videourl){
					this.$message({
						message: '请先上传视频!',
						type: 'error'
					})
				}else if(!(videoForm.title && videoForm.type && videoForm.radio)){
					this.$message({
						message: '请先填写表单信息!',
						type: 'error'
					})
				}else{
				var thiz = this
				this.$axios.post(' http://localhost:8090/onlineschool/addcinfo',{
					cname: videoForm.title,
					ctype: videoForm.type,
					cintro: videoForm.info,
					cvideo: videoForm.videourl,
					cpic: videoForm.picurl,
					cmoney: videoForm.radio
				}).then(function(res){
					console.log(res.data)
					if(res.data.code == 0){
						thiz.$message({
							message: '已上传成功，请等待审核！',
							type: 'success'
						})
						
					}else{
						this.$message({
							message: '上传失败请重试！',
							type: 'error'
						})
					}
				})
				}
			},
			onSuccess(response, file) {
				// console.log(response.data)
				console.log(file)
				this.videoForm.picurl = response.data.cpic
				this.videoForm.videourl = response.data.cvideo
				
				console.log("视频图片地址")
				
			},
			handlePreview(file) {
				console.log(file)
				console.log(file.response.url)
			}
		},
	}
	
</script>

<style>
	.upload-demo {
		margin-top: 5%;
		width: 80%;
		height: 40%;
	}
</style>
