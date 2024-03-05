import { VERIFIED_STATUS } from '@/ts/enums/input.enum'

const InputVerified = (isVerified: VERIFIED_STATUS) => {
	if (isVerified === VERIFIED_STATUS.UNVERIFIED) {
		return false
	}
	return true
}

export default InputVerified
