import EventView from './views/EventVeiw.vue'
import EventDetailView from './views/EventDetailView.vue'

export const newsRoutes = [
    {
        path: '/news',
        redirect: '/news/event/ongoing'
    },
    {
        path: '/news/event/:status?',
        name: 'EventList',
        component: EventView
    },
    {
        path: '/news/notice',
        name: 'NoticeList',
        component: EventView
    },
    {
        path: '/news/event/detail/:id',
        name: 'EventDetail',
        component: EventDetailView
    }
]
