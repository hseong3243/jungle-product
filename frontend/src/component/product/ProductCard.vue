<script>
import UpdateProductAmountDialog from "@/component/product/UpdateProductAmountDialog.vue";

export default {
  name: "ProductCard",
  components: {UpdateProductAmountDialog},
  props: ['product'],
  emits: ['updateProductAmountEvent'],
  methods: {
    consumeUpdateProductAmountEvent() {
      this.$emit('updateProductAmountEvent');
    }
  }
}
</script>

<template>
  <div class="d-flex flex-column card">
    <div class="d-flex justify-space-between mb-1">
      <div>no. {{ product.productNumber }}</div>
      <div>{{ product.price.toLocaleString("ko-KR") }}원</div>
    </div>
    <div>상품명: {{ product.name }}</div>
    <div>전시 수량: {{ product.displayAmount }}</div>
    <div>박스 재고: {{ product.storageAmount }}</div>
    <div class="d-flex justify-end ga-1 mt-1">
      <UpdateProductAmountDialog
          :product="product"
          @update-product-amount-event="consumeUpdateProductAmountEvent"/>
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