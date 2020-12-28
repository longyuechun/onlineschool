<template>
	<div>
		<div v-for="item in videoList" :key="item.release_id.id" class="message">
			<el-row >
				<el-col :span="4"><img :src='item.cpic' style="width: 60%; height: 100%;"/></el-col>
				<el-col :span="20" >
					<el-row class="innerrow" style="margin-top: 0rem;">
						<span>{{item.cname}}</span>
					</el-row>
					<el-row class="innerrow">
						<span>{{item.release_id.rtime}}</span>
						<el-divider direction="vertical"/>
						<i v-if="item.cstatus === 1" class="el-icon-circle-check">已发布</i>
						<i v-else-if="item.cstatus === 0" class="el-icon-circle-close">未通过</i>
						<i v-else-if="item.cstatus ===2" class="el-icon-more-outline">审核中</i>
					</el-row>
					<el-row class="innerrow">
						<el-col :span="8">
							<i class="el-icon-view">浏览量:{{item.cviewInfo_id.cpageView}}</i>
						</el-col>
						<!-- <el-col  :span="8">
							<i class="el-icon-chat-dot-round">评论量:{{item.cviewInfo_id.ccomment}}</i>
						</el-col> -->
						<el-col :span="8">
							<i class="el-icon-check">点赞量:{{item.cviewInfo_id.clike}}</i>
						</el-col>
					</el-row>
				</el-col>
			</el-row>
		<el-divider></el-divider>
		</div>
	</div>
</template>

<script>
	export default{
		name: 'videoMessage',
		props: [
			'videoList',
		],
		data(){
			return {
			}
		},
		beforeMount() {
			// console.log("created:"+this.videoList)
			
		},
		methods:{
			
		}
	}
</script>

<style scoped="scoped">
	.message{
		margin-top: 5%;
	}
	.innerrow{
		text-align: left;
		margin-right: 50%;
		margin-top: 1.25rem;
	}
	.butcol{
		margin-top: 2.8125rem;
	}
</style>
