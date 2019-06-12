<template>
	<div class="uk-grid-small uk-child-width-1-2@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<h3 class="uk-card-title">Efetuar Pagamento</h3>
				<form class="uk-stacked">
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="name" class="uk-form-label">Número da Hospedagem</label>
							<input type="text" class="uk-input" placeholder="Número selecionado..." v-model="form.accId" disabled>
						</div>
						<div class="uk-width-1-2">
							<label for="cpf" class="uk-form-label">Número do Quarto</label>
							<input type="text" id="cpf" class="uk-input" placeholder="Número..." v-model="form.room" disabled>
						</div>
					</div>
					<div class="uk-margin">
						<label for="email" class="uk-form-label">Tipo de Hospedagem</label>
						<div class="uk-form-controls">
							<input type="text" class="uk-input" id="email" v-model="form.accType" placeholder="Tipo de Hospedagem..." disabled>
						</div>
					</div>
					<h4>Informações</h4>
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="name" class="uk-form-label">Valor de Serviços Consumidos</label>
							<input type="text" class="uk-input" placeholder="Total de Serviços Consumidos" v-model="form.services" disabled>
						</div>
						<div class="uk-width-1-2">
							<label for="cpf" class="uk-form-label">Taxa</label>
							<input type="text" id="cpf" class="uk-input" placeholder="Taxa..." v-model="form.tax" disabled>
						</div>
					</div>
					<h4>Referente ao Pagamento</h4>
					<div class="uk-margin">
						<label class="uk-form-label" for="level">Método de Pagamento</label>
						<div class="uk-form-controls">
							<select class="uk-select" id="level" v-model="form.type">
								<option value="CARTAO">Cartão</option>
								<option value="DINHEIRO">Dinheiro</option>
								<option value="BOLETO">Boleto</option>
							</select>
						</div>
					</div>
					<div class="uk-grid-small" uk-grid>
						<div class="uk-width-1-2">
							<label for="name" class="uk-form-label">Valor Total</label>
							<input type="text" class="uk-input" placeholder="Valor total..." v-model="total" disabled>
						</div>
						<div class="uk-width-1-2">
							<label class="uk-form-label" for="level">Valor que já foi pago</label>
							<input type="text" class="uk-input" placeholder="Valor a ser pago..." v-model="form.paid" disabled>
						</div>
					</div>
					<div class="uk-margin">
						<label class="uk-form-label" for="level">Valor a ser pago</label>
						<div class="uk-form-controls">
							<input type="text" class="uk-input" placeholder="Valor a ser pago..." v-model="paid">
						</div>
					</div>
					<div class="uk-margin">
						<button class="uk-button uk-button-primary" :disabled="registering" @click="register">
							<span uk-spinner="ratio: 0.5" v-if="registering"></span>
							<span v-else>Registrar</span>
						</button>
						<router-link to="/" tag="a" class="uk-button uk-button-default">
							Cancelar
						</router-link>
					</div>
				</form>
			</div>
    	</div>
		<div>
			<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input type="text" class="uk-input" placeholder="Pesquisar por uma hospedagem...">
					</div>
				</form>
				<list-payments :payments="payments" :button="true"></list-payments>
			</div>
		</div>
	</div>
</template>

<script>
import ListPayments from "../../../components/Payments/ListPayments"
import { Event, Form } from '../../../common'
export default {
	components: { ListPayments },
	data() {
		return {
			registering: false,
			payments: [],
			payType: 'NOVO',
			total: '',
			paid: '',
			form: {
				accId: '',
				tax: '',
				accType: '',
				room: '',
				services: '',
				status: '',
				paid: '',
				type: '',
				total: ''
			}
		}
	},
	async created() {
		const { data } = await this.$http.get('/payment/pendent')
		this.payments = data

		Event.listen('send-acc', acc => {
			this.form.accId = acc.accId
			this.form.services = acc.services
			this.form.tax = acc.tax
			this.form.type = acc.type
			this.form.status = acc.status
			this.form.total = acc.total
			this.form.accType = acc.accType
			this.form.paid = acc.paid
			this.form.room = acc.room
			this.form.paid = acc.paid
			this.total = this.form.total - this.form.paid
		})

		const { data: payment } = await this.$http.get()
	},
	methods: {
		async register() {
			this.registering = true

			try {
				this.form.paid = Number(this.form.paid) + Number(this.paid)

				if (this.total === 0 || this.total < 0)
					this.form.status = 'PAGO'

				if (this.paid > this.total)
					return alert('Você está pagando mais do que deve!')

				const { success } = await this.$http.patch('/payment', this.form)

				this.$router.push('/')
			} catch ({ request, response, config }) {
				console.log(request)
				console.log(response)
				console.log(config)
			} finally {
				this.registering = false
			}
		}
	}
}
</script>