
import CustomerMain from './views/CustomerMain.vue';
import CustomerQAList from './views/CustomerQAList.vue';
import CustomerDetail from './views/CustomerDetail.vue';

export const customerRoutes = [
    {
        path: '/customer/main',
        name: 'CustomerMain',
        component: CustomerMain
    },
    {
        path: '/customer/qa_list',
        name: 'CustomerQAList',
        component: CustomerQAList
    },
    {
        path: '/customer/detail',
        name: 'CustomerDetail',
        component: CustomerDetail
    }
];
