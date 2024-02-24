<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div style="height:500px;" ref="charts"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import * as echarts from 'echarts';

  export default {
    name: "Ret4Supplier",
    data() {
      return {
        pieChart:null,
        pieOption:{
          title: {
            text: '供应商退货占比统计',
            subtext: '统计口径:该供应商被退货次数/供应商退货总次数',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter:'{b}占比:{c}%'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: '供应商退货占比统计',
              type: 'pie',
              radius: '50%',
              data: [
                {value: 1048, name: '搜索引擎'},
                {value: 735, name: '直接访问'},
                {value: 580, name: '邮件营销'},
                {value: 484, name: '联盟广告'},
                {value: 300, name: '视频广告'}
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        }
      }
    },
    methods:{
      loadChart() {
        this.pieChart = echarts.init(this.$refs["charts"]);
        // 使用配置项和数据显示图表
        this.pieChart.setOption(this.pieOption);
        this.updateChart();
      },
      updateChart() {
        this.$appJsonPost({
          url:'/statistic/loadRet4Supplier'
        }).then(result => {
          this.pieOption.series = [{
            name: '供应商退货占比统计',
            data: result.data.seriesData
          }];
          // 设置真实数据
          this.pieChart.setOption(this.pieOption);
        })
      }
    },
    mounted() {
      this.loadChart();
    }
  }
</script>

<style scoped>

</style>
