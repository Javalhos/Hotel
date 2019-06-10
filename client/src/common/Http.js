import Axios from 'axios';

import { API_URL } from '../constants';

const axios = Axios.create({
	baseURL: API_URL,
	headers: {
		// 'Access-Control-Allow-Origin': '*',
		'Content-Type': 'application/json'
	}
});

export const Http = axios;
