<template>
	<div v-if="['EMPLOYEE', 'ADMIN'].includes(user.level)">
		<div class="uk-child-width-1-2@m uk-grid-small uk-grid-match" uk-grid>
			<div>
				<div class="uk-card uk-card-default uk-card-body">
					<h3 class="uk-card-title uk-text-center@m">Seja bem vindo, {{ user.name }}!</h3>
					<p class="uk-text-center@m">
						Utilize os cards nesta página para executar seu trabalho da melhor
						maneira possível! Aqui você pode executar uma nova hospedagem, 
						um novo pagamento ou até mesmo registrar um usuário.
					</p>
				</div>
				<div class="uk-margin">
					<div class="uk-card uk-card-default uk-card-body">
						<h3 class="uk-card-title uk-text-center@m">Registro de Novos Usuários</h3>
						<p class="uk-text-center@m">
							Aqui você pode cadastrar um novo usuário para, assim, visualizá-los e
							realizar um novo aluguel!
						</p>
						<div class="uk-button-group uk-flex-center" style="width: 100%">
							<router-link to="/employee/users/create" tag="a" class="uk-button uk-button-primary">
								Cadastrar Usuário
							</router-link>
    						<router-link to="/employee/users" tag="a" class="uk-button uk-button-secondary">
								Visualizar Usuários
							</router-link>
						</div>
					</div>
				</div>
			</div>
				<div>
				<div class="uk-card uk-card-default uk-card-body">
					<h3 class="uk-card-title uk-text-center@m">Registro de Alugueis</h3>
					<p class="uk-text-center@m">
						Aqui você pode realizar um novo aluguel e consultar os que já estão em
						andamento. 
					</p>
					<div class="uk-button-group uk-flex-center" style="width: 100%">
							<router-link to="/employee/acc/create" tag="a" class="uk-button uk-button-primary">
								Registrar Aluguel
							</router-link>
    						<router-link to="/employee/acc" tag="a" class="uk-button uk-button-secondary">
								Visualizar Hospedagens
							</router-link>
						</div>
				</div>
				<div class="uk-margin">
					<div class="uk-card uk-card-default uk-card-body">
						<h3 class="uk-card-title uk-text-center@m">Registro de Pagamentos</h3>
						<p class="uk-text-center@m">
							Aqui você pode registrar um pagamento e finalizar uma hospedagem!
						</p>
						<div class="uk-button-group uk-flex-center" style="width: 100%">
							<router-link to="/employee/payment/pay" tag="a" class="uk-button uk-button-primary">
								Registrar Pagamento
							</router-link>
							<router-link to="/employee/payment" tag="a" class="uk-button uk-button-secondary">
								Visualizar Pendencias
							</router-link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			user: this.$auth._user
		}
	}
}
</script>

<style>

</style>
