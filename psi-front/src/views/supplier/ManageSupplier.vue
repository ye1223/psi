<template>
  <div>
    <section class="title-bar">
      <el-row>
        <el-col :span="2">
          <el-button @click="openAddDia" type="primary"
                     size="small"
                     icon="el-icon-plus">
            新增
          </el-button>
        </el-col>
        <el-col :span="22">
          <el-form :model="searchFormData" :inline="true" size="small">
            <el-form-item label="供应商名称" label-width="120">
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
          label="供应商名称">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
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
      <!--:current-page默认1,:page-size默认10-->
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
    <!--父组件跟监听普通事件一样,通过v-on来监听子组件发射的自定义事件即可-->
    <add-dept :addDeptDialogVisible.sync="addDeptDialogVisible"
              @addDeptCloseAndReload="handleAddDeptCloseAndReload">
    </add-dept>
    <update-dept :updateDeptDialogVisible.sync="updateDeptDialogVisible"
                 :idToUpdate="idToUpdate"
                 @updateDeptCloseAndReload="handleUpdateDeptCloseAndReload">
    </update-dept>
  </div>
</template>

<script>
import AddDept from "./AddSupplier";
import UpdateDept from "./UpdateSupplier";

export default {
  name: "ManageSupplier",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        name:''
      },
      addDeptDialogVisible: false,
      updateDeptDialogVisible: false,
      idToUpdate:-1,
      tableData: {total:0, rows:[]}
    }
  },
  methods: {
    openAddDia() {
      this.addDeptDialogVisible = true;
    },
    handleAddDeptCloseAndReload(){
      this.addDeptDialogVisible = false;
      this.loadTable();
    },
    handleUpdateDeptCloseAndReload(){
      this.updateDeptDialogVisible = false;
      this.loadTable();
    },
    handleEdit(index, row) {
      this.idToUpdate = row.id;
      this.updateDeptDialogVisible = true;
    },
    handleDelete(index, row) {
      this.$confirm('确认要删除供应商吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$formGet({
          url:'/supplier/deleteById',
          params:{"id":row.id}
        }).then(result => {
          this.$message({
            type: 'success',
            message: result.data
          });
          this.loadTable();
        });
      }).catch(() => {
      });
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
        url:'/supplier/loadTable',
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
  },
  components: {
    UpdateDept,
    AddDept
  }
}
</script>

<style scoped>
</style>
