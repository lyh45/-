import axios from 'axios'

let http = axios.create({
	 baseURL: 'http://127.0.0.1:9000',
	 timeout: 1000,
})

export default http;