// Mock Data mimicking the response content
const mockEvents = [
    // ONGOING EVENTS
    { id: 381, isOngoing: true, title: "골든 에그 트러플 머쉬룸 신제품 출시", category: "일반", daysLeft: 3, isNew: true, startDate: "2024.02.01", endDate: "2024.02.28", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/10/10/e0a99dea-8e1b-43b2-9d31-bb76b8608aef.jpg" },
    { id: 382, isOngoing: true, title: "트머와 신제품 출시", category: "일반", daysLeft: 3, isNew: false, startDate: "2024.02.05", endDate: "2024.03.05", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/09/18/de9c56cf-cff4-4883-8b11-f0e0c47491e0.jpg" },
    { id: 383, isOngoing: true, title: "NEW 딥 크런치 아포가토", category: "일반", daysLeft: 2, isNew: true, startDate: "2024.02.10", endDate: "2024.02.15", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/10/13/ada04dd8-3bb6-4183-b41d-6c960bc58e40.png" },
    { id: 384, isOngoing: true, title: "알림 켜고 버거킹 소식 받으세요.", category: "일반", daysLeft: 7, isNew: false, startDate: "2024.02.01", endDate: "2024.02.20", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/02/11/efe754b9-40c5-4c1f-bc6a-e311dfa35851.png" },
    { id: 385, isOngoing: true, title: "오믈렛킹모닝", category: "일반", daysLeft: 12, isNew: false, startDate: "2024.02.01", endDate: "2024.02.28", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/01/15/f54fe4cf-90c8-4988-869b-7b602a15f50a.png" },
    { id: 386, isOngoing: true, title: "치킨킹, 치킨킹BLT", category: "일반", daysLeft: 0, isNew: false, startDate: "2024.01.01", endDate: "2024.12.31", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/01/09/cde7fcee-91ac-4671-a15a-c2c786f0161c.png" },
    { id: 387, isOngoing: true, title: "리얼 어니언링 출시", category: "일반", daysLeft: 20, isNew: true, startDate: "2024.02.01", endDate: "2024.03.31", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/01/09/f8692546-04c0-43ac-bc8e-3ccb1a4b9588.png" },
    { id: 388, isOngoing: true, title: "24시간 딜리버리", category: "일반", daysLeft: 0, isNew: false, startDate: "2024.01.01", endDate: "2024.12.31", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/01/15/b4727d3e-3e05-43bc-84b7-55d93e40f271.png" },

    // ENDED EVENTS
    { id: 402, isOngoing: false, title: "와퍼 4500원 할인 이벤트", category: "이벤트", startDate: "2023.12.01", endDate: "2023.12.31", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/10/10/e0a99dea-8e1b-43b2-9d31-bb76b8608aef.jpg" },
    { id: 403, isOngoing: false, title: "종료된 이벤트 2", category: "이벤트", startDate: "2023.12.01", endDate: "2023.12.31", image: "https://mob-prd.burgerking.co.kr/images/event/app/list/2025/09/18/de9c56cf-cff4-4883-8b11-f0e0c47491e0.jpg" },
    // more entries could be added for duplication test
];

// Helper to simulate async response structure ({ data: [...] })
const mockResponse = (data) => Promise.resolve({ data });

// 진행중 이벤트 목록
export const getOngoingEvents = () => mockResponse(mockEvents.filter(e => e.isOngoing));

// 종료 이벤트 목록
export const getEndedEvents = () => mockResponse(mockEvents.filter(e => !e.isOngoing));

// 이벤트 상세
export const getEventDetail = (eventId) => mockResponse(mockEvents.find(e => e.id == eventId));

export default {
    getOngoingEvents,
    getEndedEvents,
    getEventDetail,
};
