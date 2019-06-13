<template>
	<room-list :rooms="rooms"></room-list>
</template>

<script>
import RoomList from '../components/Customer/Room/RoomList'

export default {
	components: { RoomList },

	async created () {
		try {
			const { data } = await this.$http.get('/api/rooms')
			this.rooms = data
		} catch (err) {
			console.error(err);
			this.rooms = []
		}
	},

	data () {
		return {
			rooms: []
		}
	}
}
</script>

<style>

</style>

