<script>
import OrderSector from "@/App.vue";

export default {
  name: "OrderPage",
  components: {OrderSector}
}
</script>

<template>
<v-container>
  <v-row>
    <v-col cols="3">
      <div class="link">주문 관리</div>
    </v-col>
  </v-row>
  <v-col cols="6">
    <div class="text-h6 text-center">정글 주문 관리</div>
  </v-col>
  <v-col cols="3">

  </v-col>
  <v-row>
    <OrderSector/>
  </v-row>
</v-container>
</template>

<style scoped>

</style>