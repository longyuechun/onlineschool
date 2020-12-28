<template>
	<div class="navbar">
		<el-menu :default-active="activeIndex" class="el-menu-navbar" mode="horizontal" @select="handleSelect" active-text-color="#f98a29">
			<el-menu-item index="0">首页</el-menu-item>
			<el-menu-item index="1" @click="gopost">投搞</el-menu-item>
			<el-submenu index="2" >
				<template slot="title">分类</template>
				<el-menu-item v-for="item in List" @click="gocategory(item.category)">{{item.category}}</el-menu-item>
			</el-submenu>
			<!--未登录-->
			<el-menu-item index="3" v-if="perInfo.upic == null" @click="gologin()">登陆/注册</el-menu-item>
			<!--已登录-->
			<el-menu-item index="3" v-else  @click="selfInfo()">
				<el-avatar  :src="perInfo.upic" style="height: 40px;border-radius: 50%" />
			</el-menu-item>


		</el-menu>
	</div>
</template>

<script>
	export default {
		name: 'navbar',
		data() {
			return {
				uploadurl: 'upload/videoupload',
				activeIndex: '0',
				perInfo: [],
				loginFromVisible: false,
				postVisible: false,
				formLabelWidth: '120px',
				List: [
					{category: "基础医学"},{category: "医学影像学"},
					{category: "临床医学"},{category: "口腔医学"},{category: "基础医学"},
					{category:"运动康复学"},{category: "药学"}
				]
			}
		},
		mounted() {
			console.log(this.headpic)
		},
		beforeMount() {
			this.getUserId()
			console.log()
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			gocategory(catename){
				
				this.$router.push({
					path: 'category',
					query: {
						data: catename
					}
				});
			},
			gologin(){
				this.$router.push('login')
			},
			getUserId() {
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/finduinfobyuid')
				.then(function(res){
					console.log(res.data)
					thiz.perInfo = res.data.data
					console.log("头像"+thiz.perInfo.upic)
				}).catch(function(err){
					console.log(err)
					thiz.headpic = null
				})
			},
			gopost(){
				console.log(window.location.href);
				window.open(window.location.href+this.uploadurl); 
			},
			selfInfo() {
				this.$router.push('/selfInfo');
			},
			login(form){
				form = this.form
				var thiz = this
				this.$axios.post('http://localhost:8090/onlineschool/login',{
					uEmail: form.name,
					uPassword: form.password
				})
				.then(function(res){
					console.log(res.data)
					if(res.data.code == 0){
						thiz.$message({
							message: '登陆成功！',
							type: 'success'
						})
					}else{
						thiz.$message({
							message: '登陆失败请重试！',
							type: 'error'
						})
					}
						
				})
			}
		}
	}
</script>

<style>
	.item {
		width: 80%;
		margin-left: 20%;
		text-align: left;
	}

	.navbar {
		width: auto;
		height: 100%;
		margin-left: 1.875rem;
	}

	
</style>
