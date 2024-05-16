import axios from "axios"
import { UserInterface } from "../interfaces/UserInterface"
import { backend } from "../App"
import { RegistrationInterface } from "../interfaces/RegistrationInterface"


function setAuth(jwt: string) {
	// set httponly cookie? if you want persistence across browser refresh

	axios.defaults.headers.common.Authorization = `Bearer ${jwt}`
}

function unsetAuth() {
	//delete cookie if you set it

	delete axios.defaults.headers.common.Authorization
}

export async function registerUser(user: RegistrationInterface) {
	const response = await axios.post(backend("/auth/register"), user)
	console.log(response)
	return response
}

export async function loginUser(user: UserInterface) {
	const response = await axios.post(backend("/auth/login"), user)
	console.log(response)
	setAuth(response.data)
	return response
}
