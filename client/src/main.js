import Vue from 'vue'
import VueRouter from 'vue-router'

import UIkit from 'uikit'
import Icons from 'uikit/dist/js/uikit-icons'
import './assets/sass/app.scss'

import App from './App.vue'
import { Auth, Http } from './common'
import { routes } from './routes'


Vue.config.productionTip = false
Vue.use(VueRouter)
UIkit.use(Icons)

const main = async () => {

	Vue.prototype.$auth = Auth
	Vue.prototype.$http = Http

	await Auth.load()

	const app = new Vue({
		render: h => h(App),
		router: new VueRouter(routes)
	})
	app.$mount('#app')
}

main().catch(console.error)
