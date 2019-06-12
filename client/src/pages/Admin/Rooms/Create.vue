<template>
	<div>
		<h1 class="uk-card-title">Novo quarto</h1>
		<form class="uk-stacked">
			<div class="uk-grid-small" uk-grid>
				<div class="uk-width-1-2">
					<label for="room" class="uk-form-label">Número do Quarto</label>
					<input type="text" id="room" class="uk-input" placeholder="Nº" v-model="form.room">
				</div>
				<div class="uk-width-1-2">
					<label for="beds" class="uk-form-label">Número de Camas</label>
					<input type="text" id="beds" class="uk-input" placeholder="Ex.: 2" v-model="form.beds">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="level">Tipo do Quarto</label>
				<div class="uk-form-controls">
					<select class="uk-select" id="level" v-model="form.type">
						<option value="NORMAL">Normal (Simples)</option>
						<option value="SUITE">Suite</option>
						<option value="LUXO">Luxo</option>
						<option value="PRESIDENCIAL">Presidencial</option>
					</select>
				</div>
			</div>
			<div class="uk-grid-small" uk-grid>
				<div class="uk-width-1-2">
					<label for="extp" class="uk-form-label">Ramal</label>
					<input type="text" id="extp" class="uk-input" placeholder="Ramal" v-model="form.extensionPhone">
				</div>
				<div class="uk-width-1-2">
					<label for="beds" class="uk-form-label">Preço da Diária</label>
					<input type="text" id="beds" class="uk-input" placeholder="Ex.: 100.00" v-model="form.dailyRate">
				</div>
			</div>
			<div class="uk-margin">
				<label class="uk-form-label" for="level">Status</label>
				<div class="uk-form-controls">
					<select class="uk-select" id="level" v-model="form.status">
						<option value="DISPONIVEL">Disponível</option>
						<option value="RESERVADO">Reservado</option>
						<option value="MANUTENCAO">Manutenção</option>
					</select>
				</div>
			</div>
			<button class="uk-button uk-button-primary" :disabled="loading" @click="create">
				<span uk-spinner="ratio: 0.5" v-if="loading"></span>
				Criar quarto
			</button>
		</form>
	</div>
</template>

<script>
import { Form } from '../../../common';
export default {
	data() {
		return {
			loading: false,
			form: new Form({
				room: '',
				beds: '',
				type: '',
				status: '',
				extensionPhone: '',
				dailyRate: ''
			})
		}
	},
	methods: {
		async create() {
			this.loading = true

			try {
				const { success } = await this.form.submit('post', '/api/room')
				console.log(success)

				if (success)
					this.$router.push('/admin')
			} catch ({ request, response, config }) {
				console.log(request)
				console.log(response)
				console.log(config)
			} finally {
				this.loading = false
			}
		}
	}
}
</script>

<style>

</style>
