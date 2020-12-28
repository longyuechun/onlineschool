<template>
	<div>
		<searchTop />
		<el-row style="margin-top: 1.25rem;">
			<el-col :span="6" :offset="8">
				<el-input v-model="input"></el-input>
			</el-col>
			<el-col :span="2">
				<el-button class="btn" icon="el-icon-search" @click="btnsearch()">搜索</el-button>
			</el-col>
		</el-row>
		<el-row class="row">
			<nothing v-if="videoList == null"></nothing>
			<el-col v-else class="col" :span="6" :offset="1" v-for="item  in videoList" :key="item.release_id.id">
				<el-card  @click.native="govideo(item.cno)" style="height: 80%;width: auto;" shadow="hover" :body-style="{ padding: '5px' }">
					<img :src='item.cpic' class="image">
					<div style="padding: 14px;">
						<el-row>
							<el-col :span="12">
								<span>{{item.cname}}</span>
							</el-col>
							<el-col :span="12">
								<span>{{item.release_id.rtime}}</span>
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
					<div style="padding-top: 0.75rem;">
						<el-row>
							<el-col :span="8" :offset="2">
								<i class="el-icon-view">浏览量:{{item.cviewInfo_id.cpageView}}</i>
							</el-col>
							<el-col :span="8" :offset="4">
								<i class="el-icon-check">点赞量:{{item.cviewInfo_id.clike}}</i>
							</el-col>
						</el-row>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import searchTop from '../components/searchTop.vue'
	import nothing from '../components/nothing.vue'
	export default {
		name: 'searchResult',
		components: {
			searchTop,
			nothing
		},
		data() {
			return {
				input: '',
				videoList: []
			}
		},
		created() {
			this.input = this.$route.query.data
		},
		mounted() {
			this.findVideo(this.input);
		},
		methods: {
			btnsearch() {
				this.findVideo(this.input)
			},
			findVideo(input) {
				var thiz = this
				console.log(input)
				this.$axios.post('http://localhost:8090/onlineschool/findcinfobytypeorcname', {
					search: input
				}).then(function(res) {
					console.log(res.data)
					if(res.data.code == 0){
						thiz.videoList = res.data.data
					}else{
						thiz.videoList = null
						console.log(thiz.videoList)
					}
					//todo: 解决data[]遍历取值的问题
				})
			},
			govideo(cno) {
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
	.col {
		margin-bottom: 15px;
	}

	.image {
		width: 80%;
		height: 60%;
		margin-top: 10%;
	}

	.row {
		margin-top: 3.125rem;
		height: 12.5rem;
	}
</style>
