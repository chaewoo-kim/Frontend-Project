import axios from 'axios'

const api = axios.create({
    baseURL: '/api/v1',
})

export const fetchStoreList = async (keyword = '') => {
    const res = await api.get('/stores', {
        params: { keyword }
    })
    return res.data.data   // { success, data, error }
}

export const fetchStoreDetail = async (storeCode) => {
    const res = await api.get(`/stores/${storeCode}`)
    return res.data.data
}
