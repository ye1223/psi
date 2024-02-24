<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-form :model="formData" label-width="100px"
                 label-position="right" ref="addForm">
          <el-form-item label="进货单号:">
            <el-input v-model="formData.purchaseNo" disabled></el-input>
          </el-form-item>
          <el-form-item label="经办人:">
            <el-input v-model="formData.operatorName" disabled></el-input>
          </el-form-item>
          <el-form-item label="采购日期:">
            <el-input v-model="formData.purchaseDate" disabled></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="addRow">添加一行</el-button>
            <el-button type="primary" @click="doSubmit('addForm')">提交采购单</el-button>
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
            <el-table-column label="采购价/元">
              <template slot-scope="scope">
                <el-input v-model="scope.row.buyPrice"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="采购数量">
              <template slot-scope="scope">
                <el-input v-model.number="scope.row.num"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="供应商">
              <template slot-scope="scope">
                <el-select v-model="scope.row.supplierId">
                  <el-option v-for="(one) in formCfg.supplierList"
                             :label="one.name"
                             :value="one.id"
                             :key="one.id">
                  </el-option>
                </el-select>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </el-col>
    </el-row>

    <check-goods :diaVisible="checkGoodsVisible"
                 :forRowIndex="forRowIndex"
                 @giveSelectedGoods="handleConfirmCheck"
                 :checkGoodsVisible.sync="checkGoodsVisible"
    >
      <!--从子组件的事件中同步值到父组件中的checkGoodsVisible
      第一个checkGoodsVisible表示子组件update:checkGoodsVisible中的
      第二个checkGoodsVisible表示父组件中的变量
      -->
    </check-goods>

  </div>
</template>

<script>
  import CheckGoods from "../../components/CheckGoods";
  import {now} from "../../utils/dateUtil";

  export default {
    name: "AddPurchase",
    data() {
      return {
        checkGoodsVisible: false,
        // 通过点击表格的第几行打开的选择商品对话框
        forRowIndex: -1,
        formData: {
          purchaseNo: '',
          operatorId: '',
          operatorName: '',
          purchaseDate: '',
          detailList: []
        },
        formCfg: {
          supplierList: []
        }
      }
    },
    methods: {
      addRow() {
        this.formData.detailList.push({
          goodsId: '',
          goodsName: '',
          unit: '',
          buyPrice: '',
          num: '',
          supplierId: '',
          supplierName: ''
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
        // 关闭对话框
        this.checkGoodsVisible = false;
      },
      initSupplierList(){
        this.$formGet({
          url: '/supplier/list'
        }).then(result => {
          this.formCfg.supplierList = result.data;
        });
      },
      initPurchaseNo(){
        this.$formGet({
          url: '/purchase/getNo'
        }).then(result => {
          this.formData.purchaseNo = result.data;
        });
      },
      initUserInfo() {
        this.formData.operatorId = this.$store.getters.userInfo.id;
        this.formData.operatorName = this.$store.getters.userInfo.userName;
      },
      initPurchaseDate() {
        this.formData.purchaseDate = now();
      },
      doSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$appJsonPost({
              url:'/purchase/add',
              data:this.formData
            }).then(resultVO => {
              this.$router.replace("/purchase/manage");
            }).catch(resultVO=>{
              this.$message.error(resultVO.errMsg);
            });
          }
        });
      }
    },
    components: {
      CheckGoods
    },
    created() {
      this.initSupplierList();
      this.initPurchaseNo();
      this.initUserInfo();
      this.initPurchaseDate();
    }
  }
</script>

<style>
</style>
