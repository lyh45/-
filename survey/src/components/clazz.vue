<template>
	<div>
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			<el-form-item label="班级">
				<el-input clearable v-model="selectForm.className" placeholder="请输入班级名称"></el-input>
			</el-form-item>
			<el-form-item label="状态">
				<el-select clearable v-model="selectForm.status" placeholder="请选择班级状态">
					<el-option v-for="item in statusList" :key="item.id" :label="item.text" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="年级">
				<el-select clearable v-model="selectForm.gradeId" placeholder="请选择年级">
					<el-option v-for="item in gradeList" :key="item.id" :label="item.gradeName" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			
			<el-form-item>
				<el-button type="primary" @click="selectClazz">查询</el-button>
			</el-form-item>
		</el-form>
		
		<el-table :data="clazzList" stripe style="width: 1300px">
			
			<el-table-column label="序号" type="index" :index="getIndex" width="80">
			</el-table-column>
			<el-table-column prop="className" label="班级名称" width="180">
			</el-table-column>
			<el-table-column prop="gradeName" label="年级" width="80">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" width="180">
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.status==1">正常</el-tag>
					<el-tag v-if="scope.row.status==2" type="success">实习</el-tag>
					<el-tag v-if="scope.row.status==3" type="warning">毕业</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="count" label="班级人数">
			</el-table-column>
			
			<el-table-column label="带班老师" width="200px">
				<template slot-scope="scope">
					<!-- 移除班级id老师id -->
					<el-tag
					  v-for="item in scope.row.teacherList"
					  :key="item.id"
					  @close="removeTeacher(scope.row.id,item.id)"
					  closable
					  type="danger">
					  {{item.teacherName}}
					</el-tag>
				</template>
			</el-table-column>
			
			<el-table-column label="操作" width="350px">
				<template slot-scope="scope">
				<el-button type="primary" @click="showTeacher(scope.row.id)">设置带班老师</el-button>
				
				<el-button type="warning" >修改</el-button>
				<el-button type="danger">删除</el-button>
				</template>
				
				
				
			</el-table-column>
		</el-table>
		<el-pagination
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		      :current-page="selectForm.page"
		      :page-sizes="[5, 10, 20, 50]"
		      :page-size="selectForm.size"
		      layout="total, sizes, prev, pager, next, jumper"
		      :total="total">
		    </el-pagination>
			<!-- 设置带班老师弹框 -->
			 <el-dialog title="添加带班老师" :visible.sync="dialogTableVisible">
			   <el-table :data="teacherList" row-key="id" @selection-change="handleSelectionChange">
				<el-table-column
					:reserve-selection="true"
				    type="selection"
				    width="55">
				</el-table-column>
			     <el-table-column type="index" :index="getIndex1" label="序号" width="150"></el-table-column>
			     <el-table-column property="teacherName" label="老师姓名" width="150"></el-table-column>
				 <el-table-column property="phonenumber" label="手机号" width="200"></el-table-column>
			     <el-table-column property="gender" label="性别">
					 <template slot-scope="scope">
					 	<el-tag v-if="scope.row.gender==1">男</el-tag>
					 	<el-tag v-if="scope.row.gender==0" type="danger">女</el-tag>
					 </template>
				 </el-table-column>
			   </el-table>
			   <el-pagination
			         @size-change="handleSizeChangeTeacher"
			         @current-change="handleCurrentChangeTeacher"
			         :current-page="teacherForm.page"
			         :page-sizes="[5, 10, 20, 50]"
			         :page-size="teacherForm.size"
			         layout="total, sizes, prev, pager, next, jumper"
			         :total="teacherForm.total">
			       </el-pagination>
			    <div style="text-align: right;">
					<el-button type="info" @click="dialogTableVisible = false">取消</el-button>
					<el-button type="primary" @click="addTeacher()" >确定</el-button>
				</div>
				   
			 </el-dialog>
	</div>
</template>

<script>
	import clazzApi from '../api/clazzApi.js'
	import gradeApi from '../api/gradeApi.js'
	import clazzTeacherApi from '../api/clazzTeacherApi.js'
	import teacherApi from '../api/teacherApi.js'
	export default {
		data() {
			return {
				checkTeacher:[],
				teacherForm:{
					page:1,
					size:5,
					total:0
				},
				teacherList:[],
				dialogTableVisible:false,
				clazz:{}, //新增修改
				
				total:0,
				clazzList:[] ,
				gradeList:[],	
				statusList: [{
						id: 1,
						text: "正常"
					},
					{
						id: 2,
						text: "实习"
					},
					{
						id: 3,
						text: "毕业"
					}
				],
				
				selectForm: {
					page:1,
					size:5
				}
				}
				
				},
				created() {
					this.selectClazz();
					this.selectGrade();
				},
				
				methods:{
					addTeacher(){//添加带班老师，cid多个老师tid
					// 获取班级和多个老师id集合
						let clazzTeacherIdList = this.checkTeacher.map(t=>{
							return {
								classId: this.teacherForm.cid,
								teacherId: t.id
							}
						})
						clazzTeacherApi.insert(clazzTeacherIdList)
						.then(({data})=>{
							this.$message({
								         message: '添加老师成功',
								         type: 'success',
										 duration:1000
							});
							this.selectClazz();
							this.dialogTableVisible = false;
						})
						
					},
					
					handleSelectionChange(value){
						this.checkTeacher = value;
					},
					
					handleSizeChangeTeacher(value){
						this.teacherForm.size = value;
						this.selectTeacher();
					},
					handleCurrentChangeTeacher(value){
						this.teacherForm.page = value;
						this.selectTeacher();
					},
					selectTeacher(){
						teacherApi.list(this.teacherForm)
						.then(({data})=>{
							// console.log(data.data)
							this.teacherList = data.data.list;
							this.teacherForm.total = data.data.total;
							
						})
					},
					
					getIndex1(value){
						return (this.teacherForm.page - 1) * this.teacherForm.size + value + 1;
					},
					showTeacher(cid){
						this.dialogTableVisible = true;
						this.teacherForm.cid = cid;//把班级id封装
						this.selectTeacher();
					},
					
					removeTeacher(cid,tid){//移除带班老师
						// console.lop(cid+tid);
						clazzTeacherApi.removeTeacher(cid,tid)
						.then(({data})=>{
							if(data.data){
								this.$message({
								         message: '移除成功',
								         type: 'success',
										 duration:1000
							});
							this.selectClazz();
						}
						
					})
					},
					
					handleSizeChange(value){
						this.selectForm.size = value;
						this.selectClazz();
					},
					handleCurrentChange(value){
						this.selectForm.page = value;
						this.selectClazz();
					},
					selectGrade(){
						gradeApi.gradeList()
						.then(({data})=>{
							if(data.data){
								this.gradeList = data.data;
							}
						})
					},
					getIndex(value){
						return (this.selectForm.page - 1)*this.selectForm.size + value + 1;
					},
					selectClazz(){
						clazzApi.list(this.selectForm)
						.then(({data})=>{
							// console.log(data)
							this.clazzList = data.data.list
							this.total = data.data.total;
						}) //等同于resp.data
					}
				}
				}
</script>

<style>
</style>