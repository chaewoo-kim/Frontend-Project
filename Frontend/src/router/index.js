import { createRouter, createWebHistory } from 'vue-router'
import { mainRoutes } from "@/features/main/router.js";
import { menuRoutes } from "@/features/menu/router.js";
import { storeRoutes } from "@/features/store/router.js";
import { customerRoutes } from "@/features/customer/router.js";
import { storyRoutes } from "@/features/story/router.js";
import { newsRoutes } from "@/features/news/router.js";
import { noticeRoutes } from "@/features/notice/router.js";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        ...mainRoutes,
        ...menuRoutes,
        ...storeRoutes,
        ...menuRoutes,
        ...storyRoutes,
        ...customerRoutes,
        ...newsRoutes,
        ...noticeRoutes
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
