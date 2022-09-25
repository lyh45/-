<template>
	<div >
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			
			<el-form-item label="调查内容">
				<el-input clearable v-model="selectForm.surveyText" placeholder="请输入调查内容"></el-input>
			</el-form-item> 

			<el-form-item>
				<el-button type="primary" @click="selectSurvey()">查询</el-button>
				<el-button type="success" @click="dialogFormVisible = true;survey={}">新增</el-button>
			</el-form-item>
		</el-form>
		<!-- 多条件分页调查列表 -->
		<el-table :data="surveyList" stripe style="width: 1200px">
			<!-- 展开调查 -->
			 <el-table-column type="expand">
			      <template slot-scope="props">
			        <el-form label-position="left" inline class="demo-table-expand">
			          <el-form-item label="调查项">
			            <span>1.发烧</span>
						<span>2.不发烧</span>
						<span>3.你猜</span>
			          </el-form-item>
			          
			        </el-form>
			      </template>
			    </el-table-column> 
				
			<el-table-column label="序号" type="index" :index="getIndex" width="80" align="center">
			</el-table-column>
			<el-table-column prop="surveyText" label="调查内容" align="center">
			</el-table-column>
			<el-table-column prop="score" label="分值" width="80" align="center">
				<!-- 插槽取值 -->
				<!-- <template slot-scope="scope">
					<el-tag v-if="scope.row.gender==1">男</el-tag>
					<el-tag v-if="scope.row.gender==0" type="danger">女</el-tag>
				</template> -->
			</el-table-column>
			<!-- 0未启动 1启动 -->
			<el-table-column label="是否启动" width="100px" align="center">
			<template slot-scope="scope">
				<el-switch
				  v-model="scope.row.checked"
				  :active-value="1"
				  :inactive-value="0"
				  active-color="#13ce66"
				  inactive-color="#90918b">
				</el-switch>
			</template>
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" width="100px" align="center">
			</el-table-column>
			
			<el-table-column label="操作" width="200px" align="center">
				<template slot-scope="scope">
					
				<el-button type="warning" @click="dialogFormVisible = true;survey={...scope.row};">修改</el-button>
				
				<el-popconfirm
				  @confirm="deleteSurvey(scope.row.id)"
				  confirm-button-text='确定'
				  cancel-button-text='取消'
				  confirm-button-type = 'danger'
				  cancel-button-type ='success'
				  icon="el-icon-info"
				  icon-color="red"
				  :title="'确定要删除['+scope.row.surveyText+']吗？'"
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
			<el-dialog :title="survey.id?'修改':'新增'" :visible.sync="dialogFormVisible">
			  <el-form :model="survey">
						<el-form-item label="调查内容" :label-width="formLabelWidth">
						  <el-input v-model="survey.surveyText" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item label="分值" :label-width="formLabelWidth">
						  <el-input v-model="survey.score" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item label="是否启动" :label-width="formLabelWidth">
						  <el-input v-model="survey.checked" autocomplete="off"></el-input>
						</el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="editSurvey()">确 定</el-button>
			  </div>
			</el-dialog>
			<!-- 新增修改对话框 -->
			
			
	</div>
</template>

<script>
	import surveyApi from '../api/surveyApi.js'
	
	export default {
		data() {
			return {
				formLabelWidth:"80px",
				
				survey:{}, //新增修改
				dialogFormVisible:false,//显示对话框
				total:0,
				surveyList:[] ,

				selectForm: {
					page:1,
					size:5
				}
			}
		},
		created() {
			this.selectSurvey();
		},
		methods:{
			deleteSurvey(id){
				surveyApi.delete(id)
				.then(({data})=>{
					this.$message({
					         message: '删除成功',
					         type: 'success',
							 duration:1000
					       });
						   this.selectSurvey();
				})
			},
			
			editSurvey(){
				//判断是新增还是修改
				if(this.survey.id){//修改
					surveyApi.update(this.survey)
					.then(({data})=>{
						this.$message({
						         message: '修改成功',
						         type: 'success',
								 duration:1000
						       });
							   this.selectSurvey();
					})
				}else{//新增
					surveyApi.insert(this.survey)
					.then(({data})=>{
						this.$message({
						         message: '新增成功',
						         type: 'success',
								 duration:1000
						       });
							   this.selectSurvey();
					})
				}
				this.dialogFormVisible = false;
			},
			getIndex(value){
				return (this.selectForm.page - 1)*this.selectForm.size + value + 1;
			},
			
			handleSizeChange(value){
				this.selectForm.size = value;
				this.selectSurvey();
			},
			handleCurrentChange(value){
				this.selectForm.page = value;
				this.selectSurvey();
			},
			selectSurvey(){
				surveyApi.list(this.selectForm)
				.then(({data})=>{
					// console.log(data)
					this.surveyList = data.data.list
					this.total = data.data.total;
				}) //等同于resp.data
			}
		}
	}
</script>

<style>
</style>
