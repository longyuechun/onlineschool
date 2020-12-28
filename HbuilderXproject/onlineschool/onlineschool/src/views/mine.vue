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
			<el-container>
				<el-main class="main">
					<el-divider content-position="left">
						<h1 class="small">我发布的</h1>
					</el-divider>
					<el-carousel v-if="havepub" class="carl" height="150px" indicator-position="outside">
						<el-carousel-item   v-for="item in upub" :key="item.cno">
							<img :src="item.cpic" style="width: 100%; height: 100%;"/>
						</el-carousel-item>
					</el-carousel>
					<div v-else>
						<h1 >还未发布任何视频</h1>
						<el-button type="text" @click="gopost()">去发布</el-button>
					</div>
					<el-divider content-position="left">
						<h1 class="small">我订阅的</h1>
					</el-divider>
					<el-row v-if="haveusub">
						<el-col :span="10" :offset="2"  v-for="subclass in usub" :key="subclass.cno" @click.native="govideo(subclass.cno)" >
							<el-card style="margin-top: 1.25rem;">
								<img :src="subclass.cpic" style="width: 100%;height: 7.5rem;" />
								<span>{{subclass.cname}}</span>
							</el-card>
						</el-col>
					</el-row>
					<el-row v-else>
						<h1>还未订阅任何视频！</h1>
					</el-row>
				</el-main>

				<el-aside width="500px">
					<div>
						<el-card class="inf">
							<el-row>
								<el-avatar :size="50" :src="uinfo.upic"></el-avatar>
							</el-row>
							<el-row>
								<el-button type="text" @click="goperinfo()">{{uinfo.uname}}</el-button>
							</el-row>
							<el-row>
								<el-col style="margin-top: 0.625rem;" :span="6" :offset="3"><span>杏林币余额</span></el-col>
								<el-col style="margin-top: 0.3125rem;" :span="4">
									<el-button type="text" size="medium">{{uinfo.umoneyInfo_id.umoney}}个</el-button>
								</el-col>
								<el-col style="margin-top: 0.3125rem;" :span="6" :offset="3">
									<el-button type="warning" size="small" @click="gopay()">去充值</el-button>
								</el-col>
							</el-row>
						</el-card>
					</div>
					<el-card class="line">
						<el-timeline>
							<el-timeline-item timestamp="2018/4/12" placement="top">
								<el-card class="inner">
									<h4>更新 Github 模板</h4>
									<p>王小虎 提交于 2018/4/12 20:46</p>
								</el-card>
							</el-timeline-item>
							<el-timeline-item timestamp="2018/4/3" placement="top">
								<el-card class="inner">
									<h4>更新 Github 模板</h4>
									<p>王小虎 提交于 2018/4/3 20:46</p>
								</el-card>
							</el-timeline-item>
							<el-timeline-item timestamp="2018/4/2" placement="top">
								<el-card class="inner">
									<h4>更新 Github 模板</h4>
									<p>王小虎 提交于 2018/4/2 20:46</p>
								</el-card>
							</el-timeline-item>
						</el-timeline>
					</el-card>
				</el-aside>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				uinfo: [],
				usub:[],
				upub: [],
				haveusub: false,
				havepub: false,
			}
		},
		created() {
			this.getuinfo();
			this.getusub();
			this.getpub();
		},
		methods:{
			gopost(){
				this.$router.push('/upload/videoupload')
			},
			getuinfo(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/finduinfobyuid')
				.then(function(res){
					console.log(res.data)
					thiz.uinfo = res.data.data
				})
			},
			getusub(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findSubcinfobyuser')
				.then(function(res){
					console.log(res.data)
					if(res.data.code == 100)
						return thiz.haveusub =false
					else if(res.data.code ==0){
						thiz.usub = res.data.data
						return thiz.haveusub =true
					}
				})
			},
			getpub(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findrecinfobyuserstatus1')
				.then(function(res){
					console.log(res.data)
					if(res.data.code == 100)
						return thiz.havepub =false
					else{
						thiz.upub = res.data.data
						return thiz.havepub =true
					}
				})
			},
			gopay(){
				this.$router.push('pay');
			},
			goperinfo(){
				this.$router.push('perinfo')
			},
			govideo(cno){
				this.$router.push({
					name: 'video',
					query: {
						index: cno
					}
				})
			}
		}
	}
</script>

<style>
	.inner {
		height: 6.25rem;
	}

	.inf {
		width: 80%;
		margin-left: 10%;
		/* margin-top: 1.25rem; */
	}

	.line {
		width: 80%;
		margin-left: 10%;
		margin-top: 1.25rem;
	}

	.carl {
		width: 80%;
		margin-left: 10%;
		margin-bottom: 5rem;
		margin-top: 0.875rem;
		background-color: #E9EEF3;
	}

	.main {
		/* background-color: #E9EEF3; */
		color: #333;
		text-align: center;
	}

	.el-aside {
		/* background-color: #E4E7ED; */
	}
</style>
