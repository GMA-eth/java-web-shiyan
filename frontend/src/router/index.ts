import { createRouter, createWebHashHistory } from "vue-router";


const routes = [
    {
        path: "/",
        name: "Home",
        component: () => import("../views/Home.vue"),
    },
    {
        path: "/Login",
        name: "Login",
        component: () => import("../views/app/Users.vue"),
    },
    {
        path: "/canteen/:canteenId",
        name: "Canteen",
        component: () => import("../views/app/CanteenDish.vue"),
    },
    {
        path: "/reviews",
        name: "Reviews",
        component: () => import("../views/Reviews.vue"),
    },
    {
        path: "/about",
        name: "About",
        component: () => import("../views/About.vue"),
    },
    {
        path: "/am-canteen",
        name: "AmCanteen",
        component: () => import("../views/app/CanteenAM.vue"),
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

/* router.beforeEach((to, from, next) => {
    console.log(from);
    if (to.name !== 'Login') next({ name: 'Login' })
    else next()
}) */


export default router;