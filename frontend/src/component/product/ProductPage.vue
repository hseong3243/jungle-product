<script>
import AddProductDialog from "@/component/product/AddProductDialog.vue";
import ProductSector from "@/App.vue";

export default {
  name: "ProductPage",
  components: {ProductSector, AddProductDialog}
}
</script>

<template>
<v-container>
  <v-row>
    <v-col cols="3">
      <div class="link">상품 관리</div>
    </v-col>
    <v-col cols="6">
      <div class="text-h6 text-center">정글 재고 관리</div>
    </v-col>
    <v-col cols="3">
      <AddProductDialog v-if="page === 'PRODUCT'"/>
    </v-col>
  </v-row>
  <v-row>
    <ProductSector/>
  </v-row>
</v-container>
</template>

<style scoped>

</style>