<template>
	<div>
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="4">
						<img src="../assets/img/logo.png" style="width: 100%;height: 70%;" />
					</el-col>
				</el-row>
			</el-header>
					<div style="margin-top: 40px; ">
						<el-row>
							<el-col style="margin-bottom: 1.875rem;">
								<span>当前视频：{{video.cname}}</span>
								<span style="margin-left: 32.5rem;">分类：{{video.ctype}}</span>
							</el-col>
						</el-row>
								<img v-if="aceesstovideo == false" :src="video.cpic" style="width: 70%; height: 25rem;"/>
								<video v-else controls :poster="video.cpic" autoplay style="width: 70%; height: 25rem;" class="vjs-big-play-centered">
									<source :src="video.cvideo"></source>
								</video>
						<el-row>
							<el-col>作者:<span style="margin-left: 1.25rem;">{{video.release_id.uinfo_id.uname}}</span>
							<el-button style="width: 6.25rem;margin-left: 28.75rem;" type="primary" icon="el-icon-star-off">点赞</el-button>
							<el-button v-if="aceesstovideo == false" style="width: 6.25rem;" type="primary" icon="el-icon-bell" @click="okvideo()">订阅</el-button>
							<el-button v-else style="width: 6.25rem;" type="primary" icon="el-icon-bell" >已订阅</el-button>
							</el-col>
						</el-row>
						<el-row style="margin-top: 1.25rem; padding-bottom: 0.625rem;">
							<el-col>
								<div style="width: 70%; margin-left: 15%;background-color: #fdf6ec;text-align: left;">
									<span>视频简介:</span><span >{{video.cintro}}</span>
									<!-- <el-input :disabled="true" type="textarea" :autosize="{ minRows: 3, maxRows: 7}" v-model="video.cintro">
									</el-input> -->
								</div>
							</el-col>
						</el-row>
					</div>
					<el-row style="margin-top: 1.875rem;">
						<el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
							<span>这个课程需要杏林币：{{video.cmoney}}</span>
							<span slot="footer" class="dialog-footer"/>
							<span class="dialog-footer">
								<el-button @click="dialogVisible = false">取 消</el-button>
								<el-button type="primary" @click="goget()">确 定</el-button>
							</span>
						</el-dialog>
					</el-row>

			<div class="comment">

			</div>
		</el-container>
	</div>
</template>

<script>
	import {
		videoPlayer
	} from 'vue-video-player'
	import 'video.js/dist/video-js.css'
	/* eslint-disable */
	export default {
		name: 'videoShowPage',
		components: {
			videoPlayer,
		},
		data() {
			return {
				aceesstovideo: false,
				dialogVisible: false,
				videoid: '',
				video:[]
			}
		},
		methods: {
			okvideo() {
				this.dialogVisible = true
			},
			getvideo(videoid) {
				var thiz = this
				this.$axios.get('http://localhost:8090/onlineschool/getcinfobycno/' + videoid)
					.then(function(res) {
						console.log(res.data)
						thiz.video = res.data.data
					})
			},
			goget(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/buycinfo',{
					cno: this.video.cno
					}).then(function(res){
						console.log(res.data)
						if(res.data.code == 0){
							thiz.aceesstovideo = true
							thiz.$message({
								message: '购买成功！',
								type: 'success'
							})
						}else {
							thiz.$message({
								message: '余额不足！',
								type: 'error'
							})
						}
					})
					thiz.dialogVisible = false;
			},
			getvideoaceess(videoid){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/isbuy',{
					cno: videoid
				}).then(function(res){
					console.log("获取视频权限")
					console.log(res.data)
					if(res.data.code == 5 || res.data.code == 6){
						return thiz.aceesstovideo =true
					}else{
						return thiz.aceesstovideo = false
					}
				})
			}
		},
		created() {
			console.log(this.$route.query.index)
			this.videoid = this.$route.query.index;
			console.log(this.videoid)
		},
		mounted() {
			this.getvideo(this.videoid);
			this.getvideoaceess(this.videoid);
		}
	};
</script>

</script>

<style scoped="scoped">
		
	
	#comment {
		border: solid #000000;
	}

	.aside {
		margin-top: 1.875rem;
	}

	.messagebox {
		border: #000000 0.0625rem;
		width: 80%;
		margin-left: 10%;
		background-color: #EBF8FC;
		padding: 0.3125rem;
	}
</style>
