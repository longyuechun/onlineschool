<template>
	<div>
		<el-row v-if="have">
			<el-col :span="10" :offset="2" v-for="item in videoList" :key="item.cno" @click.native="watch(item.cno)">
				<el-card style="margin-top: 1.25rem;">
					<img :src="item.cpic" style="width: 100%;height: 100%;" />
				</el-card>
			</el-col>
		</el-row>
		<!-- <el-card style="width: 350px; height: 350px;" v-for="item in videoList">
			<img :src="videoimg" @click="watch" style="width: 300px; height: 300px;" />
		</el-card> -->
	</div>

</template>

<script>
	export default {
		data() {
			return {
				videoList: [],
			};
		},
		mounted() {
			console.log("mounted")
			this.have();

		},
		methods: {
			have() {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/findcinfobystatus2")
					.then(function(res) {
						console.log(res.data)
						that.videoList = res.data.data
						console.log(that.videoList)
					}).catch(function(err) {
						console.log(err)
					})
			},
			watch(cno) {
				this.$router.push({
					path: '/watchvideo',
					query: {
						cno: cno
					}
				});
			}

		}
	}
</script>

<style scoped="scoped">

</style>
