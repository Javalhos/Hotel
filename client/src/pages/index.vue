<template>
	<main>
		<navbar></navbar>
		<div class="uk-section">
			<div class="uk-container">
				<router-view></router-view>
			</div>
		</div>
	</main>
</template>

<script>
import Navbar from '../components/Navbar'

export default {
	components: { Navbar }
}
</script>

<style>

</style>
