import { Router } from 'vue-fancy-router';
import { AuthGuard, GuestGuard } from './Guards';

const viewsHandler = component => require(`./pages/${component}`).default

const Route = new Router({ linkExactActiveClass: 'uk-active' }, viewsHandler)

Route.add('/', '').children(Route => {
	Route.group('auth', [ GuestGuard({ name: 'home' }) ], Route => {
		Route.add('signin', 'Auth/Signin')
		Route.add('signup', 'Auth/Signup')
	})

	Route.add('', 'Home', 'home')
})

export const routes = Route.build()
