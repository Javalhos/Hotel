export const roomType = value => {
	const types = {
		NORMAL: 'Acomodação Simples',
		SUITE: 'Suíte Simples',
		LUXO: 'Suíte de Luxo',
		PRESIDENCIAL: 'Suíte Presidencial'
	}

	return types[value] || 'Indisponível'
}
