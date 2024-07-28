<script>
import dayjs from "dayjs";
import api from "@/axios/index.js";

export default {
  name: "FindOrdersDialog",
  computed: {
    dayjs() {
      return dayjs
    }
  },
  data() {
    return {
      dialog: false,
      orders: [
        {
          orderId: 1,
          product: {
            productNumber: 2324,
            name: "미니얼룩말",
            price: 24000,
          },
          amount: 3,
          createdAt: new Date(),
        }
      ],
      page: 1,
      size: 10,
      totalElements: 0,
      totalPages: 0,
      calculateOrdersAmount: 0,
      dataReady: false,
    }
  },
  async mounted() {
    await this.findOrdersApiCall(0, this.size);
    await this.calculateOrdersAmountApiCall(dayjs().format('YYYY-MM-DD'));
    this.dataReady = true;
  },
  watch: {
    page() {
      this.findOrdersApiCall(this.page - 1, this.size);
    }
  },
  methods: {
    async findOrdersApiCall(page, size) {
      const response = await api.get('/api/orders', {
        params: {
          page,
          size
        }
      });
      const data = response.data.data;
      this.orders = data.orders;
      this.totalElements = data.totalElements;
      this.totalPages = data.totalPages;
    },
    async calculateOrdersAmountApiCall(date) {
      const response = await api.get('/api/calculates', {
        params: {
          calculateDate: date
        }
      });
      const data = response.data;
      this.calculateOrdersAmount = data.data;
    }
  }
}
</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" variant="tonal" color="blue-lighten-1">
      주문 내역
    </v-btn>

    <v-dialog
        v-model="dialog"
        width="auto"
    >
      <div class="d-flex flex-column card">
        <v-container>
          <v-row class="align-center">
            <v-col>
              <div>주문 내역</div>
            </v-col>
            <v-col class="d-flex justify-end">
              <div>금일 판매 금액: {{calculateOrdersAmount.toLocaleString("ko-KR")}}원</div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-table>
                <thead>
                <tr>
                  <th class="text-left">
                    주문번호
                  </th>
                  <th class="text-left">
                    상품 번호
                  </th>
                  <th class="text-left">
                    상품 이름
                  </th>
                  <th class="text-left">
                    판매 개수
                  </th>
                  <th class="text-left">
                    총 가격
                  </th>
                  <th class="text-left">
                    판매 시간
                  </th>
                </tr>
                </thead>
                <tbody v-if="dataReady">
                <tr v-for="order in orders" :key="order.orderId">
                  <td>{{ order.orderId }}</td>
                  <td>{{ order.product.productNumber }}</td>
                  <td>{{ order.product.name }}</td>
                  <td>{{ order.amount }}</td>
                  <td>{{ order.amount * order.product.price }}</td>
                  <td>{{ dayjs(order.createdAt).format('YYYY/MM/DD ahh:mm:ss') }}</td>
                </tr>
                </tbody>
              </v-table>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-pagination
                  v-model="page"
                  :length="totalPages">

              </v-pagination>
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