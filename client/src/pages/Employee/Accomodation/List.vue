<template>
	<div class="uk-grid-small uk-child-width-1-1@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input type="text" class="uk-input" placeholder="Pesquisar por uma hospedagem...">
					</div>
				</form>
				<list-accomodation :accomodations="accomodations"></list-accomodation>
				<div class="uk-margin">
					<router-link tag="a" to="/" class="uk-button uk-button-primary">
						Voltar
					</router-link>
				</div>
			</div>
    	</div>
	</div>
</template>

<script>
import ListAccomodation from "../../../components/ListAccomodation";
export default {
	components: { ListAccomodation },
	data() {
		return {
			accomodations: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/accomodations')
		this.accomodations = data
	}
}
</script>

<style>

</style>
