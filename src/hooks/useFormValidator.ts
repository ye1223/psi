// 需要输入 Rules
/* import { ref } from 'vue'
import type {
	Rules,
	Values,
	ValidateFieldsError
} from 'async-validator'
import Schema from 'async-validator'
import Toast from '@/components/UI/Toast' */

/* const useFormValidator = <T>(form: T, rules: Rules) => {
	const validator = new Schema(rules)
	// const errors = ref<ValidateError[] | null>(null)
	const fields = ref<Values | ValidateFieldsError>()

	validator.validate(form, (errors, validationFields) => {
		if (errors) {
			Toast.error(JSON.stringify(errors))
			// errors.value = validationErrors
		} else {
			fields.value = validationFields
		}
	})
	return {
		fields
	}
} */

// vueUse useAsyncValidator 简化版 https://github.com/vueuse/vueuse/blob/main/packages/integrations/useAsyncValidator/index.ts

import { ref, Ref } from 'vue'
import Schema from 'async-validator'
import type { Rules, ValidateError } from 'async-validator'

export interface FormValidatorError {
	errors: ValidateError[]
	fields: Record<string, ValidateError[]>
}

export interface UseFormValidatorReturn {
	pass: Ref<boolean>
	errors: Ref<FormValidatorError['errors'] | undefined>
	errorFields: Ref<FormValidatorError['fields'] | undefined>
	execute: () => Promise<void>
}

/**
 * 简化的async-validator包装函数，直接接收表单作为参数。
 */
export default function useFormValidator(
	rules: Rules,
	form: Ref<Record<string, any>>
): UseFormValidatorReturn {
	const pass = ref(true)
	const errors = ref<FormValidatorError['errors'] | undefined>(undefined)
	const errorFields = ref<FormValidatorError['fields'] | undefined>(
		undefined
	)

	const validator = new Schema(rules)

	const execute = async (): Promise<void> => {
		try {
			await validator.validate(form.value)
			pass.value = true
			errors.value = undefined
			errorFields.value = undefined
		} catch (err) {
			const validationError = err as FormValidatorError
			pass.value = false
			errors.value = validationError.errors
			errorFields.value = validationError.fields
		}
	}

	return {
		pass,
		errors,
		errorFields,
		execute
	}
}
