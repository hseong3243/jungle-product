<script>
import BuyProductDialog from "@/component/order/BuyProductDialog.vue";

export default {
  name: "OrderCard",
  components: {BuyProductDialog},
  props: ['product'],
  emits: ['buyProductEvent'],
  data() {
    return {
      amount: 0,
    }
  },
  methods: {
    consumeCancelBuyProductEvent() {
      this.amount = 0;
    },
    consumeBuyProductEvent() {
      this.$emit('buyProductEvent');
    }
  }
}
</script>

<template>
  <div class="d-flex flex-column card">
    <div class="d-flex justify-space-between mb-1">
      <div>no. {{ product.productNumber }}</div>
      <div>{{ product.price }}원</div>
    </div>
    <div>상품명: {{ product.name }}</div>
    <div>전시 수량: {{ product.displayAmount }}</div>
    <div>박스 재고: {{ product.storageAmount }}</div>
    <div class="d-flex justify-end ga-1 mt-1">
      <div class="d-flex align-center">
        <div class="border rounded d-flex justify-center pa-1">
          <select
              v-model="amount"
              class="text-center">
            <option value="0">수량</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
          </select>
        </div>
      </div>
      <BuyProductDialog
          :product="product"
          :amount="amount"
          @cancel-buy-product-event="consumeCancelBuyProductEvent"
      @buy-product-event="consumeBuyProductEvent"/>
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