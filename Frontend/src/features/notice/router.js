import NoticeList from './views/NoticeList.vue';
import NoticeDetail from './views/NoticeDetail.vue';

export const noticeRoutes = [
    {
        path: '/notice/detail/:id(\\d+)',
        name: 'NoticeDetail',
        component: NoticeDetail
    },
    {
        path: '/notice/:category?/:tab?',
        name: 'NoticeList',
        component: NoticeList,
        props: true
    }
];
