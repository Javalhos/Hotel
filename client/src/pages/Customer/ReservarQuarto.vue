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
						<form class="uk-form-stacked">
							<div class="uk-margin">
								<div uk-grid class="uk-grid-small uk-child-width-1-2">
									<div>
										<label class="uk-form-label">Data de entrada</label>
										<div class="uk-form-controls">
											<datepicker input-class="uk-input"></datepicker>
										</div>
									</div>
									<div>
										<label class="uk-form-label">Data de saída</label>
										<div class="uk-form-controls">
											<datepicker input-class="uk-input"></datepicker>
										</div>
									</div>
								</div>
							</div>
							<div class="uk-margin">
								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Datepicker from 'vuejs-datepicker'

import { roomType } from '../../filters'

export default {
	filters: { roomType },

	components: { Datepicker },

	async created () {
		try {
			const { data } = await this.$http.get(`/api/rooms/${this.$route.params.id}`)

			this.room = data
		} catch (errors) {
			if (errors.response && errors.response.status === 404)
				return
		} finally {
			this.loaded = true
		}
	},

	data () {
		return {
			loaded: false,
			room: undefined
		}
	}
}
</script>

<style>

</style>
