import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'test';

export default {
    test() {
        return axios.get(`/${app_name}/${app_module}/hello`);
    }
}