<script>
import OrderCard from "@/component/order/OrderCard.vue";

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
</template>

<style scoped>
</style>