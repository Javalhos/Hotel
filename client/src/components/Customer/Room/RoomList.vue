<template>
	<div class="uk-grid-large" uk-grid>
		<room-item v-for="room in rooms" :key="room.room" :room="room"></room-item>
	</div>
</template>

<script>
import RoomItem from './RoomItem'

export default {
	props: [ 'rooms' ],
	components: { RoomItem }
}
</script>

<style>

</style>
