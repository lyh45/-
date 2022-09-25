<template>
	<div >
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			<el-form-item label="编号">
				<el-input clearable v-model="selectForm.id" placeholder="请输入老师编号"></el-input>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input clearable v-model="selectForm.teacherName" placeholder="请输入老师姓名"></el-input>
			</el-form-item> 

			<el-form-item label="状态">
				<el-select clearable v-model="selectForm.status" placeholder="请选择老师状态">
					<el-option v-for="item in statusList" :key="item.id" :label="item.text" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="selectTeacher()">查询</el-button>
				<el-button type="success" @click="dialogFormVisible = true;teacher={}">新增</el-button>
			</el-form-item>
		</el-form>
		<!-- 多条件分页查询老师 -->
		<el-table :data="teacherList" stripe style="width: 1200px">
			
			<el-table-column label="序号" type="index" :index="getIndex" width="80">
			</el-table-column>
			<el-table-column prop="teacherName" label="姓名" width="180">
			</el-table-column>
			<el-table-column prop="gender" label="性别" width="180">
				<!-- 插槽取值 -->
				<template slot-scope="scope">
					<el-tag v-if="scope.row.gender==1">男</el-tag>
					<el-tag v-if="scope.row.gender==0" type="danger">女</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="bornday" label="出生日期">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间">
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.status==1">正常</el-tag>
					<el-tag v-if="scope.row.status==2" type="success">休假</el-tag>
					<el-tag v-if="scope.row.status==3" type="warning">离职</el-tag>
					<el-tag v-if="scope.row.status==4" type="danger">退休</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="level" label="权限级别">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.level==0">管理员</el-tag>
					<el-tag v-if="scope.row.level==1" type="success">老师</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="phonenumber" label="电话">
			</el-table-column>
			<el-table-column label="操作" width="200px">
				<template slot-scope="scope">
					
				<el-button type="warning" @click="dialogFormVisible = true;teacher={...scope.row};">修改</el-button>
				
				<el-popconfirm
				  @confirm="deleteTeacher(scope.row.id)"
				  confirm-button-text='确定'
				  cancel-button-text='取消'
				  confirm-button-type = 'danger'
				  cancel-button-type ='success'
				  icon="el-icon-info"
				  icon-color="red"
				  :title="'确定要删除['+scope.row.teacherName+']老师吗？'"
				>
				  <el-button slot="reference" type="danger">删除</el-button>
				</el-popconfirm>
				
				</template>
				
				
				
			</el-table-column>
		</el-table>
		<!-- 分页 -->
		<el-pagination
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		      :current-page="selectForm.page"
		      :page-sizes="[5, 10, 20, 50]"
		      :page-size="selectForm.size"
		      layout="total, sizes, prev, pager, next, jumper"
		      :total="total">
		    </el-pagination>
			
			<!-- 新增修改对话框 -->
			<el-dialog :title="teacher.id?'修改':'新增'" :visible.sync="dialogFormVisible">
			  <el-form :model="teacher">
			    <el-form-item label="姓名" :label-width="formLabelWidth">
			      <el-input v-model="teacher.teacherName" autocomplete="off"></el-input>
			    </el-form-item>
				<el-form-item label="性别" :label-width="formLabelWidth">
				  <!-- <el-input v-model="teacher.gender" autocomplete="off"></el-input> -->
				  <el-radio v-model="teacher.gender" label="1">男</el-radio>
				  <el-radio v-model="teacher.gender" label="0">女</el-radio>
				</el-form-item>
				<el-form-item label="手机号" :label-width="formLabelWidth">
				  <el-input v-model="teacher.phonenumber" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="生日" :label-width="formLabelWidth">
				  <el-input v-model="teacher.bornday" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="密码 " :label-width="formLabelWidth">
				  <el-input v-model="teacher.password" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="状态 " :label-width="formLabelWidth">
				  <el-input v-model="teacher.status" autocomplete="off"></el-input>
				</el-form-item>
				
			    <!-- <el-form-item label="活动区域" :label-width="formLabelWidth">
			      <el-select v-model="form.region" placeholder="请选择活动区域">
			        <el-option label="区域一" value="shanghai"></el-option>
			        <el-option label="区域二" value="beijing"></el-option>
			      </el-select>
			    </el-form-item> -->
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="editTeacher()">确 定</el-button>
			  </div>
			</el-dialog>
			
	</div>
</template>

<script>
	import teacherApi from '../api/teacherApi.js'
	
	export default {
		data() {
			return {
				formLabelWidth:"80px",
				
				teacher:{}, //新增修改
				dialogFormVisible:false,//显示对话框
				total:0,
				teacherList:[] ,
				statusList: [{
						id: 1,
						text: "正常"
					},
					{
						id: 2,
						text: "休假"
					},
					{
						id: 3,
						text: "离职"
					},
					{
						id: 4,
						text: "退休"
					}

				],

				selectForm: {
					page:1,
					size:5
				}
			}
		},
		created() {
			this.selectTeacher();
		},
		methods:{
			deleteTeacher(id){
				teacherApi.delete(id)
				.then(({data})=>{
					this.$message({
					         message: '删除成功',
					         type: 'success',
							 duration:1000
					       });
						   this.selectTeacher();
				})
			},
			
			editTeacher(){
				//判断是新增还是修改
				if(this.teacher.id){//修改
					teacherApi.update(this.teacher)
					.then(({data})=>{
						this.$message({
						         message: '修改成功',
						         type: 'success',
								 duration:1000
						       });
							   this.selectTeacher();
					})
				}else{//新增
					teacherApi.insert(this.teacher)
					.then(({data})=>{
						this.$message({
						         message: '新增成功',
						         type: 'success',
								 duration:1000
						       });
							   this.selectTeacher();
					})
				}
				this.dialogFormVisible = false;
			},
			getIndex(value){
				return (this.selectForm.page - 1)*this.selectForm.size + value + 1;
			},
			
			handleSizeChange(value){
				this.selectForm.size = value;
				this.selectTeacher();
			},
			handleCurrentChange(value){
				this.selectForm.page = value;
				this.selectTeacher();
			},
			selectTeacher(){
				teacherApi.list(this.selectForm)
				.then(({data})=>{
					// console.log(data)
					this.teacherList = data.data.list
					this.total = data.data.total;
				}) //等同于resp.data
			}
		}
	}
</script>

<style>
</style>
