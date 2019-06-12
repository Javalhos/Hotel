<template>
	<div class="uk-grid-small uk-child-width-1-1@s uk-flex-center" uk-grid>
		<div>
        	<div class="uk-card uk-card-default uk-card-body">
				<form class="uk-grid-small" uk-grid>
					<div class="uk-width-1-1">
						<input type="text" class="uk-input" placeholder="Pesquisar por um usuário...">
					</div>
				</form>
				<list-users :users="users"></list-users>
			</div>
    	</div>
	</div>
</template>

<script>
import ListUsers from "../../../components/Users/ListUsers";
export default {
	components: { ListUsers },
	data() {
		return {
			users: []
		}
	},
	async created() {
		const { data } = await this.$http.get('/users')
		this.users = data
		console.log(this.users)
	}
}
</script>