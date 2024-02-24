<template>
  <div>
    <el-dialog title="选择商品" :visible="diaVisible"
               :before-close="doBeforeClose">
      <section class="title-bar">
        <el-row>
          <el-col :span="22">
            <el-form :model="searchFormData" :inline="true" size="small">
              <el-form-item label="按商品名搜索" label-width="120">
                <el-input v-model="searchFormData.name"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="doSearch">查询</el-button>
                <el-button type="primary" @click="confirmCheck">确认选择</el-button>
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
          ref="checkGoodsTable"
          @selection-change="handleSelectionChange"
          :highlight-current-row = "true"
          style="width: 100%">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="id"
            label="系统编号"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="商品名">
          </el-table-column>
          <el-table-column
            prop="bornPlace"
            label="产地">
          </el-table-column>
          <el-table-column
            prop="salePrice"
            label="销售价">
          </el-table-column>
          <el-table-column
            prop="stock"
            label="库存">
          </el-table-column>
          <el-table-column
            prop="unit"
            label="单位">
          </el-table-column>
          <el-table-column
            prop="specifications"
            label="规格">
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
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "CheckGoods",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        name:''
      },
      tableData: {total:0,rows:[]},
      selectedRow:null
    }
  },
  props:{
    diaVisible:{
      default: false
    },
    forRowIndex: {
      type : Number
    }
  },
  methods: {
    confirmCheck(){
      if (!this.selectedRow) {
        this.$message({
          type: 'error',
          message: '请选择商品后再确认'
        });
        return;
      }
      let selectedGoods = this.selectedRow;
      this.$emit("giveSelectedGoods",{
        rowIndex:this.forRowIndex,
        goodsId: selectedGoods["id"],
        goodsName: selectedGoods["name"],
        unit: selectedGoods["unit"],
        salePrice: selectedGoods["salePrice"],
      })
    },
    handleSelectionChange(selectedRowsArr){
      if (selectedRowsArr.length === 1) {
        this.selectedRow = selectedRowsArr[0];
      } else if (selectedRowsArr.length > 1) {
        // 清空所有已选中的
        this.$refs.checkGoodsTable.clearSelection();
        // 只选择最后一行
        let lastSelectedRow = selectedRowsArr.pop();
        this.$refs.checkGoodsTable.toggleRowSelection(lastSelectedRow);
        this.selectedRow = lastSelectedRow;
      }
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
        url:'/goods/loadTable',
        data:this.searchFormData
      }).then(result => {
        if (result.data === null || result.data === undefined) {
          result.data ={total:0, rows:[]}
        }
        this.tableData = result.data
      });
    },
    doBeforeClose(){
      this.$emit("update:checkGoodsVisible",false);
    }
  },
  created() {
    this.loadTable();
  }
}
</script>

<style scoped>

</style>
