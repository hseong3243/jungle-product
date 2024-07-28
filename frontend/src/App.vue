<script>
import ProductSector from "@/component/product/ProductSector.vue";
import OrderSector from "@/component/order/OrderSector.vue";
import AddProductDialog from "@/component/product/AddProductDialog.vue";
import {useAuthStore} from "@/store/AuthStore.js";
import router from "@/router/router.js";

const ORDER = 'ORDER';
const PRODUCT = 'PRODUCT';
export default {
  name: "App",
  components: {AddProductDialog, OrderSector, ProductSector},
  setup() {
    const authStore = useAuthStore();
    return {authStore}
  },
  data() {
    return {
      page: ORDER,
    }
  },
  mounted() {
    if(!this.authStore.isLogin) {
      router.replace("/login");
    }
  },
  methods: {
    selectOrderSector() {
      this.page = ORDER;
    },
    selectProductSector() {
      this.page = PRODUCT;
    }
  }
}
</script>

<template>
  <div id="app" class="pa-2 d-flex flex-column">
    <router-view/>
  </div>
</template>

<style scoped>

</style>
