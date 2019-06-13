<template>
	<div class="uk-margin">	
		<table class="uk-table uk-table-divider">
			<thead>
				<tr>
					<th>Nº Acomodação</th>
					<th>Total de Serviços Consumidos</th>
					<th>Total</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="pay in payments" :key="pay.id">
					<td>{{ pay.accId }}</td>
					<td>R$ {{ pay.services }}</td>
					<td>R$ {{ pay.total }}</td>
					<td v-if="pay.status === 'PAGO'">Pago</td>
					<td v-else-if="pay.status === 'PENDENTE'">Pendente</td>
					<td v-else-if="pay.status === 'Cancelado'">Cancelado</td>
					<td v-else>Problema</td>
				</tr>
			</tbody>
		</table>
		<div class="uk-margin">
			<router-link class="uk-button uk-button-primary" tag="a" to="/staff">Voltar</router-link>
		</div>
	</div>
</template>

<script>
export default {
	props: ['payments'],
	created() {
		
	}
}
</script>

<style>

</style>
