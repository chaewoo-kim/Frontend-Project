/* 각 기능별 라우팅 내용이 작성될 파일 */
import MainView from "@/features/main/views/MainView.vue";

export const mainRoutes = [
    {
        path: '/',
        name: 'main',
        component: MainView
    },
    {
        path: '/main',
        redirect: '/'
    }
];
