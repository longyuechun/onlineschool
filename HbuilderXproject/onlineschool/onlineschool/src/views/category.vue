<template>
	<div>
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="4">
						<img src="../assets/img/logo.png" style="width: 100%;height: 70%;" />
					</el-col>
					<el-col :span="12" :offset="4">
						<search style="width: 100%;margin-top: 0.9375rem;" />
					</el-col>
				</el-row>
			</el-header>
			<el-main>
				<el-image class="img" style="width: 100%; height: 200px;" :src="picsrc" fill></el-image>
				<el-row>
					<el-col :span="4">
						<el-card shadow="always">
							分类：{{title}}
						</el-card>
					</el-col>
					<el-col style="margin-top: 0.3125rem" :span="16" :offset="4">
						<el-card>
							广告位招租
						</el-card>
					</el-col>
				</el-row>
				<div class="box">
					<el-row>
						<el-col span="7" :offset="1" v-for="item in videoList" :key="item.cno" >
							<el-card @click.native="govideo(item.cno)" style="height: 300px;width: 80%;" shadow="hover" :body-style="{ padding: '5px' }">
								<img :src='item.cpic' style="width: 100%; height: 100%;" >
								<div style="padding: 14px;">
									<el-row>
										<el-col :span="12">
											<span>{{item.cname}}</span>
										</el-col>
									</el-row>
								</div>
								<div>
									<el-row>
										<el-col :span="12">
											<span>所需杏林币:</span>
										</el-col>
										<el-col :span="4" :offset="3">
											<span style="color: #F98A29;">{{item.cmoney}}</span>
										</el-col>
									</el-row>
								</div>
							</el-card>
						</el-col>
					</el-row>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import search from '../components/search.vue'
	export default {
		name: 'category',
		components: {
			search
		},
		data: function() {
			return {
				title: "",
				picsrc: 'http://localhost:8090/onlineschool/toppic/basic.jpg',
				videoList: []
			}
		},
		created() {
			this.getTitle()
			this.getVideoList()
			this.gettoppic(this.title)
		},
		methods: {
			gettoppic(title){
				if(title == '基础医学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/basic.jpg'
				}else if(title == '医学影像学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/image.jpg'
				}else if(title == '临床医学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/clinical.jpg'
				}else if(title == '口腔医学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/Stomatology.jpg'
				}else if(title == '运动康复学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/sports.jpg'
				}else if(title == '药学'){
					this.picsrc= 'http://localhost:8090/onlineschool/toppic/Pharmacy.jpg'
				}
					
			},
			getTitle() {
				this.title = this.$route.query.data
			},
			getVideoList(){
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findcinfobytype',{
					search: this.title
				}).then(function(res){
					console.log(res.data)
					thiz.videoList = res.data.data
					console.log(thiz.videoList)
				})
			},
			govideo(cno){
				this.$router.push({
					path: 'video',
					query: {
						index: cno
					}
				});
			}
		}
	}
</script>

<style scoped="scoped">
	.tab {
		width: 90%;
		margin-left: 3%;
	}

	.menu {
		height: 80%;
		margin-top: 10%;
	}

	.img {
		width: 100%;
		height: 15.625rem;
	}

	.box {
		padding-top: 0.625rem;
		padding-right: 1.875rem;
		margin-top: 1.25rem;
	}

	.el-main {
		/* background-color: #E9EEF3; */
		color: #333;
		text-align: center;
		/* line-height: 160px; */
	}
</style>
