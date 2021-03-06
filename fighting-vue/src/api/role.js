import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'system/role';

export default {
    getAllRole() {
        return axios.get(`/${app_name}/${app_module}/getAllRole`);
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
    getRoleMenuIdList(id) {
        return axios.get(`/${app_name}/${app_module}/getRoleMenuIdList`, {params: {id: id}});
    }
}