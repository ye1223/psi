<template>
  <el-container class="layout-container">
    <el-header height="60px">
      <el-row style="height: 60px">
        <el-col style="height: 60px" :span="10">
          <img src="../assets/banner.png" class="img-fluid"/>
        </el-col>
        <el-col :span="3" class="f-right" style="text-align: right;line-height: 60px">
          欢迎,{{userName}}
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="/index"
          text-color="#fff"
          active-text-color="#ffd04b"
          :unique-opened = "true"
        >
          <router-link to="/index" tag="div">
            <el-menu-item index="/index">
              <i class="el-icon-s-home"></i>
              <span slot="title">首页</span>
            </el-menu-item>
          </router-link>
          <!--
          roleId:
          1:超级管理员，有所有权限
          2: 进出仓
          3: 销售
          4: 财务
          -->
          <el-submenu index="1" v-if="roleId === 1">
            <template slot="title">
              <i class="el-icon-info"></i>
              <span>基础资料</span>
            </template>
            <!--用router-link包裹,实现菜单的跳转,指定tag避免默认的a有下划线-->
            <router-link to="/user/manage" tag="div">
              <el-menu-item index="1-1">
                <i class="el-icon-user"></i>
                <span slot="title">员工管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/supplier/manage" tag="div">
              <el-menu-item index="1-2">
                <i class="el-icon-folder-opened"></i>
                <span slot="title">供应商管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/goods/manage" tag="div">
              <el-menu-item index="1-3">
                <i class="el-icon-goods"></i>
                <span slot="title">商品管理</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
          <el-submenu index="2" v-if="roleId === 1 || roleId ===2">
            <template slot="title">
              <i class="el-icon-s-operation"></i>
              <span>进退货</span>
            </template>
            <router-link to="/purchase/manage" tag="div">
              <el-menu-item index="2-1">
                <i class="el-icon-d-arrow-right"></i>
                <span slot="title">进货管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/returnGoods/manage" tag="div">
              <el-menu-item index="2-2">
                <i class="el-icon-d-arrow-left"></i>
                <span slot="title">退货管理</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
          <el-submenu index="3" v-if="roleId === 1 || roleId === 3">
            <template slot="title">
              <i class="el-icon-monitor"></i>
              <span>销售与退款</span>
            </template>
            <router-link to="/saleOrder/manage" tag="div">
              <el-menu-item index="3-1">
                <i class="el-icon-shopping-cart-full"></i>
                <span slot="title">销售管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/refund/manage" tag="div">
              <el-menu-item index="3-2">
                <i class="el-icon-connection"></i>
                <span slot="title">退款管理</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
          <el-submenu index="4" v-if="roleId === 1 || roleId === 4">
            <template slot="title">
              <i class="el-icon-coordinate"></i>
              <span>账务审核</span>
            </template>
            <router-link to="/audit/purchase" tag="div">
              <el-menu-item index="4-1">
                <i class="el-icon-folder-checked"></i>
                <span slot="title">进货审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/audit/ret" tag="div">
              <el-menu-item index="4-2">
                <i class="el-icon-folder-checked"></i>
                <span slot="title">退货审核</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
          <el-submenu index="5" v-if="roleId === 1">
            <template slot="title">
              <i class="el-icon-s-data"></i>
              <span>统计分析</span>
            </template>
            <router-link to="/statistics/purchasePrice4Goods" tag="div">
              <el-menu-item index="5-1">
                <i class="el-icon-data-line"></i>
                <span slot="title">商品进价波动</span>
              </el-menu-item>
            </router-link>
            <router-link to="/statistics/saleNum4Goods" tag="div">
              <el-menu-item index="5-2">
                <i class="el-icon-shopping-cart-full"></i>
                <span slot="title">商品购买量</span>
              </el-menu-item>
            </router-link>
            <router-link to="/statistics/ret4Supplier" tag="div">
              <el-menu-item index="5-3">
                <i class="el-icon-pie-chart"></i>
                <span slot="title">供应商退货占比</span>
              </el-menu-item>
            </router-link>
            <router-link to="/statistics/saleAmount4Goods" tag="div">
              <el-menu-item index="5-4">
                <i class="el-icon-postcard"></i>
                <span slot="title">销售总额统计</span>
              </el-menu-item>
            </router-link>
          </el-submenu>
          <div @click="logout">
            <el-menu-item index="6">
              <i class="el-icon-unlock"></i>
              <span slot="title">退出</span>
            </el-menu-item>
          </div>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import {clearStorage, drawUserInfo} from "../store/storage/storage";
  import {STORE_USER_INFO} from "../store/mutations-type";

  export default {
    name: "Layout",
    computed: {
      roleId() {
        return this.$store.getters.userInfo.roleId;
      },
      userName() {
        return this.$store.getters.userInfo.userName;
      }
    },
    methods: {
      logout() {
        clearStorage();
        this.$router.replace("/");
      }
    },
    created() {
      if (!this.$store.state.userInfo || this.$store.state.userInfo.userId === -1) {
        let storedUser = drawUserInfo();
        if (storedUser) {
          this.$store.commit(STORE_USER_INFO, storedUser);
        }
      }
    }
  }
</script>

<style scoped>
  .layout-container {
    height: 100%;
  }

  .el-header {
    background-color: #0a366b;
    color: #fff;
    padding-left: 0;
  }

  .el-aside {
    background-color: #003c7f;
  }

  .el-main {
    padding: 10px;
  }

  .img-fluid {
    height: 100%;
    max-height: 100%;
  }

  .f-right {
    float: right;
  }
  /*避免 el-menu-item 多1px*/
  .el-menu {
    border-right-width: 0;
  }
  /* 侧边栏背景 */
  aside ul,aside li {
    background-color: #003c7f;
  }
  /*侧边栏悬浮时的背景*/
  /*css修改element-ui样式不生效,通过>>>深层穿透指定;否则需要去掉scoped*/
  li.el-submenu >>> .el-submenu__title:hover {
    background-color: rgb(0, 48, 102) !important;
  }
  .el-menu-item:focus, .el-menu-item:hover {
    background-color: rgb(0, 48, 102);
  }

  /*菜单激活时的背景*/
  .el-menu-item:focus,.el-menu-item.is-active {
    background-color: rgb(0, 48, 102);
  }
</style>
