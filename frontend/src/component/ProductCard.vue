<script>
export default {
  name: "ProductCard",
  props: ['product']
}
</script>

<template>
  <div class="d-flex flex-column card">
    <div class="d-flex justify-space-between mb-1">
      <div>no. {{ product.productId }}</div>
      <div>{{ product.price }}원</div>
    </div>
    <div>상품명: {{ product.name }}</div>
    <div>전시 수량: {{ product.displayAmount }}</div>
    <div>박스 재고: {{ product.storageAmount }}</div>
    <div class="d-flex justify-end ga-1 mt-1">
      <v-btn variant="tonal" color="blue-lighten-1">재고 관리</v-btn>
    </div>
  </div>
</template>

<style scoped>
.card {
  border: #d9d9d9 2px solid;
  border-radius: 10px;
  padding: 10px;
  width: 45%;
}
</style>