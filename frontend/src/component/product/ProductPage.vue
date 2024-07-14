<script>
import OrderCard from "@/component/order/OrderCard.vue";
import ProductCard from "@/component/product/ProductCard.vue";
import AddProductDialog from "@/component/product/AddProductDialog.vue";
import api from "@/axios/index.js";
import {debounce} from "lodash";

export default {
  name: "ProductPage",
  components: {AddProductDialog, ProductCard, OrderCard},
  data() {
    return {
      keyword: "",
      products: [
        {
          productId: 2324,
          name: '미니얼룩말',
          price: 24000,
          displayAmount: 0,
          storageAmount: 0
        }
      ],
      ready: false
    }
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
      if(prefix === "") {
        await this.findAllProducts()
        return;
      }
      let response = await api.get('/api/products/search', {
        params: {
          productId: prefix
        }
      });
      this.products = response.data.data;
      console.log(this.products)
    }
  },
  async mounted() {
    await this.findAllProducts()
    this.ready = true;
  }
}
</script>

<template>
  <div>
    <div class="d-flex justify-center">
      <div class="w-75">
        <v-text-field
            v-model="keyword"
            variant="outlined"
            density="compact"
            label="품번"></v-text-field>
      </div>
    </div>
    <div class="d-flex flex-wrap" v-if="ready">
      <ProductCard
          v-for="product in products"
          :key="product.productId"
          :product="product">
      </ProductCard>
    </div>
  </div>
</template>

<style scoped>

</style>