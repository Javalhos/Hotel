<template>
	<div id="modal" uk-modal>
		<div class="uk-modal-dialog">
			<button class="uk-modal-close-default" type="button" uk-close></button>
			<div class="uk-modal-header">
				<h2 class="uk-modal-title">
					<slot name="header"></slot>
				</h2>
			</div>
			<div class="uk-modal-body">
				<slot name="body"></slot>
			</div>
			<div class="uk-modal-footer uk-text-right">
				<div class="uk-margin">
					<button class="uk-button uk-button-default uk-modal-close">Cancelar</button>
					<button class="uk-button uk-button-primary">Salvar Alterações</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: ['width_val']
}
</script>
