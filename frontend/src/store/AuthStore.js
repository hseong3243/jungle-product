import {defineStore} from "pinia";
import api from "@/axios/index.js";
import router from "@/router/router.js";

export const useAuthStore = defineStore('auth', {
    state: () => ({
        accessToken: "",
        memberId: 0
    }),
    getters: {
        getBearerToken(state) {
            return 'Bearer ' + state.accessToken;
        },
        getMemberId(state) {
            return state.memberId;
        },
        isLogin(state) {
            return state.accessToken.length !== 0;
        }
    },
    actions: {
        async loginApiCall(username, password) {
            const response = await api.post('/api/login', {
                username: username,
                password: password,
            });
            if(response.status === 200) {
                const data = response.data.data;
                this.accessToken = data.accessToken;
                this.memberId = data.memberId;
            } else {
                alert("아이디/패스워드가 일치하지 않습니다.")
            }
        },
        logout() {
            this.accessToken = "";
            this.memberId = 0;
        }
    },
    persist: true
});