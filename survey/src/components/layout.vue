<template>
	<div style='height: 100%;'>
		<myhead></myhead>
			<mynav></mynav>
			<myhome></myhome>
	</div>
</template>

<script>
	// 导入子组件
	import myhead from './myhead.vue'
	import mynav from './mynav.vue'
	import myhome from './myhome.vue'
	
	export default{
		components:{
			myhead,
			mynav,
			myhome
		}
	}
	
</script>

<style>
</style>