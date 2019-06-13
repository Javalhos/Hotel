<template>
	<div>
		<table class="uk-table uk-table-middle uk-table-divider">
			<thead>
				<tr>
					<th>CPF</th>
					<th>Nome</th>
					<th>Número de Contato</th>
					<th>Acesso</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="user in onlyUsers" :key="user.cpf">
					<td>{{ user.cpf }}</td>
					<td>{{ user.name }}</td>
					<td>{{ user.email }}</td>
					<td>{{ user.contactNumber }}</td>
					<td>
						<div class="uk-button-group">
							<a href="#modal" class="uk-button uk-button-primary uk-button-small" uk-toggle @click="openUp(user)">
								<span uk-icon="icon: pencil"></span>
							</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="uk-margin">
			<router-link class="uk-button uk-button-primary" tag="a" to="/">Voltar</router-link>
		</div>
		<modal>
			<template v-slot:header>
				Editar Usuário
			</template>
			<template v-slot:body>
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
							<label for="address" class="uk-form-label">Endereço</label>
							<input type="text" id="address" placeholder="Endereço" class="uk-input" v-model="form.address">
						</div>
						<div class="uk-width-1-2">
							<label for="tel" class="uk-form-label">Número para Contato</label>
							<input type="text" id="tel" class="uk-input" placeholder="Número para Contato" v-model="form.contactNumber">
						</div>
					</div>
				</form>
			</template>
			<template v-slot:footer>
				<div class="uk-margin">
					<button class="uk-button uk-button-default uk-modal-close">Cancelar</button>
					<button class="uk-button uk-button-primary" @click="update" :disabled="loading">
						<span uk-spinner="ratio: 0.5" v-if="loading"></span>
						Salvar Alterações
					</button>
				</div>
			</template>
		</modal>
	</div>
</template>

<script>
import { Event, Form } from '../../common';
import Modal from "../Modal";
export default {
	props: ['users'],
	components: { Modal },
	data() {
		return {
			loading: false,
			form: new Form({
				name: '',
				cpf: '',
				email: '',
				address: '',
				contactNumber: '',
				level: 'USER'
			})
		}
	},
	methods: {
		async destroy (user) {
			const { success } = await this.$http.delete(`/user/${user.cpf}`)
			Event.fire('deleted-user', user)
		},
		openUp(user) {
			this.form.name = user.name
			this.form.cpf = user.cpf
			this.form.email = user.email
			this.form.address = user.address
			this.form.contactNumber = user.contactNumber
			this.form.level = user.level			
		},
		async update() {
			this.loading = true
			try {
				const { success } = await this.$http.patch('/user', this.form)
				console.log(success)
			} catch (e) {
				console.log(e)
			} finally {
				this.loading = false
			}
		}
	},
	computed: {
		onlyUsers() {
			return this.users.filter(u => {
				if (u.level === 'USER')
					return u
			})
		}
	}
}
</script>
