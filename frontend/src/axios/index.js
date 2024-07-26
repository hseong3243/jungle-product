import axios from "axios";
import router from "@/router/router.js";
import {useAuthStore} from "@/store/AuthStore.js";

const api = axios.create();

api.defaults.baseURL = "https://jungle-product.shop";
api.defaults.withCredentials = true;

api.interceptors.request.use(config => {
    const authStore = useAuthStore();
    console.log(authStore.getBearerToken)
    if(authStore.isLogin) {
        config.headers.Authorization = `Bearer ${authStore.accessToken}`;
    }
    return config;
})

api.interceptors.response.use(response => {
    return response;
}, error => {
    const status = error.response.status;
    if (status === 403) {
        router.replace('/login');
    }
});

export default api;
