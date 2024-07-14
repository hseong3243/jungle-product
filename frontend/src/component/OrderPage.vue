<script>
import OrderCard from "@/component/OrderCard.vue";

export default {
  name: "OrderPage",
  components: {OrderCard},
  data() {
    return {
      products: [
        {
          productId: 2324,
          name: '미니얼룩말',
          price: 24000,
          displayAmount: 10,
          storageAmount: 30
        }
      ]
    }
  }
}
</script>

<template>
  <div>
    <v-container>
      <v-row no-gutters class="align-center">
        <v-col cols="3">
          <div class="link">상품 관리</div>
        </v-col>
        <v-col cols="6">
          <div class="text-h6 text-center">정글 구매 관리</div>
        </v-col>
        <v-col cols="3">

        </v-col>
      </v-row>
    </v-container>
    <div class="d-flex justify-center">
      <div class="w-75">
        <v-text-field variant="outlined" density="compact" label="품번"></v-text-field>
      </div>
    </div>
    <div class="d-flex flex-wrap">
      <OrderCard
          v-for="product in products"
          :key="product.productId"
          :product="product">
      </OrderCard>
    </div>
  </div>
</template>

<style scoped>
</style>