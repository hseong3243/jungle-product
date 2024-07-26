<script>
import api from "@/axios/index.js";

export default {
  name: "UpdateProductAmountDialog",
  props: ['product'],
  emits: ['updateProductAmountEvent'],
  data() {
    return {
      dialog: false,
      displayAmount: 0,
      storageAmount: 0,
    }
  },
  mounted() {
    this.displayAmount = this.product.displayAmount;
    this.storageAmount = this.product.storageAmount;
  },
  methods: {
    async clickUpdateAmountButton() {
      const request = {
        displayAmount: this.displayAmount,
        storageAmount: this.storageAmount,
      }
      await api.patch('/api/products/' + this.product.productId, request);
      this.$emit('updateProductAmountEvent');
      this.dialog = false;
    },
    clickCancelButton() {
      this.displayAmount = 0;
      this.storageAmount = 0;
      this.dialog = false;
    }
  }
}
</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" variant="tonal" color="blue-lighten-1">
      재고 관리
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="auto"
    >
      <div class="d-flex flex-column card">
        <v-container>
          <v-row class="align-center">
            <v-col>
              <div>재고 관리</div>
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
              <div class="d-flex flex-column mt-2">
                <v-text-field
                    v-model="displayAmount"
                    variant="outlined"
                    label="전시 수량"
                    density="comfortable"></v-text-field>
                <v-text-field
                    v-model="storageAmount"
                    variant="outlined"
                    label="박스 재고"
                    density="comfortable"></v-text-field>
              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex justify-end">
              <v-btn
                  variant="tonal"
                  color="blue-lighten-1"
                  @click="clickUpdateAmountButton">확인
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