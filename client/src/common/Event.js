import Vue from 'vue'

export const Event = {
	_vue:  new Vue,

	fire(event, ...data) {
		this._vue.$emit(event, ...data)
	},

	listen(event, callback) {
		this._vue.$on(event, callback)
	}
}
