<script>
import ProductSector from "@/component/product/ProductSector.vue";
import OrderSector from "@/component/order/OrderSector.vue";
import AddProductDialog from "@/component/product/AddProductDialog.vue";

const ORDER = 'ORDER';
const PRODUCT = 'PRODUCT';
export default {
  name: "App",
  components: {AddProductDialog, OrderSector, ProductSector},
  data() {
    return {
      page: ORDER,
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
