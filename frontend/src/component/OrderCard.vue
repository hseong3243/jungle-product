<script>
export default {
  name: "OrderCard",
  props:['product']
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
      <div class="d-flex align-center">
        <div class="border rounded d-flex justify-center pa-1">
          <select class="text-center">
            <option>수량</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
          </select>
        </div>
      </div>
      <v-btn
          color="blue-lighten-1"
          variant="tonal">
        구매
      </v-btn>
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