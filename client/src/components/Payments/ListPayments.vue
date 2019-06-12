<template>
	<table class="uk-table uk-table-divider">
		<thead>
			<tr>
				<th>Acomodação</th>
				<th>Total</th>
				<th>Pago</th>
				<th>Status</th>
				<th v-if="button">Ações</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="pay in payments" :key="pay.id">
				<td>{{ pay.accId }}</td>
				<td>R$ {{ pay.total - pay.paid}}</td>
				<td>R$ {{ pay.paid }}</td>
				<td>{{ pay.status }}</td>
				<td v-if="button">
					<button class="uk-button uk-button-primary uk-button-small" :disabled="pay.status === 'PAGO'" @click="getAcc(pay)">
						+
					</button>
				</td>
			</tr>
		</tbody>
	</table>
</template>

<script>
import { Event } from '../../common'
export default {
	props: ['payments', 'button'],
	data() {
		return {
		}
	},
	methods: {
		async getAcc(acc) {
			const { data } = await this.$http.get(`/accomodation/${null}/${acc.accId}`, { cpf: '', id: acc.accId}) 
			
			acc.accType = data.accomodationType
			acc.room = data.room
			console.log('Oi')
			console.log(acc)
			Event.fire('send-acc', acc)
		}
	}
}
</script>
