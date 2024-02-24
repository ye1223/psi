<template>
  <div>
    <el-row>
      <el-col :span="18">
        <!--label-width:设置form-item的label的宽度,字符串类型,
                        不设置的话label和表单项不在一行-->
        <el-form :rules="rules" :model="formData" label-width="100px"
                 label-position="right" ref="addForm">
          <el-form-item label="用户姓名:" prop="userName">
            <el-input v-model="formData.userName"></el-input>
          </el-form-item>
          <el-form-item label="密码:" prop="pwd">
            <el-input v-model="formData.pwd" show-password clearable></el-input>
          </el-form-item>
          <el-form-item label="角色:">
            <el-select v-model="formData.roleId">
              <el-option v-for="(one) in formCfg.roles"
                         :label="one.roleName"
                         :value="one.roleId"
                         :key="one.roleId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('addForm')">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "AddUser",
  data() {
    return {
      formData: {
        userName: '',
        pwd: '',
        // 默认选中id=2的
        roleId: 2
      },
      formCfg:{
        roles:[]
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$appJsonPost({
            url:'/user/add',
            data:this.formData
          }).then(resultVO => {
            this.$router.replace("/user/manage");
          }).catch(resultVO=>{
            this.$message.error(resultVO.errMsg);
          });
        } else {
          return false;
        }
      });
    },
    listRoles(){
      this.$formGet({
        url:'/role/listRoles'
      }).then(result => {
        this.formCfg.roles = result.data;
      });
    }
  },
  created() {
    this.listRoles();
  }
}
</script>

<style>
</style>
