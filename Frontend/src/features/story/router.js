import StoryBrandView from "@/features/story/views/StoryBrandView.vue";
import StoryWhyView from "@/features/story/views/StoryWhyView.vue";
import StoryESGView from "@/features/story/views/StoryESGView.vue";
import StoryQSRView from "@/features/story/views/StoryQSRView.vue";
import StoryAwardView from "@/features/story/views/StoryAwardView.vue";
import StoryAdView from "@/features/story/views/StoryAdView.vue";

export const storyRoutes = [
    {
        path: '/story/brand',
        name: 'story-brand',
        component: StoryBrandView
    },
    {
        path: '/story/why',
        name: 'story-why',
        component: StoryWhyView
    },
    {
        path: '/story/esg',
        name: 'story-esg',
        component: StoryESGView
    },
    {
        path: '/story/qsr',
        name: 'story-qsr',
        component: StoryQSRView
    },
    {
        path: '/story/awards',
        name: 'story-awards',
        component: StoryAwardView
    },
    {
        path: '/story/ad',
        name: 'story-ad',
        component: StoryAdView
    },
    // 사용자 추가 요청 라우팅
    {
        path: '/api/story',
        redirect: '/story/brand'
    },
    {
        path: '/api/award',
        name: 'api-award',
        component: StoryAwardView
    },
    {
        path: '/api/ad',
        name: 'api-ad',
        component: StoryAdView
    }
];
