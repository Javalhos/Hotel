<template>
	<div>
		<form class="uk-grid-small" uk-grid>
			<div class="uk-width-1-1">
				<input class="uk-input" type="text" placeholder="Pesquisar hospedagem...">
			</div>
			<div class="uk-width-1-1">
			</div>
		</form>
		<list-accomodation accomodations="accomodations"></list-accomodation>
	</div>
</template>

<script>
import ListAccomodation from "../../../components/ListAccomodation"
export default {
	data() {
		return {
			accomodations: []
		}
	},
	components: { ListAccomodation },
	async created() {
		const { data } = await this.$http.get('/accomodations')
		this.accomodations = data
	}
}
</script>

<style>

</style>
