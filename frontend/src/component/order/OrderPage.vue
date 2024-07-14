<script>
import OrderCard from "@/component/order/OrderCard.vue";
import api from "@/axios/index.js";

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
      ],
      ready: false
    }
  },
  async mounted() {
    await this.findAllProducts();
    this.ready = true;
  },
  methods: {
    async findAllProducts() {
      let response = await api.get('/api/products');
      this.products = response.data.data;
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