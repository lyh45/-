import http from '../utils/request.js'

export default {
	// delete(id){
	// 	return http.delete("/teacher/delete/"+id);
	// },
	
	// update(teacher){
	// 	return http.put("/teacher/update",teacher);
	// },
	
	// insert(teacher){
	// 	return http.post("/teacher/insert",teacher);
	// },
	
	list({gradeId,className,status,page,size}){
		return http.get("/clazz/select",{
			params:{
				gradeId,className,status,page,size
			}
		})
	}
}