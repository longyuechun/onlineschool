<template>
	<div>
		<div>
			<el-row>
				<h1 class="title">稿件管理</h1>
			</el-row>
		</div>
		<div class="box">
			<el-tabs v-model="activeName" @tab-click="handleClick">
				<el-tab-pane label="全部稿件" name="first">
					<videoMessage :videoList="videoList" v-if="videoList != null" />
					<div v-else>空空如也</div>
				</el-tab-pane>
				<el-tab-pane label="进行中" name="second">
					<videoMessage :videoList="videoList" v-if="videoList != null" />
					<div v-else>空空如也</div>
				</el-tab-pane>
				<el-tab-pane label="已通过" name="third">
					<videoMessage :videoList="videoList" v-if="videoList != null" />
					<div v-else>空空如也</div>
				</el-tab-pane>
				<el-tab-pane label="未通过" name="fourth">
					<videoMessage :videoList="videoList" v-if="videoList != null" />
					<div v-else>空空如也</div>
				</el-tab-pane>
			</el-tabs>
		</div>
	</div>
</template>

<script>
	import videoMessage from '../components/videoMessage.vue'
	export default {
		name: 'videoManagement',
		components: {
			videoMessage
		},
		data: function() {
			return {
				searchinput: '',
				activeName: [],
				videoList: []
			}
		},
		methods: {
			handleClick(tab, event) {
				console.log(tab, event);
				console.log(tab.$options)
				console.log(tab.$options.propsData.name)
				if (tab.$options.propsData.name == "first") {
					this.getallvideomessage()
				} else if (tab.$options.propsData.name == "second") {
					this.getupdingvideomessage()
				} else if (tab.$options.propsData.name == "third") {
					this.getupbedvideomessage()
				} else if (tab.$options.propsData.name == "fourth") {
					this.getunpbdvideomessage()
				}

			},
			getallvideomessage() {
				//todo:后台请求全部视频数据
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findrecinfobyuser')
					.then(function(res) {
						console.log("全部的视频数据")
						console.log(res.data.data)
						if (res.data.code == 100)
							return thiz.videoList = null
						else
							thiz.videoList = res.data.data
						console.log("father:" + thiz.videoList.cno)
					})
			},
			getupdingvideomessage() {
				//todo:后台请求正在审核的视频数据
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findrecinfobyuserstatus2').then(function(res) {
					console.log("正在审核")
					console.log(res.data)
					if (res.data.code == 100) {
						console.log(thiz.videoList)
						return thiz.videoList = null
					} else
						thiz.videoList = res.data.data
				})
			},
			getupbedvideomessage() {
				//todo:后台请求已通过审核的视频数据
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findrecinfobyuserstatus1').then(function(res) {
					console.log("已通过")
					console.log(res.data)
					if (res.data.code == 100)
						return thiz.videoList = null
					else
						thiz.videoList = res.data.data
				})
			},
			getunpbdvideomessage() {
				//todo:后台请求未通过审核的视频数据
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/findrecinfobyuserstatus0').then(function(res) {
					console.log("未通过")
					console.log(res.data)
					if (res.data.code == 100)
						return thiz.videoList = null
					else
						thiz.videoList = res.data.data
				})
			}
		}
	}
</script>

<style scoped="scoped">
	.box {
		border-color: #F98A29;
		margin-top: 2%;
		margin-left: 1%;
	}

	.title {
		margin-left: 10%;
		text-align: left;
	}
</style>
