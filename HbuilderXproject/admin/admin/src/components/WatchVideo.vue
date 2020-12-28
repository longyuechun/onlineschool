<template>
	<div>
		<div class="videobox">
			<video style="width: 600px; height: 350px;" controls poster="picurl" autoplay class="vjs-default-skin vjs-big-play-centered" :src="videourl">
			</video>
		</div>
		<el-button type="warning" @click="unpass()">审核未通过</el-button>
		<el-button type="success" @click="pass()">审核通过</el-button>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				videourl: '',
			};
		},
		created() {
			this.cno = this.$route.query.cno;
			console.log(this.cno)
		},
		mounted() {
			console.log("mounted")
			this.getvideo()
		},
		methods: {
			getvideo() {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/getcinfobycno/"+this.cno)
					.then(function(res) {
						console.log(res.data)
						that.videourl = res.data.data.cvideo;
						console.log(that.videourl)
					}).catch(function(err) {
						console.log(err)
					})
					
			},
			pass(){
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/updatecinfostatus",{
					cno:this.cno,
					status:1
				})
					.then(function(res) {
						console.log(res.data)
						that.videourl = res.data.data.cstatus;
						console.log(that.videourl)
					}).catch(function(err) {
						console.log(err)
					})
					that.$router.push('/Video');
					this.getvideo()
					
			},
			unpass(){
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/updatecinfostatus",{
					cno:this.cno,
					status:0
				})
					.then(function(res) {
						console.log(res.data)
						that.videourl = res.data.data.cstatus;
						console.log(that.videourl)
					}).catch(function(err) {
						console.log(err)
					})
					that.$router.push('/Video');
					this.getvideo()
			}
		},
	}
</script>

<style>
	.el-button {
		margin-top: 20px;
		margin-left: 200px;
	}
</style>
