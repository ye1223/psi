import { RouteRecordRaw } from 'vue-router'
import Layout from '@/components/Layout.vue'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'Login',
        component: () => import('@/pages/Login.vue'),
        meta: {
            title: '登录'
        }
    },
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        children: [
            {
                path: 'supplier',
                component: () => import('@/pages/supplier/SupplierHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/supplier/ManageSupplier.vue'),
                        meta: {
                            title: '供应商管理'
                        }
                    },
                    {
                        path: 'add',
                        component: () => import('@/pages/supplier/AddSupplier.vue'),
                        meta: {
                            title: '新增供应商'
                        }
                    },
                    {
                        path: 'toUpdate',
                        component: () => import('@/pages/supplier/UpdateSupplier.vue'),
                        meta: {
                            title: '修改供应商'
                        }
                    }
                ]
            },
            {
                path: 'user',
                component: () => import('@/pages/user/UserHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/user/ManageUser.vue'),
                        meta: {
                            title: '用户管理'
                        }
                    },
                    {
                        path: 'add',
                        component: () => import('@/pages/user/AddUser.vue'),
                        meta: {
                            title: '新增用户'
                        }
                    },
                    {
                        path: 'toUpdate',
                        component: () => import('@/pages/user/UpdateUser.vue'),
                        meta: {
                            title: '修改用户'
                        }
                    }
                ]
            },
            {
                path: 'goods',
                component: () => import('@/pages/goods/GoodsHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/goods/ManageGoods.vue'),
                        meta: {
                            title: '商品管理'
                        }
                    },
                    {
                        path: 'add',
                        component: () => import('@/pages/goods/AddGoods.vue'),
                        meta: {
                            title: '新增商品'
                        }
                    }
                ]
            },
            {
                path: 'purchase',
                component: () => import('@/pages/purchase/PurchaseHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/purchase/ManagePurchase.vue'),
                        meta: {
                            title: '进货单管理'
                        }
                    },
                    {
                        path: 'add',
                        component: () => import('@/pages/purchase/AddPurchase.vue'),
                        meta: {
                            title: '进货单新增'
                        }
                    }
                ]
            },
            {
                path: 'returnGoods',
                component: () => import('@/pages/ret/ReturnGoodsHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/ret/ManageRet.vue'),
                        meta: {
                            title: '进货单退货管理'
                        }
                    }
                ]
            },
            {
                path: 'saleOrder',
                component: () => import('@/pages/sale/SaleOrderHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/sale/ManageSaleOrder.vue'),
                        meta: {
                            title: '销售单管理'
                        }
                    },
                    {
                        path: 'add',
                        component: () => import('@/pages/sale/AddSaleOrder.vue'),
                        meta: {
                            title: '销售单新增'
                        }
                    }
                ]
            },
            {
                path: 'refund',
                component: () => import('@/pages/refund/RefundHome.vue'),
                children: [
                    {
                        path: 'manage',
                        component: () => import('@/pages/refund/ManageRefund.vue'),
                        meta: {
                            title: '销售退款管理'
                        }
                    }
                ]
            },
            {
                path: 'audit',
                component: () => import('@/pages/audit/AuditHome.vue'),
                children: [
                    {
                        path: 'purchase',
                        component: () => import('@/pages/audit/AuditPurchase.vue'),
                        meta: {
                            title: '进货审批'
                        }
                    },
                    {
                        path: 'ret',
                        component: () => import('@/pages/audit/AuditRet.vue'),
                        meta: {
                            title: '进货退货审批'
                        }
                    }
                ]
            },
            {
                path: 'statistics',
                component: () => import('@/pages/statistics/StatisticsHome.vue'),
                children: [
                    {
                        path: 'purchasePrice4Goods',
                        component: () => import('@/pages/statistics/PurchasePrice4Goods.vue'),
                        meta: {
                            title: '商品进价波动'
                        }
                    },
                    {
                        path: 'saleNum4Goods',
                        component: () => import('@/pages/statistics/SaleNum4Goods.vue'),
                        meta: {
                            title: '商品购买量'
                        }
                    },
                    {
                        path: 'ret4Supplier',
                        component: () => import('@/pages/statistics/Ret4Supplier.vue'),
                        meta: {
                            title: '退货统计'
                        }
                    },
                    {
                        path: 'saleAmount4Goods',
                        component: () => import('@/pages/statistics/SaleAmount4Goods.vue'),
                        meta: {
                            title: '销售总额统计'
                        }
                    }
                ]
            },
            {
                path: 'index',
                component: () => import('@/pages/home/Index.vue'),
                meta: {
                    title: '首页'
                }
            }
        ]
    }
]

export default routes