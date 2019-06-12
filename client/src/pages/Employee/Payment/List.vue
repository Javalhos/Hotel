<template>
	<div class="uk-grid-small uk-child-width-1-1@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input type="text" class="uk-input" placeholder="Pesquisar por um pagamento...">
					</div>
				</form>
				<list-payment :payments="payments"></list-payment>
			</div>
    	</div>
	</div>
</template>

<script>
import ListPayment from "../../../components/ListPayment";
export default {
	components: { ListPayment },
	data() {
		return {
			payments: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/payments')
		this.payments = data
		console.log(data)
	}
}
</script>