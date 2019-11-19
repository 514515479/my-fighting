import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'open';

export default {
    /* 文章 */
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
    },
    /* 留言板 */
    msgPage(data) {
        return axios.get(`/${app_name}/${app_module}/msgPage`, {params: data});
    },
    leaveMsg(data) {
        return axios.post(`/${app_name}/${app_module}/leaveMsg`, data);
    },
    replyMsg(data) {
        return axios.post(`/${app_name}/${app_module}/replyMsg`, data);
    },
    getHotArticle(data) {
        return axios.get(`/${app_name}/${app_module}/getHotArticle`, {params: data});
    },
    getFilingByTime(data) {
        return axios.get(`/${app_name}/${app_module}/getFilingByTime`, {params: data});
    },
}