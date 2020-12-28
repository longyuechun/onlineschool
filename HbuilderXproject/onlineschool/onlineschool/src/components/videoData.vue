<template>
	<div>
		<el-row>
			<div class="title"><h1>数据中心</h1></div>
			<el-divider/>
		</el-row>
		<el-row class="innerrow">
			<el-col :span="7" v-for="(item) in dataList" :key="item.name" :offset="1">
				<el-card class="card" shadow="hover">
					<div>{{item.name}}</div>
					<h1>{{item.num}}</h1>
				</el-card></el-col>
		</el-row>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				current_page: 1,
				total: null,
				pagesize : 8,
				dataList: [{
					"name": "点赞量",
					"num": 20,
				},{
					"name" : "浏览量",
					"num": 30
					},{
						"name": "评论量",
						"num": 1
					}
				]
			}
		},
		methods:{
			// getdataList(){
			// 	this.$axios.get('',{
			// 		limit:this.pagesize,
			// 		now_page:this.current_page
			// 	}).then(function(response){
			// 		var that = this;
			// 		that.dataList = response.data
			// 	}).catch(function(error){
			// 		console.log(error)
			// 	})
			// }
		}
	}
</script>

<style scoped="scoped">
	.innerrow{
		margin: 20px;
	}
	.title{
		text-align: left;
		margin-left: 10%;
	}
	.card{
		width: 60%;
		height: 80%;
		margin: 1.25rem;
		background-color: #EBF8FC;
		text-align: left;
	}
</style>
