import axios from 'axios';
import { AGENTAPP_BASE } from '../common/agentapp-api';

class HttpBaseClient {

    constructor() {
        this.client = axios.create({
            baseURL: AGENTAPP_BASE.URL
        });
        this.setInterceptor();
    }

    setInterceptor = () => {
        this.client.interceptors.request.use(config => {
            const token = window.localStorage.getItem("token");

            if (!!token) {
                Object.assign(config.headers, {
                    Authorization: `Bearer ${token}`
                });
            }

            return config;
        });

        this.client.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            // const originalRequest = error.config;
            // const { status, data, config } = error.response;
            // const token = window.localStorage.getItem("token");
            // if (status === 401 && !originalRequest._retry) {
            //     originalRequest._retry = true;
            //     console.log(`Bearer ${token}`);
            //     return axios.get(AGENTAPP_BASE.URL + '/auth/refresh', {
            //         headers: { 'Auth': `Bearer ${token}` }
            //     }).then(res => {
            //         console.log(res.data);
            //         if (res.status === 200) {
            //             localStorage.setItem('token', res.data);
            //             return this.client(originalRequest);
            //         }
            //     })
            // }

            throw error;
        });
    };

    attachHeaders(headers) {
        Object.assign(this.client.defaults.headers, headers);
    }

    detachHeader(headerKey) {
        delete this.client.defaults.headers[headerKey];
    }

    getApiClient() {
        return this.client;
    }

};

export default HttpBaseClient;