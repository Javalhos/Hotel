import { Router } from 'vue-fancy-router';
import { AuthGuard, GuestGuard } from './Guards';

const viewsHandler = component => require(`./pages/${component}`).default

const Route = new Router({ linkExactActiveClass: 'uk-active' }, viewsHandler)

Route.add('/', '').children(Route => {
	Route.group('auth', [ GuestGuard({ name: 'home' }) ], Route => {
		Route.add('signin', 'Auth/Signin', 'login')
		Route.add('signup', 'Auth/Signup', 'register')
	})

	Route.add('', 'Home', 'home')

	Route.add('quarto/:id', 'Customer/ReservarQuarto').guard(AuthGuard({ name: 'login' }))
})

export const routes = Route.build()
