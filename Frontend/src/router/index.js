import { createRouter, createWebHistory } from 'vue-router'
import { mainRoutes } from "@/features/main/router.js";
import { menuRoutes } from "@/features/menu/router.js";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        ...mainRoutes,
        ...menuRoutes
    ],
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        } else {
            return { top: 0 };
        }
    }
})

export default router
