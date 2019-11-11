<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: 'app',
        components: {},
        created() {
            if (localStorage.token && localStorage.user) {
                this.$store.dispatch("setAuthenticated", true);
                this.$store.dispatch("setUser", JSON.parse(localStorage.user));
            }
        }
    }
</script>

<style>
    html,
    body,
    #app {
        width: 100%;
        height: 100%;
    }
</style>