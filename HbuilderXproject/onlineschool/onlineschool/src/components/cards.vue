<template>
	<div id="cards">
		<el-row style="margin-top: 20px;">
			<el-col :span="5" :offset="1" v-for="item in videoList" :key="item.release_id.id">
				<el-card @click.native="govideo(item.cno)" style="height: 12.5rem;width: auto;" shadow="hover" :body-style="{ padding: '5px' }">
					<img :src="item.cpic" class="image" />
					<div style="padding: 14px;">
						<span>{{item.cname}}</span>
						<div class="bottom clearfix">
							<h6>视频介绍：{{item.cintro}}</h6>
							<el-row>
								<el-col :span="12">{{item.cviewInfo_id.cpageView}}</el-col>
								<el-col :span="12">{{item.cviewInfo_id.clike}}</el-col>
							</el-row>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>

	</div>
</template>

<script>
	export default {
		name: 'cards',
		data() {
			return {
				//todo:
				videoList: [],
			}
		},
		mounted() {
			this.init()
		},
		methods: {
			init() {
				console.log(this.picurl)
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findcinfobycpageview')
					.then(function(res) {
						console.log(res.data)
						thiz.videoList = res.data.data
					})
			},
			govideo(cno){
				console.log("click")
				this.$router.push({
					path: 'video',
					query: {
						index: cno
					}
				});
			}
		},
	}
</script>

<style scoped="scoped">
	.image {
		height: 6.25rem;
		width: auto;
	}
</style>
