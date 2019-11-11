import axios from 'axios'
import { Loading } from 'element-ui'
import msg from '@/utils/msg'
import common from '@/utils/common'

//axios.defaults.baseURL='http://127.0.0.1:8888'

let loading;
function startLoading() {
    loading = Loading.service({
        lock: true,
        text: "拼命加载中...",
        background: 'rgba(255,255,255,0.9)'
    })
}
function stopLoading() {
    loading.close();
}

// 请求拦截
axios.interceptors.request.use(config => {
    // 加载动画
    startLoading();
    // 每个请求都带上token
    if (localStorage.token) {
        config.headers.Authorization = localStorage.token;
    }
    return config;
}, err => {
    return Promise.reject(err);
})

// 响应拦截
axios.interceptors.response.use(res => {
    // 停止动画
    stopLoading();
    // 如果响应中包含新的token，则缓存起来
    const newToken = res.headers.authorization;
    if (newToken) {
        if (newToken != localStorage.token) {
            localStorage.setItem("token", newToken);
        }
    }
    // 如果状态码是200，并且有msg，则输入msg
    if (res.data.status === 200) {
        if (res.data.msg) {
            msg.success(res.data.msg);
        }
    } else {
        msg.error(res.data.msg);
    }
    return res;
}, err => {
    // 停止动画
    stopLoading();
    if (err.response.data) {
        // 提示错误信息
        if (err.response.data.msg) {
            msg.error(err.response.data.msg);
        } else {
            msg.error("服务器繁忙，请稍后再试~");
        }
    }
    if (err.response.status === 401) {
        common.clearTokenToLogin();
    }
    return Promise.reject(err);
})

export default axios;