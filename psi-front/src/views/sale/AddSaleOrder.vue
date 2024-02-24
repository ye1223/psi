<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-form :model="formData" label-width="100px"
                 label-position="right" ref="addForm">
          <el-form-item label="销售单号:">
            <el-input v-model="formData.orderNo" disabled></el-input>
          </el-form-item>
          <el-form-item label="订单创建时间:">
            <el-input v-model="formData.createTime" disabled></el-input>
          </el-form-item>
          <el-form-item label="总金额:">
            <el-input v-model="totalAmount" disabled></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="addRow">添加一行</el-button>
            <el-button type="primary" @click="doSubmit('addForm')">提交销售订单</el-button>
          </el-form-item>

          <el-table
            :data="formData.detailList"
            stripe
            border
            size="mini"
            style="width: 100%">
            <el-table-column
              label="商品名"
              width="180">
              <template slot-scope="scope">
                <el-input v-model="scope.row.goodsName"
                          @click.native="openCheckGoods(scope.$index)"
                          disabled
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="单位"
              width="180">
              <template slot-scope="scope">
                <el-input v-model="scope.row.unit" disabled></el-input>
              </template>
            </el-table-column>
            <el-table-column label="销售价">
              <template slot-scope="scope">
                <el-input v-model.number="scope.row.salePrice" disabled></el-input>
              </template>
            </el-table-column>
            <el-table-column label="销售数量">
              <template slot-scope="scope">
                <el-input v-model.number="scope.row.num"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </el-col>
    </el-row>

    <check-goods :diaVisible="checkGoodsVisible"
                 :forRowIndex="forRowIndex"
                 @giveSelectedGoods="handleConfirmCheck"
                 @update:checkGoodsVisible="handleCloseIcon"
    >
      <!--@update:checkGoodsVisible 处理子组件的事件
        子组件触发事件时如果使用 "update:"前缀+事件名来更新父组件的属性,
        则父组件此处可间写,参见AddPurchase.vue中此处的写法
      -->
    </check-goods>

  </div>
</template>

<script>
  import CheckGoods from "../../components/CheckGoods";
  import {now} from "../../utils/dateUtil";

  export default {
    name: "AddSaleOrder",
    data() {
      return {
        checkGoodsVisible: false,
        // 通过点击表格的第几行打开的选择商品对话框
        forRowIndex: -1,
        formData: {
          orderNo: '',
          createTime: '',
          detailList: []
        }
      }
    },
    computed:{
      totalAmount(){
        return this.calcTotalAmount();
      }
    },
    methods: {
      addRow() {
        this.formData.detailList.push({
          goodsId: '',
          goodsName: '',
          unit: '',
          salePrice: '',
          num: ''
        })
      },
      openCheckGoods(index) {
        this.checkGoodsVisible = true;
        this.forRowIndex = index;
      },
      handleConfirmCheck(selectedGoods) {
        let idx = selectedGoods["rowIndex"];
        this.formData.detailList[idx].goodsId = selectedGoods["goodsId"];
        this.formData.detailList[idx].goodsName = selectedGoods["goodsName"];
        this.formData.detailList[idx].unit = selectedGoods["unit"];
        this.formData.detailList[idx].salePrice = selectedGoods["salePrice"];
        // 关闭对话框
        this.checkGoodsVisible = false;
      },
      initNo(){
        this.$formGet({
          url: '/saleOrder/getNo'
        }).then(result => {
          this.formData.orderNo = result.data;
        });
      },
      initCreateDate() {
        this.formData.createTime = now();
      },
      calcTotalAmount() {
        let total = 0;
        if (this.formData.detailList) {
          this.formData.detailList.forEach((crtValue,index) => {
            total += crtValue["salePrice"] * crtValue["num"];
          })
        }
        return total;
      },
      doSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.formData["totalAmount"] = this.totalAmount;
            this.$appJsonPost({
              url:'/saleOrder/add',
              data:this.formData
            }).then(resultVO => {
              this.$router.replace("/saleOrder/manage");
            }).catch(resultVO=>{
              this.$message.error(resultVO.errMsg);
            });
          }
        });
      },
      handleCloseIcon(value){
        this.checkGoodsVisible = value;
      }
    },
    components: {
      CheckGoods
    },
    created() {
      this.initNo();
      this.initCreateDate();
    }
  }
</script>

<style>
</style>
