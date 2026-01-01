import MenuMainView from "@/features/menu/views/MenuMainView.vue";
import MenuDetailView from "@/features/menu/views/MenuDetailView.vue";

export const menuRoutes = [
    {
        path: '/menu',
        name: 'menu',
        component: MenuMainView
    },
    {
        path: '/menu/detail/:id',
        name: 'menu-detail',
        component: MenuDetailView,
        props: true
    }
];
