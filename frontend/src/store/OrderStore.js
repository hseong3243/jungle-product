import {defineStore} from "pinia";
import api from "@/axios/index.js";

export const useOrderStore = defineStore('order', {
    state: () => ({
        orders: [],
        totalElements: 0,
        totalPages: 0,
    }),
    getters: {
        getOrders() {
            return this.orders;
        },
        getTotalElements() {
            return this.totalElements;
        },
        getTotalPages() {
            return this.totalPages;
        }
    },
    actions: {
        async findOrdersApiCall(page, size) {
            const response = await api.get('/api/orders', {
                params: {
                    page,
                    size
                }
            });
            const data = response.data.data;
            this.orders = data.orders;
            this.totalElements = data.totalElements;
            this.totalPages = data.totalPages;
        },
    }
});
