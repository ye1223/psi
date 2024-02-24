<template>
  <div class="login">
    <el-container>
      <el-main>
        <el-row class="mt-7">
          <el-col :span="6" :offset="9">
            <img src="../assets/login_bg.png" class="img-fluid"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" :offset="9">
            <el-form label-position="top"
              :rules="rules" :model="formData" ref="loginForm">
              <el-form-item prop="userName" label="用户名">
                <el-input  v-model="formData.userName" clearable>
                  <template slot="prepend"><i class="el-icon-user-solid"></i></template>
                </el-input>
              </el-form-item>
              <el-form-item prop="pwd" label="密码">
                <el-input  v-model="formData.pwd"
                          show-password clearable>
                  <template slot="prepend"><i class="el-icon-lock"></i></template>
                </el-input>
              </el-form-item>
              <el-form-item>
                <div class="form-btn">
                  <el-button type="success"
                             size="medium"
                             @click="login('loginForm')">
                    登录
                  </el-button>
                  <el-button type="primary"
                             size="medium"
                             @click="resetForm">
                    重置
                  </el-button>
                </div>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import {storeToken, storeUserInfo} from "../store/storage/storage";
import {STORE_USER_INFO} from "../store/mutations-type";

export default {
  name: 'Login',
  data() {
    return {
      formData: {
        userName: '',
        pwd: ''
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    resetForm(){
      this.formData.userName = "";
      this.formData.pwd = "";
    },
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$appJsonPost({
            url:'/user/login',
            data:this.formData
          }).then(resultVO => {
            storeToken(resultVO.data);
            this.$formGet({
              url: '/user/getUserByToken'
            }).then(result => {
              // 同步调用mutations的方法
              this.$store.commit(STORE_USER_INFO,result.data);
              // 会话存储
              storeUserInfo(result.data);
              // 存储完后再跳转,因为其他页面依赖了这些存储的值
              this.$router.replace("/index");
            });
          }).catch(resultVO => {
            this.$message.error(resultVO.errMsg);
          });
        }
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .form-btn{
    display: flex;
    justify-content: space-around;
  }
  .mt-7 {
    margin-top: 7rem;
  }
  h2{
    text-align: center;
  }
  .img-fluid {
    width: 100%;
  }
</style>
