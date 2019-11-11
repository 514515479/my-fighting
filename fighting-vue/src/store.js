import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const types = {
    SET_AUTHENTICATED: "SET_AUTHENTICATED",
    SET_USER: "SET_USER",
    SET_MENUS: "SET_MENUS",
}

const state = {
    isAuthenticated: false,
    user: {},
    menus: [],
}

const getters = {
    isAuthenticated: state => state.isAuthenticated,
    user: state => state.user,
    menus: state => state.menus,
}

const mutations = {
    [types.SET_AUTHENTICATED](state, isAuthenticated) {
        if (isAuthenticated) {
            state.isAuthenticated = isAuthenticated;
        } else {
            state.isAuthenticated = false;
        }
    },
    [types.SET_USER](state, user) {
        if (user) {
            state.user = user;
        } else {
            state.user = {};
        }
    },
    [types.SET_MENUS](state, menus) {
        if (menus) {
            state.menus = menus;
        } else {
            state.menus = [];
        }
    }
}

const actions = {
    setAuthenticated: ({ commit }, isAuthenticated) => {
        commit(types.SET_AUTHENTICATED, isAuthenticated);
    },
    setUser: ({ commit }, user) => {
        commit(types.SET_USER, user);
    },
    setMenus: ({ commit }, menus) => {
        commit(types.SET_MENUS, menus);
    },
    clearCurrentState: ({ commit }) => {
        commit(types.SET_AUTHENTICATED, false)
        commit(types.SET_USER, {})
        commit(types.SET_MENUS, [])
    },
}

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
})
