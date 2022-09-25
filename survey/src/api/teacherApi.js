import http from '../utils/request.js'

export default {
	delete(id){
		return http.delete("/teacher/delete/"+id);
	},
	
	update(teacher){
		return http.put("/teacher/update",teacher);
	},
	
	insert(teacher){
		return http.post("/teacher/insert",teacher);
	},
	
	list(teacherForm){
		return http.get("/teacher/select",{
			params:teacherForm
		});
	}
}