<script>
import OrderCard from "@/component/order/OrderCard.vue";
import ProductCard from "@/component/product/ProductCard.vue";
import AddProductDialog from "@/component/product/AddProductDialog.vue";
import api from "@/axios/index.js";
import {debounce} from "lodash";
import {useProductStore} from "@/store/ProductStore.js";

export default {
  name: "ProductPage",
  components: {AddProductDialog, ProductCard, OrderCard},
  setup() {
    const productStore = useProductStore();
    return {productStore};
  },
  data() {
    return {
      keyword: "",
      products: [
        {
          productId: 1,
          productNumber: 2324,
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
  async mounted() {
    this.productStore.$subscribe((mutation, state) => {
      this.products = state.products;
    })
    await this.findAllProducts()
    this.ready = true;
  },
  methods: {
    async findAllProducts() {
      await this.searchProducts("");
    },
    async searchProducts(prefix) {
      await this.productStore.searchProducts(prefix.trim());
      this.products = this.productStore.getProducts;
    },
    consumeUpdateProductAmountEvent() {
      this.searchProducts(this.keyword.trim())
    }
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
          :product="product"
      @update-product-amount-event="consumeUpdateProductAmountEvent">
      </ProductCard>
    </div>
  </div>
</template>

<style scoped>

</style>