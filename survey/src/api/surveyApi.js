import http from '../utils/request.js'

export default {
	delete(id){
		return http.delete("/survey/delete/"+id);
	},
	
	update(survey){
		return http.put("/survey/update",survey);
	},
	
	insert(survey){
		return http.post("/survey/insert",survey);
	},
	
	list(surveyForm){
		return http.get("/survey/select",{
			params:surveyForm
		});
	}
}