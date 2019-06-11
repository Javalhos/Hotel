<template>
	<div class="uk-width-1-2">
		<div class="uk-card uk-card-small uk-card-default uk-child-width-1-2" uk-grid>
			<div class="uk-card-media-left uk-cover-container">
				<img src="http://placehold.it/600x400" uk-cover>
				<canvas width="600" height="400"></canvas>
			</div>
			<div class="uk-card-body">
				<h3 class="uk-card-title">{{ room.type | roomType }}</h3>
				<p>Número de Camas: {{ room.beds }}</p>
				<p>Valor Diária: {{ `R$ ${room.dailyRate}` }}</p>
				<router-link tag="a" :to="`/quarto/${room.room}`" class="uk-button uk-width-expand uk-button-primary">
					Reservar
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
import { roomType } from '../../../filters';

export default {
	props: [ 'room' ],
	filters: { roomType }
}
</script>

<style>

</style>
