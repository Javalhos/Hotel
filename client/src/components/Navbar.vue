<template>
	<div class="uk-section uk-section-primary uk-padding-remove">
		<div class="uk-container uk-container-expand">
			<nav uk-navbar>
				<div class="uk-navbar-left">
					<ul class="uk-navbar-nav">
						<router-link to="/" tag="li">
							<a>Início</a>
						</router-link>
					</ul>
				</div>
				<div class="uk-navbar-right">
					<ul class="uk-navbar-nav">
						<template v-if="!isLoggedIn">
							<router-link to="/auth/signup" tag="li">
								<a>Registrar</a>
							</router-link>
							<router-link to="/auth/signin" tag="li">
								<a>Entrar</a>
							</router-link>
						</template>
						<template v-else>
							<li>
								<a @click="logout">Sair</a>
							</li>
						</template>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</template>

<script>
import { Event } from '../common';

export default {
	created () {
		Event.listen('auth-status-changed', ({ signedIn }) => {
			this.isLoggedIn = signedIn
		})
	},

	data () {
		return {
			isLoggedIn: this.$auth.isSignedIn()
		}
	},

	methods: {
		logout () {
			this.$auth.signOut()
			this.$router.push({ name: 'home' })
			this.isLoggedIn = this.$auth.isSignedIn()
		}
	}
}
</script>

<style>

</style>
