import { Auth } from '../common';

export const AuthGuard = redirectPath => ({ next, to }) => {
	if (Auth.isSignedIn())
		return next()

	if (typeof redirectPath !== 'object')
		return next(redirectPath)
	
	redirectPath.query = { redirect: to.path }
	next(redirectPath)
}

export const GuestGuard = redirectPath => ({ next }) => {
	if (Auth.isSignedIn())
		return next(redirectPath)
	next()
}
