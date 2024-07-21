import {createRouter, createWebHashHistory} from "vue-router";
import OrderPage from "@/component/order/OrderPage.vue";
import ProductPage from "@/component/product/ProductPage.vue";

const routes = [
  {path: '/', component: OrderPage},
  {path: '/product', component: ProductPage}];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router
