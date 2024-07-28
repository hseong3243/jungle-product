<script>
import api from "@/axios/index.js";
import {useOrderStore} from "@/store/OrderStore.js";

export default {
  name: "BuyProductDialog",
  setup() {
    const orderStore = useOrderStore();
    return {orderStore}
  },
  props: ['product', 'amount'],
  emits: ['cancelBuyProductEvent', 'buyProductEvent'],
  data() {
    return {
      dialog: false,
    }
  },
  methods: {
    clickCancelButton() {
      this.$emit("cancelBuyProductEvent");
      this.dialog = false;
    },
    async clickBuyProductButton() {
      const request = {
        productId: this.product.productId,
        amount: this.amount,
      }
      await api.post('/api/orders', request);
      await this.orderStore.refreshOrders();
      this.$emit('buyProductEvent');
      this.dialog = false;
    }
  }
}
</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" variant="tonal" color="blue-lighten-1">
      판매
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="auto"
    >
      <div class="d-flex flex-column card">
        <v-container>
          <v-row class="align-center">
            <v-col>
              <div>주문 확인</div>
            </v-col>
            <v-col class="d-flex justify-end">
              <v-btn
                  variant="tonal"
                  color="red-lighten-1"
                  @click="clickCancelButton">취소
              </v-btn>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex flex-column">
              <div>
                품번: {{ product.productNumber }}
              </div>
              <div>
                상품명: {{ product.name }}
              </div>
              <div>
                판매 갯수: {{ amount }}개
              </div>
              <div>
                총 가격: {{ (amount * product.price).toLocaleString("ko-KR") }}원
              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex justify-end">
              <v-btn
                  variant="tonal"
                  color="blue-lighten-1"
                  @click="clickBuyProductButton">판매
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </v-dialog>
  </div>
</template>

<style scoped>
.card {
  background: white;
  border: #d9d9d9 2px solid;
  border-radius: 10px;
  padding: 10px;
  min-width: 350px;
}
</style>