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
				<tr v-for="user in users" :key="user.cpf">
					<td>{{ user.cpf }}</td>
					<td>{{ user.name }}</td>
					<td>{{ user.contactNumber }}</td>
					<td v-if="user.level === 'ADMIN'">Administrador</td>
					<td v-else-if="user.level === 'EMPLOYEE'">Funcionário</td>
					<td v-else>Usuário</td>
					<td>
						<div class="uk-button-group">
							<a href="#modal" class="uk-button uk-button-primary uk-button-small" uk-toggle>
								<span uk-icon="icon: pencil"></span>
							</a>
							<div class="uk-button uk-button-danger uk-button-small" @click="destroy(user)">
								<span uk-icon="icon: trash"></span>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
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
					<div class="uk-margin">
						<label class="uk-form-label" for="level">Tipo de Acesso</label>
						<div class="uk-form-controls">
							<select class="uk-select" id="level" v-model="form.level">
								<option value="EMPLOYEE">Funcionário</option>
								<option value="ADMIN">Administrador</option>
							</select>
						</div>
					</div>
				</form>
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
			form: new Form({
				name: '',
				cpf: '',
				email: '',
				address: '',
				contactNumber: '',
				level: ''
			})
		}
	},
	methods: {
		async destroy (user) {
			const { success } = await this.$http.delete(`/user/${user.cpf}`)
			Event.fire('deleted-user', user)
		}
	}
}
</script>
