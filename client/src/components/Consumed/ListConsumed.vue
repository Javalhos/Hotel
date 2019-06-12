<template>
	<table class="uk-table uk-table-divider">
		<thead>
			<tr>
				<th>Nº da Acomodação</th>
				<th>Nome do Serviço</th>
				<th>Valor</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="cns in consumed" :key="cns.id">
				<td>{{ cns.accId }}</td>
				<td>{{ cns.name }}</td>
				<td>{{ cns.value }}</td>
			</tr>
		</tbody>
	</table>
</template>

<script>
export default {
	props: ['consumed']
}
</script>
