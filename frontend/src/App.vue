<script>
import ProductPage from "@/component/ProductPage.vue";
import OrderPage from "@/component/OrderPage.vue";
import AddProductDialog from "@/component/AddProductDialog.vue";

const ORDER = 'ORDER';
const PRODUCT = 'PRODUCT';
export default {
  name: "App",
  components: {AddProductDialog, OrderPage, ProductPage},
  data() {
    return {
      page: ORDER,
    }
  },
  methods: {
    selectOrderPage() {
      this.page = ORDER;
    },
    selectProductPage() {
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
          <div class="link" @click="selectOrderPage" v-if="page === 'PRODUCT'">주문 관리</div>
          <div class="link" @click="selectProductPage" v-if="page === 'ORDER'">상품 관리</div>
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
    <ProductPage v-if="page === 'PRODUCT'"/>
    <OrderPage v-if="page === 'ORDER'"/>
  </div>
</template>

<style scoped>

</style>
