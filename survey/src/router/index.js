//路由配置
import Vue from 'vue'
import VueRouter from 'vue-router'
//导入组件
import login from '../components/login.vue'
//导入子组件在父组件上方
import grade from '../components/grade.vue'
import student from '../components/student.vue'
import survey from '../components/survey.vue'
import teacher from '../components/teacher.vue'
import clazz from '../components/clazz.vue'

import layout from '../components/layout.vue'

Vue.use(VueRouter)

export default new VueRouter({
	routes:[
		{
			path:'/',
			redirect:"/manager/login"
		},
		{//登录
			path:'/manager/login',
			component:login
		},
		{//主页
			path:'/manager/home',
			component:layout,
			//嵌套路由
			children:[
				{
					path:"/",
					redirect:"/grade/home"
				},
				{
					path:"/grade/home",
					component:grade
				},
				{
					path:"/clazz/home",
					component:clazz
				},
				{
					path:"/teacher/home",
					component:teacher
				},
				{
					path:"/student/home",
					component:student
				},
				{
					path:"/survey/home",
					component:survey
				},
			]
		},
		
	]
})