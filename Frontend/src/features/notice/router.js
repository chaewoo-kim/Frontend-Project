import NoticeList from './views/NoticeList.vue';
import NoticeDetail from './views/NoticeDetail.vue';

export const noticeRoutes = [
    {
        path: '/notice',
        name: 'NoticeList',
        component: NoticeList
    },
    {
        path: '/notice/:id',
        name: 'NoticeDetail',
        component: NoticeDetail
    }
];
