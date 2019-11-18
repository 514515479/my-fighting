import Vue from 'vue'
import Router from 'vue-router'
import store from './store'
import msg from '@/utils/msg'

import Index from '@/views/Index.vue'

import menuApi from '@/api/menu'

Vue.use(Router)

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: () => import('@/views/open/Welcome.vue'),
            meta: {
                requireAuth: false,
            },
            children: [
                {
                    path: '',
                    name: '文章页',
                    component: () => import('@/views/open/ContentList.vue'),
                    meta: {
                        keepAlive: true,
                        requireAuth: false,
                    },
                },
                {
                    path: 'open/contentDetail/:id',
                    name: '文章详情页',
                    component: () => import('@/views/open/ContentDetail.vue'),
                    meta: {
                        keepAlive: false,
                        requireAuth: false,
                    },
                },
                {
                    path: 'open/messageBoard',
                    name: '留言板',
                    component: () => import('@/views/open/MessageBoard.vue'),
                    meta: {
                        keepAlive: true,
                        requireAuth: false,
                    },
                },
                {
                    path: 'open/announcement',
                    name: '公告',
                    component: () => import('@/views/open/Announcement.vue'),
                    meta: {
                        keepAlive: true,
                        requireAuth: false,
                    },
                },
            ]
        },
        {
            path: '/login',
            name: '登录页',
            component: () => import('@/views/Login.vue'),
            meta: {
                requireAuth: false,
            },
        },
        {
            path: '/register',
            name: '注册页',
            component: () => import('@/views/Register.vue'),
            meta: {
                requireAuth: false,
            },
        },
        {
            path: '/index',
            component: Index,
            meta: {
                requireAuth: true,
            },
            children: [
                {
                    path: '',
                    component: () => import('@/views/Home.vue'),
                    meta: {
                        requireAuth: true,
                    },
                },
                {
                    path: '/404',
                    name: '页面未找到',
                    component: () => import('@/views/404.vue'),
                    meta: {
                        requireAuth: true,
                    },
                },
            ]
        },
        {
            path: '/personal',
            component: Index,
            meta: {
                requireAuth: true,
            },
            children: [
                {
                    path: 'info',
                    name: '个人信息',
                    component: () => import('@/views/personal/Info.vue'),
                    meta: {
                        requireAuth: true,
                    },
                },
            ]
        },
        // {path: '*',redirect: '/404'}
    ]
});

// 路由守卫
router.beforeEach((to, from, next) => {
    const isLogin = localStorage.token ? true : false;
    // 访问登录页的判断
    if (to.path === "/login") {
        if (isLogin) {
            next("/index");
        } else {
            next();
        }
    }
    // requireAuth该页面是登录后才能访问,并且没有登录
    if (!isLogin) {
        if (to.meta.requireAuth === undefined || to.meta.requireAuth) {
            // 访问需要登录的页面，但未登录，重定向到登录页，带上参数
            //next({ path: "/login", query: { redirect: to.path } });
            next("/");
        } else {
            // 访问不需要登录的页面
            next();
        }
    } else {
        menuApi.initMenu(router, store);
        next();
    }
});

export default router;
