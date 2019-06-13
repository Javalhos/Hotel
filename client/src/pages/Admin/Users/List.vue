<template>
	<div>
		<form class="uk-grid-small" uk-grid>
			<div class="uk-width-1-1">
				<input class="uk-input" type="text" placeholder="Pesquisar usuário...">
			</div>
			<div class="uk-width-1-1">
			</div>
		</form>
		<list-admin-users :users="users"></list-admin-users>
	</div>
</template>

<script>
import ListAdminUsers from '../../../components/Users/ListAdminUsers'
import { Event } from '../../../common'
export default {
	components: { ListAdminUsers },
	data() {
		return {
			users: [],
			user: this.$auth.user
		}
	},
	async created() {
		const { data } = await this.$http.get('/users')
		this.users = data

		if (this.users && this.users.length >= 0) {
			this.users.map(u => {
				if (u.cpf === this.user.cpf)
					return
				else
					return u
			})
		}
	}
}
</script>

<style>

</style>
