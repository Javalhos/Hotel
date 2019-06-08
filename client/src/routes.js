import { Router } from 'vue-fancy-router';

const viewsHandler = component => require(`./pages/${component}`).default

const Route = new Router(viewsHandler)

Route.add('/', 'Home')

export const router = Route.build()
