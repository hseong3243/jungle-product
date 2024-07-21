<script>
import api from "@/axios/index.js";
import router from "@/router/router.js";

export default {
  name: "LoginPage",
  data() {
    return {
      username: "",
      password: "",
    }
  },
  methods: {
    clickLoginButton() {
      if (this.loginApiCall()) {
        router.replace('/');
      }
    },
    async loginApiCall() {
      const request = {
        username: this.username,
        password: this.password
      };
      const response = await api.post("/api/login", request);

      const status = response.status;
      if (status === 404) {
        alert("아이디/패스워드가 올바르지 않습니다.");
        this.password = "";
        return false;
      }
      return true;
    }
  }
}
</script>

<template>
  <v-container>
    <v-row>
      <v-col class="d-flex flex-column">
        <div class="text-h6 text-center">정글 판매 시스템</div>
        <div class="text-h6 text-center mt-5">로그인</div>
        <div class="d-flex justify-center">
          <div class="d-flex flex-column justify-center w-75">
            <v-text-field
                v-model="username"
                label="아이디"
                variant="outlined"></v-text-field>
            <v-text-field
                v-model="password"
                label="패스워드"
                type="password"
                variant="outlined"></v-text-field>
            <v-btn
                @click="clickLoginButton"
                variant="tonal"
                color="green-lighten-1">로그인
            </v-btn>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>