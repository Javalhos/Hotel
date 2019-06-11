<template>
	<room-list :rooms="rooms"></room-list>
</template>

<script>
import RoomList from '../components/Customer/Room/RoomList';


export default {
	components: { RoomList },

	async created () {
		try {
			const { data } = await this.$http.get('/api/room')

			this.rooms = data
		} catch (e) {
			console.log(e)
			this.rooms = []
		}
	},

	filters: {  },

	data () {
		return {
			rooms: []
		}
	}
}
</script>

<style>

</style>
