import { Router } from 'vue-fancy-router';
import { AuthGuard, GuestGuard } from './Guards';

const viewsHandler = component => require(`./pages/${component}`).default

const Route = new Router(viewsHandler)

Route.add('/', '').children(Route => {
	Route.group('auth', [ GuestGuard({ name: 'home' }) ], Route => {
		Route.add('signin', 'Auth/Signin', 'login')
		Route.add('signup', 'Auth/Signup', 'register')
	})

	Route.add('quarto/:id', 'Customer/ReservarQuarto').guard(AuthGuard({ name: 'login' }))

	Route.add('', 'Home', 'home')
	Route.add('/staff', 'Staff', 'staff')
	Route.add('/staff/admin', 'Admin').children(Route => {
		Route.add('users', 'Admin/Users').children(Route => {
			Route.add('', 'Admin/Users/List')
			Route.add('create', 'Admin/Users/Create')
		})

		Route.add('accomodations', 'Admin/Accomodation')

		Route.add('rooms', 'Admin/Rooms').children(Route => {
			Route.add('', 'Admin/Rooms/List')
			Route.add('create', 'Admin/Rooms/Create')
		})

		Route.add('payments', 'Admin/Payments')
	})

	Route.add('/staff/employee', 'Employee').children(Route => {
		Route.add('users', 'Employee/Users').children(Route => {
			Route.add('', 'Employee/Users/List')
			Route.add('create', 'Employee/Users/Create')
		})

		Route.add('payment', 'Employee/Payment').children(Route => {
			Route.add('', 'Employee/Payment/List')
			Route.add('pay', 'Employee/Payment/Pay')
		})

		Route.add('acc', 'Employee/Accomodation').children(Route => {
			Route.add('', 'Employee/Accomodation/List')
			Route.add('create', 'Employee/Accomodation/Create')
		})

		Route.add('consumed', 'Employee/Consumed').children(Route => {
			Route.add('', 'Employee/Consumed/List')
			Route.add('create', 'Employee/Consumed/Create')
		})
	})
})

export const routes = Route.build()
console.log(routes)