<template>
	<div class="uk-grid-small uk-child-width-1-1@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input type="text" class="uk-input" placeholder="Pesquisar por um serviço consumido...">
					</div>
				</form>
				<list-consumed :consumed="consumed"></list-consumed>
				<div class="uk-margin">
					<router-link tag="a" to="/staff" class="uk-button uk-button-primary">
						Voltar
					</router-link>
				</div>
			</div>
    	</div>
	</div>
</template>

<script>
import ListConsumed from "../../../components/Consumed/ListConsumed"
export default {
	components: { ListConsumed },
	data() {
		return {
			consumed: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/consumeds')
		this.consumed = data
	}
}
</script>

<style>

</style>
