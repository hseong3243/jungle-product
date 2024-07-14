<script>
export default {
  name: "AddProductDialog",
  data() {
    return {
      dialog: false,
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
                  @click="dialog = false">취소
              </v-btn>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex flex-column">
              <v-text-field
                  variant="outlined"
                  label="품번"
                  density="comfortable"></v-text-field>
              <v-text-field
                  variant="outlined"
                  label="상품명"
                  density="comfortable"></v-text-field>
              <v-text-field
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