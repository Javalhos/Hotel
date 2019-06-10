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
						<!-- TODO: Adicionar Router Links -->
						<router-link to="/auth/signup" tag="li">
							<a>Registrar</a>
						</router-link>
						<router-link to="/auth/signin" tag="li">
							<a>Entrar</a>
						</router-link>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</template>

<script>
export default {

}
</script>

<style>

</style>
