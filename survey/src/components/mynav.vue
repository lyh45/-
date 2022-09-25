<template>
	<div class="mynav">
		 <el-menu
		      :router='true'
		      :default-active="defaultActive"
		      class="el-menu-vertical-demo"
		      background-color="#545c64"
		      text-color="#fff"
		      active-text-color="#ffd04b">
		      
		        <el-menu-item index="/grade/home">
		          <i class="el-icon-info"></i>
		          <span slot="title">年级管理</span>
		        </el-menu-item>
		      
		      <el-menu-item index="/clazz/home">
		        <i class="el-icon-star-off
"></i>
		        <span slot="title">班级管理</span>
		      </el-menu-item>
		      <el-menu-item index="/teacher/home" >
		        <i class="el-icon-s-custom"></i>
		        <span slot="title">老师管理</span>
		      </el-menu-item>
			  <el-menu-item index="/student/home">
			    <i class="el-icon-user-solid"></i>
			    <span slot="title">学生管理</span>
			  </el-menu-item>
		      <el-menu-item index="/survey/home">
		        <i class="el-icon-search"></i>
		        <span slot="title">调查管理</span>
		      </el-menu-item>
		    </el-menu>
		</div>
</template>

<script>
	export default {
		data(){
			return {
				defaultActive:"/grade/home"
			}
		},
		created() { //狗子函数,页面加载时自动执行
			this.defaultActive = location.hash.substring(1);
			
		}
	}
</script>

<style>
	.mynav{
		width:150px;
		/* sass:总高度-头部 */
		height:calc( 100% - 100px );
		background-color: #545c64;
		float:left;
	}
</style>