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
            <el-form-item label="按商品名搜索" label-width="120">
              <el-input v-model="searchFormData.name"></el-input>
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
          label="销售价(单位:元)">
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

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="handleDelete(scope.$index, scope.row)">删除
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
  name: "ManageGoods",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        name:''
      },
      tableData: {total:0,rows:[]}
    }
  },
  methods: {
    toAdd(){
      this.$router.replace("/goods/add")
    },
    handleDelete(index, row) {
      this.$confirm('确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$formGet({
          url:'/goods/deleteById',
          params:{"id":row.id}
        }).then(result => {
          this.loadTable();
        }).catch(result => {
          this.$message.error(result.errMsg);
        });
      }).catch(reject =>{});
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
    }
  },
  created() {
    this.loadTable();
  }
}
</script>

<style scoped>

</style>
