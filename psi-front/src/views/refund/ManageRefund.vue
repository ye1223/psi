<template>
  <div>
    <section class="title-bar">
      <el-row>
        <el-col :span="22">
          <el-form :model="searchFormData" :inline="true" size="small">
            <el-form-item label-width="120">
              <el-input v-model="searchFormData.fuzzyRefundNo"
                        placeholder="按退款号模糊搜索"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="doSearch">查询</el-button>
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
        style="width: 100%">
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
                prop="refundPrice"
                label="退款价">
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
          label="所属订单号">
        </el-table-column>
        <el-table-column
          prop="refundNo"
          label="退款编号">
        </el-table-column>
        <el-table-column
          prop="totalAmount"
          label="总金额">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="退款日期">
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
  name: "ManageReturnGoods",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        fuzzyRefundNo:''
      },
      tableData: {total:0,rows:[]}
    }
  },
  methods: {
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
        url:'/refund/loadTable',
        data:this.searchFormData
      }).then(result => {
        if (result.data === null || result.data === undefined) {
          result.data ={total:0, rows:[]}
        }
        this.tableData = result.data
      });
    }
  },
  created() {
    this.loadTable();
  }
}
</script>

<style scoped>

</style>
