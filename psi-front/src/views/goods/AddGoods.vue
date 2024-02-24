<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-form :rules="rules" :model="formData" label-width="100px"
                 label-position="right" ref="addForm">
          <el-form-item label="商品名:" prop="name">
            <el-input v-model="formData.name"></el-input>
          </el-form-item>
          <el-form-item label="产地:">
            <el-cascader
              size="large"
              :options="options"
              v-model="formData.bornPlaceCode"
              @change="handleRegionChange">
            </el-cascader>
          </el-form-item>
          <el-form-item label="销售价:" prop="salePrice">
            <!--默认input是string,需要加.number修饰符-->
            <el-input v-model="formData.salePrice">
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="单位:" prop="unit">
            <el-input v-model="formData.unit"></el-input>
          </el-form-item>
          <el-form-item label="规格:" prop="specifications">
            <el-input v-model="formData.specifications"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary"
                       size="mini"
                       @click="onSubmit('addForm')">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data';
export default {
  name: "AddGoods",
  data() {
    let validateNumber = (rule, value, callback) => {
      // 正实数校验
      let reg = /^[+]?(\d+)(\.\d+)?$/g;
      if (!value || value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入正实数'));
      } else {
        callback();
      }
    };
    return {
      formData: {
        name: '',
        bornPlace: '',// bornPlaceCode转换而来
        bornPlaceCode: [],
        salePrice: '',
        unit:'',
        specifications:''
      },
      options: regionData,
      rules: {
        name: [
          {required: true, message: '请输入商品名', trigger: 'blur'}
        ],
        salePrice: [
          {required: true, validator:validateNumber, trigger: 'blur'}
        ],
        unit: [
          {required: true, message: '请输入单位', trigger: 'blur'}
        ],
        specifications: [
          {required: true, message: '请输入规格', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // code转为文字
          this.formData.bornPlace = this.formData.bornPlaceCode
            .map((value, index) => {
                return CodeToText[value];
            }).join("/");
          this.$appJsonPost({
            url:'/goods/add',
            data:this.formData
          }).then(resultVO => {
            this.$router.replace("/goods/manage");
          }).catch(resultVO=>{
            this.$message.error(resultVO.errMsg);
          });
        }
      });
    },
    handleRegionChange(value){

    }
  }
}
</script>

<style>
</style>
