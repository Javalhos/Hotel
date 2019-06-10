import { Auth } from '../common';

export const AuthGuard = redirectPath => ({ next }) => {
	if (!Auth.isSignedIn())
		return next(redirectPath)
	next()
}

export const GuestGuard = redirectPath => ({ next }) => {
	if (Auth.isSignedIn())
		return next(redirectPath)
	next()
}
