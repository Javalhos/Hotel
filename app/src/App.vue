<template>
	<div>
		<h1>Hello World :D</h1>
		<ul>
			<li v-for="v in values" :key="v">Item {{ v }}</li>
		</ul>
	</div>
</template>

<script>
import axios from "axios"

export default {
	data() {
		return {
			values: [
				1, 2, 3, 4, 5
			]
		}
	}
}
</script>

<style>

</style>
