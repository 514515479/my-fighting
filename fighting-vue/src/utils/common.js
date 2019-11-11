import router from '@/router'
import store from '@/store'

export default {
    // 清空token和user
    clearTokenToLogin() {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        store.dispatch("clearCurrentState");
        if (router.history.current.path != "/login") {
            router.push("/login");
        }
    },
    // 去首页
    toIndex() {
        if (router.history.current.path != "/index") {
            router.push("/index");
        }
    },
    // 校验表单
    validateForm(form) {
        let result;
        form.validate(valid => {
            result = valid;
        });
        return result;
    }
}