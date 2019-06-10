<template>
		<div class="uk-flex uk-flex-center">
			<div class="uk-card uk-card-body uk-card-default uk-width-1-3">
				<h1 class="uk-card-title">Login</h1>
				<div class="uk-alert-danger" uk-alert v-if="errors.any()">
					<p v-if="errors.has('server')">{{ errors.get('server') }}</p>
					<p v-if="errors.has('credentials')">{{ errors.get('credentials') }}</p>
				</div>
				<form class="uk-form-stacked">
					<div class="uk-margin">
						<label class="uk-form-label" for="cpf">CPF</label>
						<div class="uk-form-controls">
							<input type="text"
								class="uk-input"
								id="cpf"
								placeholder="Ex.: 000.000.000-00"
								v-model="credentials.cpf">
						</div>
					</div>
					<div class="uk-margin">
							<label class="uk-form-label" for="password">Senha</label>
							<div class="uk-form-controls">
								<input type="password"
									class="uk-input"
									id="password"
									v-model="credentials.password">
							</div>
					</div>
					<button class="uk-button uk-button-primary" type="button" @click="login" :disabled="loading">
						<span uk-spinner="ratio: 0.5" v-if="loading"></span>
						<span v-else>Entrar</span>
					</button>
				</form>
			</div>
		</div>
</template>

<script>
import { Errors } from '../../common'

export default {
	data () {
		return {
			errors: new Errors,
			credentials: {
				cpf: '',
				password: '',
			},
			loading: false
		}
	},

	methods: {
		async login () {
			this.loading = true
			console.log('Hou')
			try {
				await this.$auth.signIn(this.credentials)
				console.log('hey')
				this.$router.push({ name: 'home' })
			} catch (errors) {
				console.log(errors)
				this.errors.record(errors)
			} finally {
				this.loading = false
			}
		}
	}
}
</script>

<style>

</style>
