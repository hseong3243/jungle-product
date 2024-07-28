<script>
import api from "@/axios/index.js";
import {useProductStore} from "@/store/ProductStore.js";

export default {
  name: "AddProductDialog",
  setup() {
    const productStore = useProductStore();
    return {productStore}
  },
  data() {
    return {
      productNumber: "",
      name: "",
      price: 0,
      dialog: false,
    }
  },
  methods: {
    async clickAddProductButton() {
      const request = {
        productNumber: this.productNumber,
        name: this.name,
        price: this.price,
      }
      await api.post('/api/products', request);
      await this.productStore.searchProducts("");
      this.dialog = false;
    },
    clickCancelButton() {
      this.productNumber = "";
      this.name = "";
      this.price = 0;
      this.dialog = false;
    }
  }
}
</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" variant="tonal" color="green-lighten-1">
      상품 등록
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="auto"
    >
      <div class="d-flex flex-column card">
        <v-container>
          <v-row class="align-center">
            <v-col>
              <div>상품 등록</div>
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
              <v-text-field
                  v-model="productNumber"
                  variant="outlined"
                  label="품번"
                  density="comfortable"></v-text-field>
              <v-text-field
                  v-model="name"
                  variant="outlined"
                  label="상품명"
                  density="comfortable"></v-text-field>
              <v-text-field
                  v-model="price"
                  variant="outlined"
                  label="가격"
                  density="comfortable"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex justify-end">
              <v-btn
                  variant="tonal"
                  color="blue-lighten-1"
                  @click="clickAddProductButton">확인
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