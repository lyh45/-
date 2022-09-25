import http from '../utils/request.js'

export default{
	insert(clazzTeacherIdList){
		return http.post("/clazzteacher/insert",clazzTeacherIdList)
	},
	
	removeTeacher(cid,tid){
		return http.delete("/clazzteacher/delete",{
			params:{
				cid,
				tid
			}
		})
	}
}