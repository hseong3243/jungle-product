<script>
import OrderCard from "@/component/OrderCard.vue";
import ProductCard from "@/component/ProductCard.vue";
import AddProductDialog from "@/component/AddProductDialog.vue";

export default {
  name: "ProductPage",
  components: {AddProductDialog, ProductCard, OrderCard},
  data() {
    return {
      products: [
        {
          productId: 2324,
          name: '미니얼룩말',
          price: 24000,
          displayAmount: 0,
          storageAmount: 0
        }
      ]
    }
  }
}
</script>

<template>
<div>
  <div>
    <v-container>
      <v-row no-gutters class="align-center">
        <v-col cols="3">
          <div class="link">구매 관리</div>
        </v-col>
        <v-col cols="6" class="text-center">
          <div class="text-h6">정글 재고 관리</div>
        </v-col>
        <v-col cols="3">
          <AddProductDialog/>
        </v-col>
      </v-row>
    </v-container>
    <div class="d-flex justify-center">
      <div class="w-75">
        <v-text-field variant="outlined" density="compact" label="품번"></v-text-field>
      </div>
    </div>
    <div class="d-flex flex-wrap">
      <ProductCard
          v-for="product in products"
          :key="product.productId"
      :product="product">
      </ProductCard>
    </div>
  </div>
</div>
</template>

<style scoped>

</style>