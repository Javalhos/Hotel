<template>
	<table class="uk-table uk-table-divider">
		<thead>
			<tr>
				<th>Nº do Quarto</th>
				<th>Tipo</th>
				<th>Camas</th>
				<th>Status</th>
				<th>Selecionar</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="room in rooms" :key="room.room">
				<td>{{ room.room }}</td>
				<td>{{ room.type }}</td>
				<td>{{ room.beds }}</td>
				<td v-if="room.status === 'DISPONIVEL'">Disponível</td>
				<td v-else-if="room.status === 'MANUTENCAO'">Manutenção</td>
				<td v-else>Reservado</td>
				<td>
					<button class="uk-button uk-button-primary uk-button-small" @click="sendRoom(room)">
						<span uk-icon="icon: plus-circle"></span>
					</button>
				</td>
			</tr>
		</tbody>
	</table>
</template>

<script>
import { Event } from '../../common'
export default {
	props: ['rooms'],
	methods: {
		sendRoom(room) {
			Event.fire('send-room', room)
		}
	}
}
</script>