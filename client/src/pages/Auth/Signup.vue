<template>
		<div class="uk-flex uk-flex-center">
			<div class="uk-card uk-card-body uk-card-default uk-width-1-2">
				<h1 class="uk-card-title">Criar conta</h1>
				<form class="uk-form-stacked">
					<div class="uk-margin">
						<label class="uk-form-label" for="name">Nome</label>
						<div class="uk-form-controls">
							<input type="text"
								class="uk-input"
								id="name"
								placeholder="Nome Completo"
								v-model="form.name">
						</div>
					</div>
					<div class="uk-margin">
						<label class="uk-form-label" for="email">Email</label>
						<div class="uk-form-controls">
							<input type="text"
								class="uk-input"
								id="email"
								placeholder="Ex.: seu@email.com"
								v-model="form.email">
						</div>
					</div>
					<div class="uk-margin">
						<div class="uk-grid-small" uk-grid>
							<div class="uk-width-1-2">
								<label class="uk-form-label" for="password">Senha</label>
								<div class="uk-form-controls">
									<input type="password" class="uk-input" id="password" v-model="form.password">
								</div>
							</div>
							<div class="uk-width-1-2">
								<label class="uk-form-label" for="password_confirmation">Confirmar Senha</label>
								<div class="uk-form-controls">
									<input type="password" class="uk-input" id="password_confirmation" v-model="form.password_confirmation">
								</div>
							</div>
						</div>
					</div>
					<div class="uk-margin">
						<label for="address" class="uk-form-label">Endereço Completo</label>
						<div class="uk-form-controls">
							<input type="text"
								class="uk-input"
								id="address"
								placeholder="Ex.: Logradouro, Nº, Bairro, Cidade - UF"
								v-model="form.address">
						</div>
					</div>
					<div class="uk-margin">
						<div class="uk-grid-small" uk-grid>
							<div class="uk-width-1-2">
								<label class="uk-form-label" for="cpf">CPF</label>
								<div class="uk-form-controls">
									<input type="text"
										class="uk-input"
										id="cpf"
										placeholder="Ex.: 000.000.000-00"
										v-model="form.cpf">
								</div>
							</div>
							<div class="uk-width-1-2">
								<label for="contactNumber" class="uk-form-label">Número de Contato</label>
								<div class="uk-form-controls">
									<input type="text"
										class="uk-input"
										id="contactNumber"
										placeholder="Telefone fixo ou celular"
										v-model="form.contactNumber">
								</div>
							</div>
						</div>
					</div>
					<button class="uk-button uk-button-primary" type="button" @click="register" :disabled="registering">
						<span uk-spinner="ratio: 0.5" v-if="registering"></span>
						<span v-else>Registrar</span>
					</button>
				</form>
			</div>
		</div>
</template>

<script>
import { Form, Errors, Auth } from '../../common';

export default {
	data () {
		return {
			registering: false,
			form: new Form({
				cpf: '',
				name: '',
				email: '',
				password: '',
				contactNumber: '',
				address: ''
			}),
			errors: new Errors
		}
	},

	methods: {
		async register () {
			this.registering = true
			const { cpf, password } = this.form.data()

			try {
				const { success } = await this.form.submit('post', '/api/auth/signup')

				if (!success)
					return alert('Erro!')
				
				try {
					await Auth.signIn({ cpf, password })
					this.$router.push('/')
				} catch (e) {
					console.log(e)
					this.$router.push('/auth/signin')
				}
			} catch ({ response, request, config }) {
				console.log(response)
				console.log(request)
				console.log(config)
			} finally {
				this.registering = false
			}
		}
	}
}
</script>

<style>

</style>
