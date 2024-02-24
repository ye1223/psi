<template>
  <div>
    <el-dialog title="编辑供应商"
               :visible="updateDeptDialogVisible"
               @open="doOpen"
               :before-close="doBeforeClose"
    >
      <el-form :rules="rules" :model="formData" ref="updateForm">
        <el-form-item label="编号" label-width="120">
          <el-input v-model="formData.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="名称" label-width="120">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="onSubmit('updateForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UpdateSupplier",
  data() {
    return {
      formData:{
        id:-1,
        name:''
      },
      rules: {
        name: [
          {required: true, message: '请输入供应商名', trigger: 'blur'}
        ]
      }
    }
  },
  props:{
    updateDeptDialogVisible:{
      default: false
    },
    idToUpdate:{
      type: Number
    }
  },
  methods: {
    handleCancel(){
      this.$emit("update:updateDeptDialogVisible",false);
    },
    onSubmit(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$appJsonPost({
            url:'/supplier/updateById',
            data:this.formData
          }).then(resultVO => {
            this.$emit("updateDeptCloseAndReload",false);
          }).catch(resultVO=>{
            this.$message.error(resultVO.errMsg);
          });
        }
      });
    },
    doOpen() {
      this.$formGet({
        url:'/supplier/getById',
        params:{"id":this.idToUpdate}
      }).then(result => {
        this.formData = result.data;
      });
    },
    doBeforeClose(){
      this.$emit("update:updateDeptDialogVisible",false);
    }
  }
}
</script>

<style scoped>
</style>
