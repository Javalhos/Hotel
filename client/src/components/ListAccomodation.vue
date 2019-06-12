<template>
	<table class="uk-table uk-table-divider">
		<thead>
			<tr>
				<th>Nº do Quarto</th>
				<th>CPF do Usuário</th>
				<th>Tipo de Acomodação</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="acc in accomodations" :key="acc.id">
				<td>{{ acc.room }}</td>
				<td>{{ acc.cpf }}</td>
				<td v-if="acc.accomodationType === 'ALUGUEL'">Aluguel</td>
				<td v-else>Reserva</td>
				<td v-if="acc.status === 'PENDENTE'">Pendente</td>
				<td v-else-if="acc.status === 'EM ANDAMENTO'">Em andamento</td>
				<td v-else>Cancelada</td>
			</tr>
		</tbody>
	</table>
</template>

<script>
export default {
	props: ['accomodations']
}
</script>
