import http from '../utils/request.js'

export default {
	login(username,password){
		return http.get("/manager/login",{
			params:{
				username,
				password
			}
		});
	}
}