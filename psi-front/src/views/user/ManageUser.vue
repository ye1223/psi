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
            <el-form-item>
              <el-input placeholder="按用户名搜索" v-model="searchFormData.userName"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" size="mini" @click="doSearch"
                icon="el-icon-search"
              >查询</el-button>
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
          prop="userName"
          label="用户名">
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)">修改密码
            </el-button>
            <!--管理员不显示删除-->
            <el-button
              v-if="scope.row.roleId !== 1"
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
  name: "ManageUser",
  data() {
    return {
      searchFormData:{
        pageNow:1,
        pageSize:10,
        userName:''
      },
      tableData: {total:0,rows:[]}
    }
  },
  methods: {
    toAdd(){
      this.$router.replace("/user/add")
    },
    handleEdit(index, row) {
      this.$router.replace({
        path:'/user/toUpdate',
        query:{
          'userId':row.id
        }
      })
    },
    handleDelete(index, row) {
      this.$confirm('确定要删除该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$formGet({
          url:'/user/deleteById',
          params:{"userId":row.id}
        }).then(result => {
          this.loadTable();
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
        url:'/user/loadTable',
        data:this.searchFormData
      }).then(resultVO => {
        this.tableData = resultVO.data
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
