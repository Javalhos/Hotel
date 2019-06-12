<template>
	<div>
		<form class="uk-grid-small" uk-grid>
			<div class="uk-width-1-1">
				<input class="uk-input" type="text" placeholder="Pesquisar quarto...">
			</div>
			<div class="uk-width-1-1">
			</div>
		</form>
		<list-rooms :rooms="rooms"></list-rooms>
	</div>
</template>

<script>
import ListRooms from "../../../components/Rooms/ListRooms";
export default {
	components: { ListRooms },
	data() {
		return {
			rooms: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/api/room')
		this.rooms = data
		console.log(data)
	}
}
</script>

<style>

</style>
