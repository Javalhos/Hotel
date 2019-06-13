<template>
	<div class="uk-grid-small uk-child-width-1-2@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<h3 class="uk-card-title">Registrar Novo Usuário</h3>
				<form class="uk-stacked">
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="name" class="uk-form-label">Nome</label>
							<input type="text" class="uk-input" placeholder="Nome completo" v-model="form.name">
						</div>
						<div class="uk-width-1-2">
							<label for="cpf" class="uk-form-label">CPF</label>
							<input type="text" id="cpf" class="uk-input" placeholder="Ex.: 000.000.000-00" v-model="form.cpf">
						</div>
					</div>
					<div class="uk-margin">
						<label for="email" class="uk-form-label">E-mail</label>
						<div class="uk-form-controls">
							<input type="email" class="uk-input" id="email" placeholder="Ex.: seu@email.com" v-model="form.email">
						</div>
					</div>
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="password" class="uk-form-label">Senha</label>
							<input type="password" id="password" class="uk-input" v-model="form.password">
						</div>
						<div class="uk-width-1-2">
							<label for="conf-senha" class="uk-form-label">Confirmar Senha</label>
							<input type="password" id="conf-senha" class="uk-input" v-model="confSenha">
						</div>
					</div>
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="address" class="uk-form-label">Endereço</label>
							<input type="text" id="address" placeholder="Endereço" class="uk-input" v-model="form.address">
						</div>
						<div class="uk-width-1-2">
							<label for="tel" class="uk-form-label">Número para Contato</label>
							<input type="text" id="tel" class="uk-input" placeholder="Número para Contato" v-model="form.contactNumber">
						</div>
					</div>
					<div class="uk-margin">
						<button class="uk-button uk-button-primary" @click="register" :disabled="registering">
							<span uk-spinner="ratio: 0.5" v-if="registering"></span>
							<span v-else>Registrar</span>
						</button>
						<router-link to="/staff" tag="a" class="uk-button uk-button-default">
							Cancelar
						</router-link>
					</div>
				</form>
			</div>
    	</div>
	</div>
</template>

<script>
import { Form } from '../../../common';
export default {
	data() {
		return {
			registering: false,
			confSenha: '',
			form: new Form({
				name: '',
				email: '',
				cpf: '',
				password: '',
				address: '',
				contactNumber: ''
			})
		}
	},
	methods: {
		async register() {
			this.registering = true
			
			try {
				if (this.confSenha !== this.form.data().password)
					return alert("Erro! As senhas devem ser iguais!")

				const { success } = await this.form.submit('post', '/api/auth/signup')

				if (!success)
					return alert('Erro!')
				
				this.$router.push('/staff/employee/users')
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
