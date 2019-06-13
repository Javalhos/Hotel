/* eslint-disable no-console */
import { Buffer } from 'buffer'

import { Http } from './Http';
import { Event } from './Event';

const TOKEN_KEY = 'hotel-auth-token'

export const Auth = {
	_token: undefined,
	
	_user: null,

	get user () {
		return this._user
	},

	async load () {
		if (!this.isSignedIn())
			return this.signOut()
		
		this._authenticate()

		try {
			await this.loadUser()
		} catch (error) {
			// suppress
			return this.signOut()
		}
	},

	set token (token) {
		this._token = token
		if (token)
			localStorage.setItem(TOKEN_KEY, token)
		else
			localStorage.removeItem(TOKEN_KEY)
	},

	get token () {
		this._token = localStorage.getItem(TOKEN_KEY) || undefined
		return this._token
	},

	isEmployee () {
		if (!this.user)
			return false
		return this.user.level === 'EMPLOYEE'
	},

	isAdmin () {
		if (!this.user)
			return false
		return this.user.level === 'ADMIN'
	},

	isSignedIn () {
		if (!this.token)
			return false
		return true
	},

	signOut () {
		this.token = undefined
		this._user = null
		Event.fire('auth-status-changed', { signedIn: false })
	},

	async loadUser () {
		const { data } = await Http.get('/api/auth/signin')
		this._user = data
	},

	async signIn ({ cpf, password }) {
		this.token = Buffer.from(`${cpf}:${password}`).toString('base64')
		
		// autentica as requisições
		this._authenticate()

		// Tenta pegar o usuário.
		try {
			await this.loadUser()
			Event.fire('auth-status-changed', { signedIn: true })
			return
		} catch (error) {
			const { response, request, config } = error
			const serverError = { server: 'Erro interno do sistema. Tente novamente mais tarde' }

			console.log(response)
			console.log(request)
			console.log(config)

			if (response) {
				if (response.status !== 400)
					throw serverError
				
				throw { credentials: 'CPF ou Senha inválidos.' }
			} else {
				throw serverError
			}
		}
	},

	_authenticate () {
		Http.defaults.headers.common['Authorization'] = this.token ? `Basic ${this.token}` : undefined;
	}
}
