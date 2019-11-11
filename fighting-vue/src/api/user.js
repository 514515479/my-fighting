import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'system/user';

export default {
    login(user) {
        return axios.post(`/${app_name}/login`, qs.stringify(user));
    },
    logout() {
        return axios.get(`/${app_name}/logout`);
    },
    list(data) {
        return axios.get(`/${app_name}/${app_module}`, {params: data});
    },
    save(data) {
        return axios.post(`/${app_name}/${app_module}`, data);
    },
    update(data) {
        return axios.put(`/${app_name}/${app_module}`, data);
    },
    delete(ids) {
        const params = {
            ids: ids
        };
        return axios.delete(`/${app_name}/${app_module}`, {
            params: params,
            paramsSerializer: params => {
                return qs.stringify(params, {indices: false})
            }
        });
    },
}