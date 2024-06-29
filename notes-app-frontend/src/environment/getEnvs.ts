interface EnvVars {
    auth_url: string,
    notes_url: string
}

export const envs: EnvVars = {
    auth_url: import.meta.env.VITE_AUTH_MICROSERVICE_URL,
    notes_url: import.meta.env.VITE_NOTE_MICROSERVICE_URL
}