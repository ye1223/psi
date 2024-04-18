import { appJsonPost } from '@/api/request'
import * as echarts from 'echarts'
import type {
	// 系列类型的定义后缀都为 SeriesOption
	BarSeriesOption,
	LineSeriesOption
} from 'echarts/charts'
import type {
	// 组件类型的定义后缀都为 ComponentOption
	TitleComponentOption,
	TooltipComponentOption,
	GridComponentOption,
	DatasetComponentOption
} from 'echarts/components'
import type { ComposeOption } from 'echarts/core'
export type ECOption = ComposeOption<
	| BarSeriesOption
	| LineSeriesOption
	| TitleComponentOption
	| TooltipComponentOption
	| GridComponentOption
	| DatasetComponentOption
>
// interface LoadTrend {
// 	legendData: []
// 	series: {
// 		name: string
// 		data: number[]
// 		type: string
// 		smooth: boolean
// 	}[]
// 	xAxisData: string[]
// }
interface Chart {
	chartType: 'pie' | 'line' | 'bar'
	chartOptions: ECOption
}
interface DataFetchOptions {
	url: string
	data?: any
}
const useChart = (
	el: HTMLDivElement,
	chart: Chart,
	dataFetchOptions: DataFetchOptions
) => {
	let trendChart: echarts.ECharts
	// 加载图表
	const loadTrend = () => {
		trendChart = echarts.init(el)
		// true:表示不进行合并,全部更新，老的删除
		trendChart.setOption(chart.chartOptions, true)
		updateTrend()
	}
	const updateTrend = () => {
		appJsonPost<any, any>({
			url: dataFetchOptions.url,
			data: dataFetchOptions.data
		}).then(result => {
			if (chart.chartType === 'line') {
				chart.chartOptions.legend = {
					data: result.data.legendData
				}
				chart.chartOptions.xAxis = { data: result.data.xAxisData }
				chart.chartOptions.series = result.data.series
				// 设置真实数据
				trendChart.setOption(chart.chartOptions, true)
			} else if (chart.chartType === 'bar') {
				chart.chartOptions.xAxis = {
					data: result.data.xAxisData
				}
				chart.chartOptions.series = [
					{
						name: 'series-rank',
						data: result.data.seriesData
					}
				]
				// false 或不设置这个参数，则新的配置会与旧的配置合并
				trendChart.setOption(chart.chartOptions, false)
			} else if ((chart.chartType = 'pie')) {
				chart.chartOptions.series = [
					{
						name: '供应商退货占比统计',
						data: result.data.seriesData
					}
				]
				trendChart.setOption(chart.chartOptions)
			}
		})
	}
	return { loadTrend, updateTrend }
}

export default useChart
