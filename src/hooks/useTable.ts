import { reactive } from 'vue'
import { appJsonPost } from '@/api/request'
import { TableData } from '@/ts/interfaces/general.interface'
import { SearchForm } from '@/ts/interfaces/user.interface'

interface HasId {
	id: number | string
}
const useTable = <T extends HasId>(config: { fetchUrl: string }) => {
	const tableData = reactive({ total: 0, rows: [] })
	const loadTable = async (
		searchFormData: SearchForm = {
			pageNow: 1,
			pageSize: 10,
			name: ''
		}
	) => {
		const { data } = await appJsonPost<SearchForm, TableData<T>>({
			url: config.fetchUrl,
			data: { ...searchFormData }
		})
		data && data.total
			? data.rows.sort((a, b) => Number(a.id) - Number(b.id))
			: (tableData.rows = [])
		Object.assign(tableData, data)
	}
	return { loadTable, tableData }
}

export default useTable
