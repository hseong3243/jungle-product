import {createRouter, createWebHashHistory} from "vue-router";
import OrderPage from "@/component/order/OrderPage.vue";
import ProductPage from "@/component/product/ProductPage.vue";
import LoginPage from "@/component/login/LoginPage.vue";

const routes = [
  {path: '/', component: OrderPage},
  {path: '/product', component: ProductPage},
  {path: '/login', component: LoginPage}];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router
