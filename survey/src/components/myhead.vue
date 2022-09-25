<template>
	<div class="myhead">
		<div class = "logo">
			<img src="../assets/logo1.png" alt="" height="96px">
		</div>
		<h1 class="title">人员管理系统</h1>
		<div class="welcome">
			<el-dropdown @command="handleCommand">
			  <span class="el-dropdown-link">
			    欢迎您: admin<i class="el-icon-arrow-down el-icon--right"></i>
			  </span>
			  <el-dropdown-menu slot="dropdown">
			    <el-dropdown-item command="a">修改密码</el-dropdown-item>
			    <el-dropdown-item command="b">注销</el-dropdown-item>
			  </el-dropdown-menu>
			</el-dropdown>
		</div>
		</div>
</template>

<script>
	export default{
		data(){
			return {
				
			}
		},
		methods:{
			handleCommand(value){
				if(value == 'a'){
					alert("修改密码")
				}else if(value == 'b'){
					alert("注销")
				}
			}
		}
	}
</script>

<style>
	*{
		margin:0;
		padding:0;
	}
	.myhead{
		height:100px;
		background-color:#26292d;
		color:white;
	}
	.logo{
		width:150px;
		padding:0 0 0 35px;
		float:left;
		/* border: 1px solid red; */
		/* box-sizing: border-box; */
	}
	.title{
		width:800px;
		text-align:center;
		height:100px;
		line-height:100px;
		font-size:35px;
		font-family: 楷体;
		font-weight: bolder;
		float:left;
		margin-left: 180px;
		/* border: 1px solid red; */
		
	}
	.welcome{
		text-align: right;
		width:200px;
		height:100px;
		float:right;
		/* border: 1px solid red;
		box-sizing: border-box; */
	}
	 .el-dropdown-link {
	    cursor: pointer;
	    color: #409EFF;
	  }
	  .el-icon-arrow-down {
	    font-size: 12px;
	  }
</style>