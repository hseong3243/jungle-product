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
    <v-container>
      <v-row no-gutters class="align-center">
        <v-col cols="3">
          <div class="link" @click="selectOrderSector" v-if="page === 'PRODUCT'">주문 관리</div>
          <div class="link" @click="selectProductSector" v-if="page === 'ORDER'">상품 관리</div>
        </v-col>
        <v-col cols="6">
          <div class="text-h6 text-center" v-if="page === 'ORDER'">정글 주문 관리</div>
          <div class="text-h6 text-center" v-if="page === 'PRODUCT'">정글 재고 관리</div>
        </v-col>
        <v-col cols="3">
          <AddProductDialog v-if="page === 'PRODUCT'"/>
        </v-col>
      </v-row>
    </v-container>
    <ProductSector v-if="page === 'PRODUCT'"/>
    <OrderSector v-if="page === 'ORDER'"/>
  </div>
</template>

<style scoped>

</style>
