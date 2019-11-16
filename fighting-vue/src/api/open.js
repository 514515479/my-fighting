import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'open';

export default {
    getArticleList(data) {
        return axios.get(`/${app_name}/${app_module}/getArticleList`, {params: data});
    },
    getArticle(id) {
        return axios.get(`/${app_name}/${app_module}/getArticle`, {params: {id: id}});
    },
    viewArticle(id) {
        return axios.get(`/${app_name}/${app_module}/viewArticle`, {params: {id: id}});
    },
    getAllTag() {
        return axios.get(`/${app_name}/${app_module}/getAllTag`);
    }
}