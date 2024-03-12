<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Rules } from 'async-validator'
import Toast from '@/components/UI/Toast'
import Label from '@/components/UI/Label.vue'
import BackButton from '@/components/UI/BackButton.vue'
import { appJsonPost } from '@/api/request'
import useFormValidator from '@/hooks/useFormValidator'
import { GoodsInfo } from '@/ts/interfaces/goods.interface'

const form = ref<GoodsInfo>({
	id: -99,
	name: '',
	bornPlace: '',
	salePrice: '',
	stock: -999,
	unit: '',
	specifications: ''
})
const rules: Rules = {
	name: {
		type: 'string',
		required: true
	},
	salePrice: {
		type: 'string',
		required: true
	},
	unit: {
		type: 'string',
		required: true
	},
	specifications: {
		type: 'string',
		required: true
	}
}

const router = useRouter()
const handleAddGoods = async () => {
	const { pass, execute } = useFormValidator(rules, form)
	await execute()
	if (pass.value) {
		appJsonPost({
			url: '/goods/add',
			data: form.value
		})
			.then(_ => {
				Toast.success('添加成功')
				router.replace('/goods/manage')
			})
			.catch(resultVO => {
				Toast.error(resultVO.errMsg)
			})
	} else {
		Toast.error('校验失败')
	}
}
</script>

<template>
	<!-- <div>goods</div> -->

	<div class="max-w-sm mx-auto">
		<section class="mb-8">
			<BackButton
				@click="
					() => {
						router.back()
					}
				"
			/>
		</section>

		<section>
			<form>
				<div class="mb-5">
					<Label for="goodsname" required>商品</Label>
					<input
						id="goodsname"
						class="input-default"
						v-model="form.name"
					/>
				</div>
				<div class="mb-5">
					<label for="bornPlace" class="label-default">产地</label>
					<input
						id="bornPlace"
						class="input-default"
						v-model="form.bornPlace"
					/>
				</div>
				<div class="mb-5">
					<Label for="salePrice" required>销售价（￥元）</Label>
					<input
						id="salePrice"
						class="input-default"
						v-model="form.salePrice"
					/>
				</div>
				<div class="mb-5">
					<Label for="unit" required>单位</Label>
					<input
						id="unit"
						class="input-default"
						v-model="form.unit"
					/>
				</div>
				<div class="mb-5">
					<Label for="spec" required>规格</Label>
					<input
						id="spec"
						class="input-default"
						v-model="form.specifications"
					/>
				</div>
				<button
					type="button"
					class="button-normal"
					@click="handleAddGoods"
				>
					添加
				</button>
			</form>
		</section>
	</div>
</template>

<style scoped></style>
