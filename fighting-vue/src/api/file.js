import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'file';

// 上传文件路径
const upload = `/${app_name}/${app_module}/upload`;

export default {
    upload,
    // 生成文本
    generateTxt(filename, text) {
        const element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
        element.setAttribute('download', filename);
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        //document.body.removeChild(element);
    },
    // 上传图片
    uploadImg(formData) {
        return axios({
            url: upload,
            method: 'post',
            data: formData,
            headers: {'Content-Type': 'multipart/form-data'},
        })
    },
}