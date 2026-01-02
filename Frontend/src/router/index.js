import { createRouter, createWebHistory } from 'vue-router'
import { mainRoutes } from "@/features/main/router.js";
import { menuRoutes } from "@/features/menu/router.js";
import { storyRoutes } from "@/features/story/router.js";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        ...mainRoutes,
        ...menuRoutes,
        ...storyRoutes
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
