<script>
export default {
  name: "UpdateProductAmountDialog",
  props: ['product'],
  data() {
    return {
      dialog: false,
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
                  @click="dialog = false">취소
              </v-btn>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex flex-column">
              <div>
                품번: {{ product.productId }}
              </div>
              <div>
                상품명: {{ product.name }}
              </div>
              <div class="d-flex flex-column mt-2">
                <v-text-field
                    variant="outlined"
                    label="전시 수량"
                    density="comfortable"></v-text-field>
                <v-text-field
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
                  @click="dialog = false">확인
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