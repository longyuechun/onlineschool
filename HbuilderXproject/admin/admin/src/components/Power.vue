<template>
	<div>
		<el-card>
			<el-row :gutter="40">
				<el-col :span="10">
					<el-input placeholder="请输入内容" v-model="input">
						<el-button slot="append" icon="el-icon-search" @click="getadminbyano(input)"></el-button>
					</el-input>
				</el-col>
				<el-col :span="4">
					<el-button type="primary" @click="addDialogVisible=true">添加管理员</el-button>
				</el-col>
			</el-row>
		</el-card>
		<!-- 表格 -->
		<el-table :data="adminList" border style="width: 100%">
			<el-table-column fixed prop="ano" label="工号" width="100">
			</el-table-column>
			<el-table-column prop="agrade" label="等级" width="100">
			</el-table-column>
			<el-table-column prop="aphonenum" label="联系电话" width="200">
			</el-table-column>
			<el-table-column prop="asex" label="性别" width="100">
			</el-table-column>
			<el-table-column prop="aemail" label="邮箱" width="240">
			</el-table-column>
			<el-table-column prop="aage" label="年龄" width="100">
			</el-table-column>

			<el-table-column fixed="right" label="操作" width="100">
				<template slot-scope="scope">
					<el-button icon="el-icon-edit" type="primary" size="small" circle @click="updateDialog(scope.row.ano)"></el-button>
					<el-button type="danger" size="small" icon="el-icon-delete" circle @click="deleteadmin(scope.row.ano)"></el-button>
				</template>
			</el-table-column>
		</el-table>
		<!-- 添加管理员 -->
		<el-dialog title="添加管理员信息" :visible.sync="addDialogVisible" @close="resetField">
			<el-form :model="addForm" ref="addFormRef">
				<el-form-item label="工号" :label-width="formLabelWidth">
					<el-input v-model="addForm.ano" style="width: 350px;"></el-input>
				</el-form-item>
				<el-form-item label="密码" :label-width="formLabelWidth">
					<el-input v-model="addForm.apassword" style="width: 350px;"></el-input>
				</el-form-item>
				<el-form-item label="等级" :label-width="formLabelWidth">
					<el-select v-model="addForm.agrade" placeholder="请选择等级">
						<el-option label="管理员" value="管理员"></el-option>
						<el-option label="超级管理员" value="超级管理员"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="联系电话" :label-width="formLabelWidth">
					<el-input v-model="addForm.aphonenum" style="width: 350px;"></el-input>
				</el-form-item>
				<el-form-item label="性别" :label-width="formLabelWidth">
					<el-select v-model="addForm.asex" placeholder="请选择性别">
						<el-option label="男" value="男"></el-option>
						<el-option label="女" value="女"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="邮箱" :label-width="formLabelWidth">
					<el-input v-model="addForm.aemail" style="width: 350px;"></el-input>
				</el-form-item>
				<el-form-item label="年龄" :label-width="formLabelWidth">
					<el-input v-model="addForm.aage" style="width: 350px;"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="addDialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="addAdmin">确 定</el-button>
			</div>
		</el-dialog>
		<!-- 修改信息 -->
		<el-dialog title="修改个人信息" :visible.sync="updateDialogVisible">
			<el-form :model="updateForm" ref="updateFormRef">
				<el-form-item label="工号" :label-width="formLabelWidth">
					<el-input v-model="updateForm.ano" disabled></el-input>
				</el-form-item>
				<el-form-item label="密码" :label-width="formLabelWidth">
					<el-input v-model="updateForm.apassword" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="等级" :label-width="formLabelWidth">
					<el-select v-model="updateForm.agrade" placeholder="请选择等级">
						<el-option label="管理员" value="管理员"></el-option>
						<el-option label="超级管理员" value="超级管理员"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="联系电话" :label-width="formLabelWidth">
					<el-input v-model="updateForm.aphonenum" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="性别" :label-width="formLabelWidth">
					<el-select v-model="updateForm.asex" placeholder="请选择性别">
						<el-option label="男" value="男"></el-option>
						<el-option label="女" value="女"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="邮箱" :label-width="formLabelWidth">
					<el-input v-model="updateForm.aemail" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="年龄" :label-width="formLabelWidth">
					<el-input v-model="updateForm.aage" autocomplete="off" ></el-input>
				</el-form-item>
				
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="updateDialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="editadmin">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		methods: {
			//监听添加表单关闭事件
			resetField() {
				this.$refs.addFormRef.resetFields();
			},
			//table中的数据获取
			getadmin() {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/findallainfo")
					.then(function(res) {
						console.log(res.data)
						that.adminList = res.data.data
					}).catch(function(err) {
						console.log(err)
					})
			},
			//添加管理员
			addAdmin() {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/addainfo", this.addForm)
					.then(function(res) {
						console.log(res)
						that.getadmin();
					})
					.catch(function(error) {
						console.log(error)
					});
				this.addDialogVisible = false;
			},
			//根据ano查询管理员信息
			getadminbyano(input) {
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/findainfobyno", {
						ano: input
					})
					.then(function(res) {
						console.log(res.data)
						that.adminList = res.data.data
					}).catch(function(err) {
						console.log(err)
					});
			},
			//删除管理员
			deleteadmin(ano) {
				console.log(ano)
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/deleteainfobyano", {
						ano: ano
					})
					.then(function(res) {
						console.log(res)
						that.getadmin()
					})
					.catch(function(error) {
						console.log(error)
					})
			},
			//修改管理员表单
			updateDialog(ano){
				this.updateDialogVisible = true;
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/findainfobyno", {
						ano: ano
					})
					.then(function(res) {
						console.log(res.data)
						that.updateForm = res.data.data[0]
					}).catch(function(err) {
						console.log(err)
					});
			},
			//提交修改
			editadmin(){
				var that = this
				this.$axios.post("http://localhost:8090/onlineschool/updateainfo",this.updateForm)
					.then(function(res) {
						console.log(res)
						that.getadmin();
						that.updateDialogVisible = false;
					})
					.catch(function(error) {
						console.log(error)
					});
			}
		},
		mounted() {
			console.log("mounted")
			this.getadmin();
		},
		data() {
			return {
				input: '',
				addDialogVisible: false,
				updateDialogVisible: false,
				adminList: [],
				addForm: {
					ano: '',
					apassword: '',
					agrade: '',
					aphonenum: '',
					asex: '',
					aemail: '',
					aage: '',
				},
				updateForm: {},
				formLabelWidth: '120px'
			};
		}
	}
</script>

<style>
</style>
