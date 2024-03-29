import { Http } from './Http'

export class Errors {
    /**
     * Create a new Errors instance.
     */
    constructor () {
        this.errors = {}
    }
    /**
     * Determine if an errors exists for the given field.
     *
     * @param {string} field
     */
    has (field) {
        return this.errors.hasOwnProperty(field)
    }
    /**
     * Determine if we have any errors.
     */
    any () {
        return Object.keys(this.errors).length > 0
    }

    /**
     * Retrieve the error message for a field.
     *
     * @param {string} field
     */
    get (field) {
        if (this.errors[field])
            return this.errors[field]
    }

    /**
     * Record the new errors.
     *
     * @param {object} errors
     */
    record (errors) {
        this.errors = errors
    }

    /**
     * Clear one or all error fields.
     *
     * @param {string|null} field
     */
    clear (field) {
        if (field) {
            delete this.errors[field]
            return
        }
        this.errors = {}
    }
}

export class Form {

    /**
     * Create a new Form instance.
     *
     * @param {object} data
     */
    constructor (data) {
        this.originalData = data
        for (let field in data) {
            this[field] = data[field]
        }
        this.errors = new Errors()
    }

    /**
     * Fetch all relevant data for the form.
     */
    data () {
        let data = {}
        for (let property in this.originalData)
            data[property] = this[property]

        return data
    }

    /**
     * Reset the form fields.
     */
    reset () {
        for (let field in this.originalData)
            this[field] = ''

        this.errors.clear()
    }

    /**
     * Send a POST request to the given URL.
     * .
     * @param {string} url
     */
    post (url) {
        return this.submit('post', url)
    }

    /**
     * Send a PUT request to the given URL.
     * .
     * @param {string} url
     */
    put (url) {
        return this.submit('put', url)
    }

    /**
     * Send a PATCH request to the given URL.
     * .
     * @param {string} url
     */
    patch (url) {
        return this.submit('patch', url)
    }

    /**
     * Send a DELETE request to the given URL.
     * .
     * @param {string} url
     */
    delete (url) {
        return this.submit('delete', url)
    }

    /**
     * Submit the form.
     *
     * @param {string} requestType
     * @param {string} url
     */
    async submit (requestType, url) {
		const { [requestType.toLowerCase()]: method } = Http

		try {
			const { data } = await method(url, this.data())
			return data
		} catch (error) {
			return error
		}
    }
}
