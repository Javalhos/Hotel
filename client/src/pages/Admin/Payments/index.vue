<template>
	<div>
		<form class="uk-grid-small" uk-grid>
			<div class="uk-width-1-1">
				<input class="uk-input" type="text" placeholder="Pesquisar pagamento...">
			</div>
			<div class="uk-width-1-1">
			</div>
		</form>
		<list-payments :payments="payments" :button="false"></list-payments>
	</div>
</template>

<script>
import ListPayments from "../../../components/Payments/ListPayments"
export default {
	components: { ListPayments },
	data() {
		return {
			payments: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/payments')
		this.payments = data
	}

}
</script>
