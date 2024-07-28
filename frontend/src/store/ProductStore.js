import {defineStore} from "pinia";
import api from "@/axios/index.js";

export const useProductStore = defineStore('product', {
    state: () => ({
        products: [],
    }),
    getters: {
        getProducts() {
            return this.products;
        }
    },
    actions: {
        async searchProducts(prefix) {
            let response = await api.get('/api/products/search', {
                params: {
                    productNumber: prefix
                }
            });
            this.products = response.data.data;
        }
    }
});
