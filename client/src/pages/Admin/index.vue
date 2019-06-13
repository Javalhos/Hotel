<template>
	<div class="uk-grid">
		<div class="uk-width-1-3@m">
			<div class="uk-card uk-card-default uk-card-body uk-width-1-@2s">
				<ul class="uk-nav-default uk-nav-parent-icon" uk-nav>
					<li class="uk-parent">
						<a><span class="uk-margin-small-right" uk-icon="icon: user"></span> Usuários</a>
						<ul class="uk-nav-sub">
							<li><router-link to="/staff/admin/users" tag="a">Todos os Usuários</router-link></li>
							<li><router-link to="/staff/admin/users/create" tag="a">Novo Usuário do Sistema</router-link></li>
						</ul>
					</li>
					<li class="uk-parent">
						<a><span class="uk-margin-small-right" uk-icon="icon: home"></span> Hospedagens</a>
						<ul class="uk-nav-sub">
							<li><router-link to="/staff/admin/accomodations" tag="a">Todos as Hospedagens</router-link></li>
						</ul>
					</li>
					<li class="uk-parent">
						<a><span class="uk-margin-small-right" uk-icon="icon: credit-card"></span> Pagamentos</a>
						<ul class="uk-nav-sub">
							<li><router-link to="/staff/admin/payments" tag="a">Todos os pagamentos</router-link></li>
						</ul>
					</li>
					<li class="uk-parent">
						<a><span class="uk-margin-small-right" uk-icon="icon: location"></span> Quartos</a>
						<ul class="uk-nav-sub">
							<li><router-link to="/staff/admin/rooms" tag="a">Todos os Quartos</router-link></li>
							<li><router-link to="/staff/admin/rooms/create" tag="a">Novo Quarto</router-link></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="uk-width-expand@m">
			<div class="uk-card uk-card-default uk-card-body">
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script>
export default {

}
</script>

<style>

</style>
