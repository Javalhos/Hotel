import { Router } from 'vue-fancy-router';
import { AuthGuard, GuestGuard } from './Guards';

const viewsHandler = component => require(`./pages/${component}`).default

const Route = new Router(viewsHandler)

Route.add('/', '').children(Route => {
	Route.group('auth', [ GuestGuard({ name: 'home' }) ], Route => {
		Route.add('signin', 'Auth/Signin', 'login')
		Route.add('signup', 'Auth/Signup', 'register')
	})

	Route.add('admin', 'Admin').children(() => {
		Route.add('users', 'Admin/Users').children(() => {
			Route.add('', 'Admin/Users/List')
			Route.add('create', 'Admin/Users/Create')
		})

		Route.add('accomodations', 'Admin/Accomodation')

		Route.add('rooms', 'Admin/Rooms').children(() => {
			Route.add('', 'Admin/Rooms/List')
			Route.add('create', 'Admin/Rooms/Create')
		})

		Route.add('payments', 'Admin/Payments')
	})

	Route.add('employee', 'Employee').children(() => {
		Route.add('users', 'Employee/Users').children(() => {
			Route.add('', 'Employee/Users/List')
			Route.add('create', 'Employee/Users/Create')
		})

		Route.add('payment', 'Employee/Payment').children(() => {
			Route.add('', 'Employee/Payment/List')
			Route.add('pay', 'Employee/Payment/Pay')
		})

		Route.add('acc', 'Employee/Accomodation').children(() => {
			Route.add('', 'Employee/Accomodation/List')
			Route.add('create', 'Employee/Accomodation/Create')
		})
	})

	Route.add('', 'Home', 'home')
})

export const routes = Route.build()