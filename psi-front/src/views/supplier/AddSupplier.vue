<template>
  <div>
    <el-dialog title="新增供应商" :visible="addDeptDialogVisible"
               :before-close="doBeforeClose"
               @close="closeFun"
      >
      <el-form :rules="rules" :model="formData" ref="addForm">
        <el-form-item label="供应商名称" label-width="120" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="onSubmit('addForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AddSupplier",
  data() {
    return {
      formData:{
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
    addDeptDialogVisible:{
      default: false
    }
  },
  methods: {
    handleCancel(){
      // 子组件向父组件传值,通过events up的方式
      this.$emit("update:addDeptDialogVisible",false);
    },
    onSubmit(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$appJsonPost({
            url:'/supplier/add',
            data:this.formData
          }).then(resultVO => {
            this.$emit("addDeptCloseAndReload");
          }).catch(resultVO=>{
            this.$message.error(resultVO.errMsg);
          });
        }
      });
    },
    doBeforeClose(){
      this.$emit("update:addDeptDialogVisible",false);
    },
    closeFun(){
      // element ui 表单的方法
      this.$refs["addForm"].resetFields();
    }
  }
}
</script>

<style>
</style>
