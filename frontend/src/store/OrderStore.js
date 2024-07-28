import {defineStore} from "pinia";
import api from "@/axios/index.js";
import dayjs from "dayjs";

export const useOrderStore = defineStore('order', {
    state: () => ({
        orders: [],
        totalElements: 0,
        totalPages: 0,
        calculateOrdersAmount: 0,
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
        },
        getCalculateAmount() {
            return this.calculateOrdersAmount.toLocaleString("ko-KR")
        }
    },
    actions: {
        async refreshOrders() {
            await this.findOrdersApiCall(0, 10);
            await this.calculateOrdersAmountApiCall(dayjs().format('YYYY-MM-DD'))
        },
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
        async calculateOrdersAmountApiCall(date) {
            const response = await api.get('/api/calculates', {
                params: {
                    calculateDate: date
                }
            });
            const data = response.data;
            this.calculateOrdersAmount = data.data;
        }
    }
});
