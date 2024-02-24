<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-form :rules="rules" :model="formData" label-width="100px"
                 label-position="right" ref="updateForm">
          <el-form-item label="用户编号:">
            <el-input v-model="formData.id" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名:">
            <el-input v-model="formData.userName" disabled></el-input>
          </el-form-item>
          <el-form-item label="原始密码:" prop="oldPwd">
            <el-input v-model="formData.oldPwd" show-password clearable></el-input>
          </el-form-item>
          <el-form-item label="新密码:" prop="newPwd">
            <el-input v-model="formData.newPwd" show-password clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('updateForm')">确认修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "UpdateUser",
  data() {
    return {
      formData: {
        id:-1,
        userName:'',
        oldPwd: '',
        newPwd: ''
      },
      rules: {
        oldPwd: [
          {required: true, message: '请输入原始密码', trigger: 'blur'}
        ],
        newPwd: [
          {required: true, message: '请输入新密码', trigger: 'blur'}
        ]
      }
    }
  },
  computed:{
    queryParam() {
      // 获取查询字符串
      return this.$route.query;
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$appJsonPost({
            url:'/user/updatePwd',
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
    }
  },
  created() {
    this.$appJsonPost({
      url:'/user/getUserById',
      data:{"userId":this.queryParam.userId}
    }).then(result => {
      this.formData["id"] = result.data.id;
      this.formData["userName"] = result.data.userName;
    });
  }
}
</script>

<style scoped>
</style>
