<template>
	<div class="uk-section uk-section-primary uk-padding-remove">
		<div class="uk-container uk-container-expand">
			<nav uk-navbar>
				<div class="uk-navbar-left">
					<ul class="uk-navbar-nav">
						<router-link tag="a" :to="homeLink" :key="homeLink.name">HOTELARIA CHERNOBYL</router-link>
					</ul>
				</div>
				<div class="uk-navbar-right">
					<ul class="uk-navbar-nav" v-if="!isLoggedIn" :key="isLoggedIn">
						<router-link to="/auth/signup" tag="li">
							<a>Registrar</a>
						</router-link>
						<router-link to="/auth/signin" tag="li">
							<a>Entrar</a>
						</router-link>
					</ul>
					<ul class="uk-navbar-nav" v-if="isLoggedIn">
						<router-link :to="homeLink" tag="li" :key="homeLink.name">
							<a>Início</a>
						</router-link>
						<router-link to="/staff/admin" tag="li" v-if="$auth.isAdmin()">
							<a>Administrador</a>
						</router-link>
						<li>
							<a @click="logout">Sair</a>
						</li>
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
			this.getHomeLink()
		})
	},

	data () {
		return {
			isLoggedIn: this.$auth.isSignedIn(),
			homeLink: { name: 'home' },
			user: this.$auth.user
		}
	},

	computed: {
	},

	mounted () {
		this.getHomeLink()
	},

	methods: {
		getHomeLink () {
			const homelink = this.homeLink
			const { user } = this.$auth

			if (!user) {
				homelink.name = 'home'
				return homelink
			}
			
			switch (user.level) {
				case 'EMPLOYEE':
				case 'ADMIN':
					homelink.name = 'staff'
					break
				default:
					homelink.name = 'home'
					break
			}
			return homelink
		},

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
