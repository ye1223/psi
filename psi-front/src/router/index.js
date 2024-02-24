import Vue from 'vue'
import Router from 'vue-router'
import Layout from "../components/Layout";
import UserHome from "../views/user/UserHome";
import ManageUser from "../views/user/ManageUser";
import AddUser from "../views/user/AddUser";
import UpdateUser from "../views/user/UpdateUser";
import SupplierHome from "../views/supplier/SupplierHome";
import ManageSupplier from "../views/supplier/ManageSupplier";
import AddSupplier from "../views/supplier/AddSupplier";
import UpdateSupplier from "../views/supplier/UpdateSupplier";
import GoodsHome from "../views/goods/GoodsHome";
import ManageGoods from "../views/goods/ManageGoods";
import AddGoods from "../views/goods/AddGoods";
import PurchaseHome from "../views/purchase/PurchaseHome";
import ManagePurchase from "../views/purchase/ManagePurchase";
import AddPurchase from "../views/purchase/AddPurchase";
import ReturnGoodsHome from "../views/ret/ReturnGoodsHome";
import ManageRet from "../views/ret/ManageRet";
import SaleOrderHome from "../views/sale/SaleOrderHome";
import ManageSaleOrder from "../views/sale/ManageSaleOrder";
import AddSaleOrder from "../views/sale/AddSaleOrder";
import RefundHome from "../views/refund/RefundHome";
import ManageRefund from "../views/refund/ManageRefund";
import AuditHome from "../views/audit/AuditHome";
import AuditPurchase from "../views/audit/AuditPurchase";
import AuditRet from "../views/audit/AuditRet";
import Login from "../views/Login";
import {IGNORE_URLS, ROLE_AUTH} from "../utils/constants";
import {drawToken, drawUserInfo} from "../store/storage/storage";
import StatisticsHome from "../views/statistics/StatisticsHome";
import PurchasePrice4Goods from "../views/statistics/PurchasePrice4Goods";
import SaleAmount4Goods from "../views/statistics/SaleAmount4Goods";
import Ret4Supplier from "../views/statistics/Ret4Supplier";
import SaleNum4Goods from "../views/statistics/SaleNum4Goods";
import Index from "../views/home/Index";

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      meta:{
        title:'登录'
      }
    },
    {
      path: '/',
      name: 'Layout',
      component: Layout,
      children: [
        {
          path: 'supplier',
          component: SupplierHome,
          children: [
            {
              path: 'manage',
              component: ManageSupplier,
              meta:{
                title:'供应商管理'
              }
            },
            {
              path: 'add',
              component: AddSupplier,
              meta:{
                title:'新增供应商'
              }
            },
            {
              path: 'toUpdate',
              component: UpdateSupplier,
              meta:{
                title:'修改供应商'
              }
            }
          ]
        },
        {
          path: 'user',
          component: UserHome,
          children: [
            {
              path: 'manage',
              component: ManageUser,
              meta:{
                title:'用户管理'
              }
            },
            {
              path: 'add',
              component: AddUser,
              meta:{
                title:'新增用户'
              }
            },
            {
              path: 'toUpdate',
              component: UpdateUser,
              meta:{
                title:'修改用户'
              }
            }
          ]
        },
        {
          path: 'goods',
          component: GoodsHome,
          children: [
            {
              path: 'manage',
              component: ManageGoods,
              meta:{
                title:'商品管理'
              }
            },
            {
              path: 'add',
              component: AddGoods,
              meta:{
                title:'新增商品'
              }
            }
          ]
        },
        {
          path: 'purchase',
          component: PurchaseHome,
          children: [
            {
              path: 'manage',
              component: ManagePurchase,
              meta:{
                title:'进货单管理'
              }
            },
            {
              path: 'add',
              component: AddPurchase,
              meta:{
                title:'进货单新增'
              }
            }
          ]
        },
        {
          path: 'returnGoods',
          component: ReturnGoodsHome,
          children: [
            {
              path: 'manage',
              component: ManageRet,
              meta:{
                title:'进货单退货管理'
              }
            }
          ]
        },
        {
          path: 'saleOrder',
          component: SaleOrderHome,
          children: [
            {
              path: 'manage',
              component: ManageSaleOrder,
              meta:{
                title:'销售单管理'
              }
            },
            {
              path: 'add',
              component: AddSaleOrder,
              meta:{
                title:'销售单新增'
              }
            }
          ]
        },
        {
          path: 'refund',
          component: RefundHome,
          children: [
            {
              path: 'manage',
              component: ManageRefund,
              meta:{
                title:'销售退款管理'
              }
            }
          ]
        },
        {
          path: 'audit',
          component: AuditHome,
          children: [
            {
              path: 'purchase',
              component: AuditPurchase,
              meta:{
                title:'进货审批'
              }
            },
            {
              path: 'ret',
              component: AuditRet,
              meta:{
                title:'进货退货审批'
              }
            }
          ]
        },
        {
          path: 'statistics',
          component: StatisticsHome,
          children: [
            {
              path: 'purchasePrice4Goods',
              component: PurchasePrice4Goods,
              meta:{
                title:'商品进价波动'
              }
            },
            {
              path: 'saleNum4Goods',
              component: SaleNum4Goods,
              meta:{
                title:'商品购买量'
              }
            },
            {
              path: 'ret4Supplier',
              component: Ret4Supplier,
              meta:{
                title:'退货统计'
              }
            },
            {
              path: 'saleAmount4Goods',
              component: SaleAmount4Goods,
              meta:{
                title:'销售总额统计'
              }
            }
          ]
        },
        {
          path: 'index',
          component: Index,
          meta:{
            title:'首页'
          }
        }
      ]
    }
  ]
})

// 权限控制
router.beforeEach((toRoute,fromRoute,next) => {
  let targetTitle = toRoute.matched[toRoute.matched.length - 1].meta.title;
  console.log(toRoute)
  console.log(targetTitle)
  // toRoute.path:/user/manage
  if (IGNORE_URLS.includes(toRoute.path)) {
    if (targetTitle) {
      document.title = targetTitle;
    }
    // 说明当前轻轻的路径不需要拦截
    return next();
  } else {
    if (drawToken()) {
      let authUrls = ROLE_AUTH[drawUserInfo().roleId];
      if (!authUrls) {
        return next({path:'/'});
      }
      // 检查配置的权限数组
      if (authUrls.includes("*")) { // 超级角色
        if (targetTitle) {
          document.title = targetTitle;
        }
        return next();
      } else {
        let match = false;
        // ['/purchase',"/returnGoods"]
        for (let item of authUrls) {
          if (toRoute.path.indexOf(item) > -1) {
            match = true;
            // arr.forEach不能匹配后break,return无效的,这里用for of
            break;
          }
        }
        if (match) {
          if (targetTitle) {
            document.title = targetTitle;
          }
          return next();
        }
      }
    }
    // 无权限指定跳转到哪里
    return next({path:'/'});
  }
});
export default router
