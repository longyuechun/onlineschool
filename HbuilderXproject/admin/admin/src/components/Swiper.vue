<template>
	<div>
		<el-upload class="upload-demo" drag :on-success="handlesuccess" action="http://localhost:8090/onlineschool/swiperupload"
		 multiple>
			<i class="el-icon-upload"></i>
			<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
			<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
		</el-upload>
		</br>
		<el-input style="width: 350px;" v-model="schain"></el-input>
		<el-button type="primary" style="margin-left: 20px;" @click="addswiper(schain)">上传<i class="el-icon-upload el-icon--right"></i></el-button>
		<el-table :data="swiperList" border style="width: 100%; margin-top: 20px;">
			<el-table-column fixed prop="sid" label="图片编号" width="50">
			</el-table-column>
			<el-table-column fixed prop="spic" label="图片" width="600">
				<template slot-scope="scope">
					<img :src="scope.row.spic" style="height: 100px" />
				</template>
			</el-table-column>
			<el-table-column fixed prop="schain" label="图片链接" width="250">
			</el-table-column>
			<el-table-column fixed="right" label="删除图片" width="100">
				<template slot-scope="scope">
					<el-button type="danger" size="small" icon="el-icon-delete" circle @click="deleteswiper(scope.row.sid)"></el-button>
				</template>
			</el-table-column>
		</el-table>

	</div>

</template>

<script>
	export default {
		data() {
			return {
				swiperList: [],
				schain: '',
			};

		},
		mounted() {
			console.log("mounted")
			this.getswiper();
		},
		methods: {
			getswiper() {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/findallswiper")
					.then(function(res) {
						console.log(res.data)
						that.swiperList = res.data.data
					}).catch(function(err) {
						console.log(err)
					})
			},
			deleteswiper(sid) {
				console.log(sid)
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/deleteswiperbyid", {
						id: sid
					})
					.then(function(res) {
						console.log(res)
						that.getswiper()
					})
					.catch(function(error) {
						console.log(error)
					})
			},
			handlesuccess(response, file, fileList) {
				console.log("success")
				console.log(response)
				this.spic = response.data
				console.log(this.spic)
			},
			addswiper(schain) {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/addswiper", {
						schain: schain,
						spic: this.spic
					})
					.then(function(res) {
						console.log(res.data)
						that.swiperList = res.data.data
						that.getswiper();
					}).catch(function(err) {
						console.log(err)
					});
			},

		}
	}
</script>

<style>
</style>
