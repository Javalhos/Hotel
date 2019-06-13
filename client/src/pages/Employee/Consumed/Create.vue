<template>
	<div class="uk-grid-small uk-child-width-1-2@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<h3 class="uk-card-title">Registrar Serviço Consumido</h3>
				<div class="uk-margin">
					<label class="uk-form-label" for="service">Tipo de Serviço</label>
					<div class="uk-form-controls">
						<select class="uk-select" id="service" v-model="service" @change="getService">
							<option value="1">Restaurante</option>
							<option value="2">Lavanderia</option>
							<option value="3">Academia</option>
							<option value="4">Balada</option>
							<option value="5">Sauna</option>
						</select>
					</div>
				</div>
				<div class="uk-grid-small" uk-grid>
					<div class="uk-width-1-2">
						<label for="value" class="uk-form-label">Valor do Serviço</label>
						<input type="text" id="value" placeholder="Valor do Serviço" class="uk-input" v-model="form.value" disabled>
					</div>
					<div class="uk-width-1-2">
						<label for="tel" class="uk-form-label">Número da Acomodação</label>
						<input type="text" id="tel" class="uk-input" placeholder="Número da Acomodação" v-model="form.accId" disabled>
					</div>
				</div>
				<div class="uk-margin">
					<button class="uk-button uk-button-primary" @click="registerConsume" :disabled="registering">
						<span uk-spinner="ratio: 0.5" v-if="registering"></span>
						<span v-else>Registrar consumo</span>
					</button>
					<router-link to="/staff" tag="a" class="uk-button uk-button-default">Cancelar</router-link>
				</div>
        	</div>
		</div>
		<div>
			<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input class="uk-input" type="text" placeholder="Pesquisar acomodação...">
					</div>
					<div class="uk-width-1-1">
					</div>
				</form>
				<table class="uk-table uk-table-divider">
					<thead>
						<tr>
							<th>Nº da Acomodação</th>
							<th>Quarto</th>
							<th>CPF do Hóspede</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="acc in accomodations" :key="acc.id">
							<td>{{ acc.id }}</td>
							<td>{{ acc.room }}</td>
							<td>{{ acc.cpf }}</td>
							<td>
								<button class="uk-button uk-button-small uk-button-primary" @click="getAcc(acc)">
									+
								</button>
							</td>
						</tr>
					</tbody>
				</table>
        	</div>
		</div>
	</div>
</template>

<script>
import { Form } from '../../../common'
export default {
	data() {
		return {
			registering: false,
			accomodations: [],
			service: '',
			form: new Form({
				value: '',
				serviceId: '',
				name: '',
				accId: ''
			})
		}
	},
	async created() {
		const { data } = await this.$http.get('/accomodations')
		this.accomodations = data
	},
	methods: {
		getAcc(acc) {
			this.form.accId = acc.id
		},
		async getService() {
			const { data } = await this.$http.get(`/service/${this.service}`)
			console.log(data)
			this.form.serviceId = data.id
			this.form.value = data.value
			this.form.name = data.name
		},
		async registerConsume() {
			try {
				const [ payment ] = await this.getPayment(this.form.accId)
				const { success } = await this.$http.post('/service', this.form)
				payment.services = Number(payment.services) + Number(this.form.value)
				const { success: suss } = await this.$http.patch('/payment', payment)
				console.log(suss)
			} catch (e) {
				console.log(e)
			}
		},
		async getPayment(id) {
			const { data } = await this.$http.get(`/accpay/${id}`)
			return data
		}
	}
}
</script>
