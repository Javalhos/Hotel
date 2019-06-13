<template>
	<div uk-grid>
		<div class="uk-width-1-1" v-if="loaded">
			<h1 class="uk-heading-small">Reservar {{ room.type | roomType }}</h1>
			<div uk-grid>
				<div class="uk-width-1-2">
					<div class="uk-card uk-card-small uk-card-default">
						<div class="uk-card-media-top">
							<img src="http://placehold.it/600x400">
						</div>
						<div class="uk-card-body">
							<p class="uk-text-lead">Número de Camas: {{ room.beds }}</p>
							<p class="uk-text-lead">Valor Diária: {{ `R$ ${room.dailyRate}` }}</p>
						</div>
					</div>
				</div>
				<div class="uk-width-1-2">
					<div class="uk-card uk-card-small uk-card-default uk-card-body">
						<p class="uk-text-lead">Olá <b>{{ $auth.user.name }}</b>, para concluir a reserva do quarto, preencha o formulário abaixo:</p>
						<hr class="uk-divider-icon">
						<form class="uk-form-stacked" v-if="loaded">
							<div class="uk-margin">
								<label class="uk-form-label">Selecione o intervalo de dias que deseja reservar</label>
								<div class="uk-form-controls">
									<div class="datepicker-trigger">
										<input type="text"
											id="datepicker-trigger"
											placeholder="DD/MM/YYYY - DD/MM/YYYY"
											:value="formatedDate"
											readonly
											class="uk-input">
	
										<AirbnbStyleDatepicker
											trigger-element-id="datepicker-trigger"
											mode="range"
											:date-one="dateOne"
											:date-two="dateTwo"
											@date-one-selected="val => { dateOne = val }"
											@date-two-selected="val => { dateTwo = val }"
										/>
									</div>
								</div>
							</div>
							<div class="uk-margin">
								<div class="uk-grid-small" uk-grid>
									<div class="uk-width-1-2">
										<label class="uk-form-	label">Total de dias</label>
										<div class="uk-form-controls">
											<input type="number" readonly class="uk-input uk-form-blank" :value="days">
										</div>
									</div>
									<div class="uk-width-1-2">
										<label class="uk-form-	label">Valor estimado da estadia</label>
										<div class="uk-form-controls">
											<input type="text" readonly class="uk-input uk-form-blank" :value="estimatedCost">
										</div>
									</div>
								</div>
							</div>
							<div class="uk-form-controls">
								<button class="uk-button uk-button-primary" type="button" @click="reserve" :disabled="reserving">
									<span uk-spinner="ratio: 0.5" v-if="reserving"></span>
									<span v-else>Reservar</span>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import moment from 'moment'

import { roomType } from '../../filters'
import { Utils } from "../../common/Utils"

export default {
	filters: { roomType },

	async created () {
		try {
			const { data } = await this.$http.get(`/api/rooms/${this.$route.params.id}`)

			this.room = data

			const { accomodations } = this.room

			const disabled = []
			for (const a of accomodations)
				disabled.push(...this.getDatesBetween(a.entryDate, a.departureDate))

			this.disabledDates = disabled
		} catch (errors) {
			if (errors.response && errors.response.status === 404)
				this.$router.go(-1)
		} finally {
			this.loaded = true
		}
	},

	computed: {
		estimatedCost () {
			let v
			if (!this.room)
				v = 0
			else
				v = this.room.dailyRate * this.days
			return Utils.toCurrency(v)
		},

		days () {
			const { dateOne, dateTwo } = this

			if (!dateOne || !dateTwo)
				return 0

			return 1 + Math.abs(moment(dateTwo).diff(moment(dateOne), 'days'))
		}, 
		formatedDate () {
			let d1, d2
			
			if (!this.dateOne || !this.dateTwo) 
				return ''

			d1 = moment(this.dateOne).format('DD/MM/YYYY')
			d2 = moment(this.dateTwo).format('DD/MM/YYYY')

			return `${d1} - ${d2}`
		}
	},

	methods: {
		getDatesBetween (start, end) {
			const range = []
			start = moment(start)
			end = moment(end)

			while (start <= end) {
				range.push(moment(start).format('YYYY-MM-DD'))
				start = moment(start).add(1, 'days')
			}
			return range
		},

		async reserve () {
			try {
				const { data } = await this.$http.post('/api/reservar', {
					room: this.room.room,
					cpf: this.$auth.user.cpf,
					entryDate: this.dateOne,
					departureDate: this.dateTwo
				})

				console.log("Resultado: ", data)
			} catch (error) {
				console.log(error.response, error.request, error.config)
			}
		}
	},

	data () {
		return {
			loaded: false,
			reserving: false,
			room: undefined,
			dateOne: '',
			dateTwo: '',
			disabledDates: []
		}
	}
}
</script>

<style>

</style>
