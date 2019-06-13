<template>
	<div>
		<div class="uk-margin">
			<div class="uk-card uk-card-default uk-card-body" v-if="form.accomodationType === 'ALUGUEL'">
				<h3 class="uk-card-title">Confirme o CPF </h3>
				<div class="uk-margin">
					<label for="text" class="uk-form-label">Confirme o CPF do usuário para prosseguir com o registro da acomodação. Caso o 
					usuário ainda não estiver cadastrado, você será redirecionado à página de cadastros.</label>
					<div class="uk-form-controls">
						<input type="text" class="uk-input" id="text" placeholder="Ex.: 000.000.000-00" v-model="confcpf">
					</div>
				</div>
				<button class="uk-button uk-button-primary" @click="searchCpf">Efetuar confirmação</button>
			</div>
		</div>
		<div class="uk-child-width-1-2@m uk-grid-small uk-grid-match" uk-grid>
			<div>
				<div class="uk-card uk-card-default uk-card-body">
					<h3 class="uk-card-title">Registrar Acomodação</h3>
					<div class="uk-margin">
						<div class="uk-form-label">Selecione o tipo de registro</div>
						<div class="uk-form-controls">
							<label>
								<input class="uk-radio" 
									type="radio" 
									name="radio" 
									value="ALUGUEL" 
									v-model="form.accomodationType" 
									:checked="form.accomodationType === 'ALUGUEL' ? 'checked' : ''">
								Novo Aluguel
							</label>
							<br>
							<label>
								<input class="uk-radio" 
									type="radio" 
									value="RESERVA" 
									name="radio" 
									v-model="form.accomodationType" 
									:checked="form.accomodationType === 'RESERVA' ? 'checked' : ''"> 
								Reserva Efetuada Previamente
							</label>
						</div>
						<div class="uk-margin">
							<form class="uk-stacked">
								<div class="uk-margin">
									<label for="cpf" class="uk-form-label">CPF do Hóspede</label>
									<div class="uk-form-controls">
										<input type="text" class="uk-input" id="cpf" placeholder="Ex.: 000.000.000-00" v-model="form.cpf" disabled>
									</div>
								</div>
								<div class="uk-grid-small" uk-grid>
									<div class="uk-width-1-2">
										<label class="uk-form-label">Quarto</label>
										<input type="text" class="uk-input" placeholder="Número do quarto" v-model="form.room" disabled>
									</div>
									<div class="uk-width-1-2">
										<label class="uk-form-label">Tipo de Acomodação</label>
										<input type="text" class="uk-input" placeholder="Tipo da Acomodação" v-model="form.accomodationType" disabled>
									</div>
								</div>
								<div class="uk-grid-small" uk-grid>
									<div class="uk-width-1-2">
										<label for="begin" class="uk-form-label">Data de Entrada</label>
										<datepicker id="begin"
											:language="pt" 
											:formater="customFormatter"
											input-class="uk-input" 
											placeholder="Data de Entrada..."
											v-model="start"
										></datepicker>
									</div>
									<div class="uk-width-1-2">
										<label for="end" class="uk-form-label">Data de Saída</label>
										<datepicker id="end"
											:language="pt" 
											:formater="customFormatter"
											input-class="uk-input" 
											placeholder="Data de Saída..."
											v-model="end"
										></datepicker>
									</div>
								</div>
								<div class="uk-margin">
									<label class="uk-form-label">Valor das Diárias</label>
									<div class="uk-form-controls">
										<input type="text" class="uk-input" placeholder="Valor total das diárias" v-model="form.accValue" disabled>
									</div>
								</div>
								<button class="uk-button uk-button-primary" @click="registRental" :disabled="loading" v-if="form.accomodationType === 'ALUGUEL'">
									<span uk-spinner="ratio: 0.5" v-if="loading"></span>
									Criar aluguel
								</button>
								<button class="uk-button uk-button-primary" @click="bookingToRental" :disabled="loading" v-if="form.accomodationType === 'RESERVA'">
									<span uk-spinner="ratio: 0.5" v-if="loading"></span>
									Transformar em aluguel
								</button>
								<router-link to="/staff" tag="a" class="uk-button uk-button-default">Cancelar</router-link>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div class="uk-card uk-card-default uk-card-body" v-if="form.accomodationType === 'RESERVA'">
					<h3 class="uk-card-title">Procurar reserva</h3>
					<div class="uk-margin">
						<label for="cpf" class="uk-form-label">Insira o CPF e a reserva será retornada para você.</label>
						<div class="uk-form-controls">
							<input type="text" class="uk-input" id="cpf" placeholder="Ex.: 000.000.000-00" v-model="getcpf">
						</div>
					</div>
					<button class="uk-button uk-button-primary" @click="searchBooking">Procurar Reserva</button>
				</div>
				<div class="uk-margin" v-if="form.accomodationType === 'ALUGUEL'">
					<div class="uk-card uk-card-default uk-card-body">
						<form class="uk-grid-small" uk-grid>
							<div class="uk-width-1-1">
								<input type="text" class="uk-input" placeholder="Pesquisar por um tipo de quarto...">
							</div>
						</form>
						<list-room :rooms="rooms"></list-room>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import ListRoom from "../../../components/Rooms/ListRooms"
import { Form, Event } from '../../../common'
import moment from 'moment'
import Datepicker from 'vuejs-datepicker'
import { ptBr } from "vuejs-datepicker/dist/locale"
export default {
	components: { ListRoom, Datepicker },
	data() {
		return {
			loading: false,
			pt: ptBr,
			rooms: [],
			confcpf: '',
			diary: '',
			start: '',
			end: '',
			getcpf: '',
			bookingId: '',
			form: new Form({
				cpf: '',
				room: '',
				entryDate: '',
				departureDate: '',
				accValue: '',
				accomodationType: 'ALUGUEL',
				status: 'EM ANDAMENTO'
			}),
			payment:{
				accId: '',
				total: '',
				status: ''
			}
		}
	},
	async created() {
		const { data } = await this.$http.get('/api/rooms')
		this.rooms = data

		Event.listen('send-room', room => {
			this.form.room = room.room
			this.form.accValue = room.dailyRate
		})
	},
	methods: {
		customFormatter(date) {
			return moment(date).format('YYYY/MM/DD')
		},
		async searchCpf() {
			const { data } = await this.$http.get(`/user/${this.confcpf}`)

			if (data) {
				this.form.cpf = data.cpf
			} else {
				this.$router.push('/employee/users/create')
			}
		},
		async registRental() {
			this.form.entryDate = this.customFormatter(this.start)
			this.form.departureDate = this.customFormatter(this.end)
			let newD = moment(this.form.departureDate).diff(moment(this.form.entryDate), 'days')
			this.form.accValue = Number(this.form.accValue) * newD
			
			try {
				const { success } = await this.form.submit('post', '/accomodation') 
				let data = await this.getAcc()
				console.log(data)
				this.payment.accId = data.id
				this.payment.total = this.form.accValue
				this.payment.status = 'PENDENTE'

				this.registerPayment(this.payment)
				this.$router.push('/employee/accomodations')
			} catch ({ response, request, config }) {
				console.log(response)
				console.log(request)
				console.log(config)
			}
		},
		async searchBooking() {
			const { data } = await this.$http.get(`/booking/${this.getcpf}`)
			const booking = data[0]

			this.bookingId = booking.id
			this.form.accomodationType = booking.accomodationType
			this.form.cpf = booking.cpf
			this.start = booking.entryDate
			this.end = booking.departureDate
			this.form.room = booking.room
			this.form.status = booking.status
			this.form.entryDate = this.customFormatter(this.start)
			this.form.departureDate = this.customFormatter(this.end)
			let newD = moment(this.form.departureDate).diff(moment(this.form.entryDate), 'days')
			this.form.accValue = Number(booking.accValue) * newD
		},
		async bookingToRental() {
			this.loading = true
			this.form.status = 'EM ANDAMENTO'
			this.form.accomodationType = 'ALUGUEL'
			this.form.id = this.bookingId
			delete this.form.errors
			delete this.form.originalData

			console.log(this.form)
			try {
				const { success } = await this.$http.patch('/accomodation', this.form)
				let data = await this.getAcc()
				
				this.payment.accId = data.id
				this.payment.total = this.form.accValue
				this.payment.status = 'PENDENTE'

				this.registerPayment(this.payment)
			} catch ({ request, response, config}) {
				console.log(request)
				console.log(response)
				console.log(config)
			} finally {
				this.loading = false
			}
		},
		async registerPayment(payment) {
			const { success } = await this.$http.post('/payment', payment)
			this.$router.push('/employee/accomodations')
		},
		async getAcc() {
			const { data } = await this.$http.get(`/accomodation/${this.form.cpf}/${0}`)
			return data
		}	
	}
}
</script>
