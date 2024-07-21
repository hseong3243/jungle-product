<script>
import OrderSector from "@/component/order/OrderSector.vue";

export default {
  name: "OrderPage",
  components: {OrderSector}
}
</script>

<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <router-link to="/product">상품 관리</router-link>
      </v-col>
      <v-col cols="6">
        <div class="text-h6 text-center">정글 주문 관리</div>
      </v-col>
      <v-col cols="3">

      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <OrderSector/>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>