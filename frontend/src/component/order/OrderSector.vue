<script>
import OrderCard from "@/component/order/OrderCard.vue";
import api from "@/axios/index.js";
import {debounce} from "lodash";

export default {
  name: "OrderPage",
  components: {OrderCard},
  data() {
    return {
      keyword: "",
      products: [
        {
          productId: 1,
          productNumber: 2324,
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
  watch: {
    keyword: debounce(function (val, oldVal) {
      this.searchProducts(val)
    }, 200)
  },
  methods: {
    async findAllProducts() {
      let response = await api.get('/api/products');
      this.products = response.data.data;
    },
    async searchProducts(prefix) {
      if (prefix === "") {
        await this.findAllProducts()
        return;
      }
      let response = await api.get('/api/products/search', {
        params: {
          productId: prefix
        }
      });
      this.products = response.data.data;
    },
    consumeBuyProductEvent() {
      if (this.keyword.length >= 1) {
        this.searchProducts(this.keyword);
        return;
      }
      this.findAllProducts();
    }
  },
}
</script>

<template>
  <div class="d-flex justify-center">
    <div class="w-75">
      <v-text-field
          v-model="keyword"
          variant="outlined"
          density="compact"
          label="품번"></v-text-field>
    </div>
  </div>
  <div class="d-flex flex-wrap">
    <OrderCard
        v-for="product in products"
        :key="product.productId"
        :product="product"
        @buy-product-event="consumeBuyProductEvent">
    </OrderCard>
  </div>
</template>

<style scoped>
</style>