<template>
	<div class="login">
		<!-- <h1>登录</h1> -->
		<el-form ref="loginForm" :rules="rules" :model="manager" label-width="">
			
		  <el-form-item label="" prop = "username">
		    <el-input v-model="manager.username" placeholder="请输入用户名"></el-input>
		  </el-form-item>
		    <el-form-item label="" prop = "password">
		      <el-input v-model="manager.password" placeholder="请输入密码"></el-input>
		    </el-form-item>
		  <el-form-item style="text-align: center;">
		    <el-button type="primary" @click="login()">登录</el-button>
		    <el-button>取消</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script>
	import managerApi from '../api/managerApi.js'
	
	export default {//默认导出
		data(){
			return {
				rules:{//验证规则
					username:[
						{required:true , message:"请输入用户名", trigger:"blur"}
					],
					password:[
						{required:true , message:"请输入密码", trigger:"blur"},
						{min:3, max:6,message:"长度在3 到 6个字符", trigger:"blur"}
					]
				},
				manager:{}
			}
		},
		methods:{//函数（方法）
			login(){
				// this.$refs["loginForm"].validate((valid)=>{
				// 	console.log(valid);
				// })
				this.$refs.loginForm.validate()
				.then((resp)=>{
					// console.log(resp)
					if(resp){
						managerApi.login(this.manager.username,this.manager.password)
						.then(resp=>{
							// console.log(resp);
							if(resp.data.data){
								
								  this.$message({
								           message: '登录成功',
								           type: 'success',
										   duration:1000
								         });
										 
										 let manager1 = resp.data.data
										 this.$cookie.set("token-survey1905",manager1.token);
										 this.$cookie.set("username-survey1905",manager1.username);
										 this.$router.push({
											 path:'/manager/home'
										 })
							}else{
								this.$message({
								         message: '登录失败',
								         type: 'error',
										 duration:1000
								       });
							}
							
						})
					}
				})
			}
		}
	}
</script>

<style scoped>
	.login{
		background-image: url("../assets/1161810.jpg");
		background-size: cover;		
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.el-form{
		width: 300px;
		height: 180px;
		/* 半透明白色 */
		background-color:rgba(255,255,255,0.5);
		border-radius: 20px;
		padding:50px;
		
	}
	
</style>