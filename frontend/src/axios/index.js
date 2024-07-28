import axios from "axios";
import router from "@/router/router.js";
import {useAuthStore} from "@/store/AuthStore.js";

const api = axios.create();

api.defaults.baseURL = "https://jungle-product.shop";
api.defaults.withCredentials = true;

api.interceptors.request.use(config => {
    const authStore = useAuthStore();
    if (authStore.isLogin) {
        config.headers.Authorization = `Bearer ${authStore.accessToken}`;
    }
    return config;
})

api.interceptors.response.use(response => {
    return response;
}, error => {
    const status = error.response.status;
    const message = error.response.data.data;
    const authStore = useAuthStore();
    switch (status) {
        case 400:
            alert(message);
            break;
        case 401:
            authStore.logout();
            alert("잘못된 인증입니다.")
            router.replace('/login');
            break;
        case 403:
            authStore.logout();
            alert("다시 로그인해주세요.")
            router.replace('/login');
            break;
        case 404:
            alert("리소스가 존재하지 않습니다. 잘못된 접근입니다.")
            break;
        case 500:
            alert("시스템 오류가 발생했습니다. 관리자에게 문의하세요.")
            break;
    }
});

export default api;
