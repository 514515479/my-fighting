import axios from '@/http'
import qs from "qs";

// 应用名
const app_name = 'api';

// 模块名
const app_module = 'system/menu';

/**
 * 格式化菜单
 */
function formatRoutes(routes) {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            id,
            path,
            component,
            name,
            icon,
            children,
            level,
            parentName,
            type,
            externalLink
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            id: id,
            path: path,
            component(resolve) {
                if (component.startsWith("Index")) {
                    require(['../views/' + component + '.vue'], resolve)
                } else if (component.startsWith("System")) {
                    require(['../views/system/' + component + '.vue'], resolve)
                } else if (component.startsWith("Blog")) {
                    require(['../views/blog/' + component + '.vue'], resolve)
                }
            },
            name: name,
            icon: icon,
            meta: {
                requireAuth: true,
            },
            children: children,
            level: level,
            parentName: parentName,
            type: type,
            externalLink: externalLink
        };
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}

export default {
    /**
     * 初始化左侧菜单栏
     */
    initMenu(router, store) {
        if (store.getters.menus.length > 0) {
            return;
        }
        axios.get(`/${app_name}/${app_module}/getMenuTree`).then(res => {
            if (res.data.status === 200) {
                const fmtRoutes = formatRoutes(res.data.data);
                const joinNotFound = fmtRoutes;
                joinNotFound.push({path: '*', redirect: '/404'});
                router.addRoutes(joinNotFound);
                store.dispatch('setMenus', fmtRoutes);
            }
        }).catch(err => {
        })
    },
    menuTree(data) {
        return axios.get(`/${app_name}/${app_module}`, {params: data});
    },
    getAllMenuTree() {
        return axios.get(`/${app_name}/${app_module}/getAllMenuTree`);
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
    refreshPermission() {
        return axios.get(`/${app_name}/${app_module}/refreshPermission`);
    }
}