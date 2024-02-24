<template>
  <div>
    <section class="title-bar">
      <el-row>
        <el-col :span="22">
          <el-form :model="searchFormData" :inline="true" size="small">
            <el-form-item label="按进货号模糊搜索" label-width="120">
              <el-input v-model="searchFormData.purchaseNo"></el-input>
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
                prop="buyPrice"
                label="采购价">
              </el-table-column>
              <el-table-column
                prop="num"
                label="数量">
              </el-table-column>
              <el-table-column
                prop="supplierName"
                label="供应商">
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
          prop="purchaseNo"
          label="进货编号">
        </el-table-column>
        <el-table-column
          prop="operatorName"
          label="经办人">
        </el-table-column>
        <el-table-column label="状态">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 0">待审核</span>
            <span v-if="scope.row.status === 1">审核通过</span>
            <span v-if="scope.row.status === 2">审核拒绝</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="purchaseDate"
          label="进货日期">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-edit"
              @click="handleAgree(scope.$index, scope.row)">通过
            </el-button>
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-edit"
              @click="handleReject(scope.$index, scope.row)">拒绝
            </el-button>
          </template>
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
        purchaseNo:'',
        status:0
      },
      tableData: {total:0,rows:[]},
      selectedRows:[]
    }
  },
  methods: {
    toAdd(){
      this.$router.replace("/purchase/add")
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
        url:'/purchase/loadTable',
        data:this.searchFormData
      }).then(result => {
        if (result.data === null || result.data === undefined) {
          result.data ={total:0, rows:[]}
        }
        this.tableData = result.data
      });
    },
    handleAgree(index,rowData){
      this.$appJsonPost({
        url:'/purchase/updateStatus',
        data:{"id":rowData["id"],"status":1}
      }).then(result => {
        this.loadTable();
      });
    },
    handleReject(index,rowData){
      this.$appJsonPost({
        url:'/purchase/updateStatus',
        data:{"id":rowData["id"],"status":2}
      }).then(result => {
        this.loadTable();
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
