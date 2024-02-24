<template>
  <div>
    <section class="title-bar">
      <el-row>
        <el-col :span="2">
          <el-button @click="toAdd" type="primary"
                     size="mini"
                     icon="el-icon-plus">
            新增
          </el-button>
        </el-col>
        <el-col :span="22">
          <el-form :model="searchFormData" :inline="true" size="small">
            <el-form-item label-width="120">
              <el-input v-model="searchFormData.fuzzyOrderNo"
                placeholder="按订单号模糊搜索"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="doSearch">查询</el-button>
              <el-button type="primary" @click="doRefund">全额退款</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

    </section>
    <section>
      <el-table
        :data="tableData.rows"
        stripe
        border
        size="mini"
        @selection-change="handleSelectionChange"
        style="width: 100%">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table
              :data="props.row.detailList"
              stripe
              border
              size="mini"
              style="width: 100%">
              <el-table-column
                prop="id"
                label="系统编号"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsName"
                label="商品名">
              </el-table-column>
              <el-table-column
                prop="unit"
                label="单位">
              </el-table-column>
              <el-table-column
                prop="salePrice"
                label="销售价格">
              </el-table-column>
              <el-table-column
                prop="num"
                label="数量">
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column
          prop="id"
          label="系统编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="orderNo"
          label="订单号">
        </el-table-column>
        <el-table-column
          prop="totalAmount"
          label="总金额">
        </el-table-column>
        <el-table-column
          prop="statusDesc"
          label="状态">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="订单创建时间">
        </el-table-column>

      </el-table>
    </section>

    <section class="page">
      <el-pagination
        @size-change="handlePageSizeChange"
        @current-change="handlePageNowChange"
        :current-page="searchFormData.pageNow"
        :page-sizes="[5,10, 15,20]"
        :page-size="searchFormData.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.total">
      </el-pagination>
    </section>
  </div>
</template>

<script>
export default {
  name: "ManagePurchase",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        fuzzyOrderNo:''
      },
      tableData: {total:0,rows:[]},
      selectedRows:[]
    }
  },
  methods: {
    toAdd(){
      this.$router.replace("/saleOrder/add")
    },
    handlePageSizeChange(newPageSize){ // 当前要改变成x条/页，则为x
      this.searchFormData.pageSize = newPageSize;
      this.loadTable();
    },
    handlePageNowChange(newPageNow){ // 当前要前往的页码
      this.searchFormData.pageNow = newPageNow;
      this.loadTable();
    },
    doSearch(){
      this.loadTable();
    },
    loadTable(){
      this.$appJsonPost({
        url:'/saleOrder/loadTable',
        data:this.searchFormData
      }).then(result => {
        if (result.data === null || result.data === undefined) {
          result.data ={total:0, rows:[]}
        }
        this.tableData = result.data
      });
    },
    doRefund(){
      let orderNoList = [];
      this.selectedRows.forEach((crtValue,idx) => {
        orderNoList.push(crtValue["orderNo"]);
      });
      if (orderNoList.length === 0) {
        this.$message({
          type: 'error',
          message: '请先选择需要退款的订单'
        });
        return;
      }
      this.$appJsonPost({
        url:'/refund/add',
        data:{orderNoList}
      }).then(result => {
        this.$message({
          type: 'success',
          message: result.data
        });
        // 更新了销售单状态,需要重新加载下表格
        this.loadTable();
      }).catch(result => {
        this.$message({
          type: 'error',
          message: result.errMsg
        });
      });
    },
    handleSelectionChange(selectedRowsArr){
      this.selectedRows = selectedRowsArr;
    }
  },
  created() {
    this.loadTable();
  }
}
</script>

<style scoped>

</style>
