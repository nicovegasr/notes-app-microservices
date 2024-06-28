interface EnvVars {
    auth_url: string,
    note_url: string
}

export const envs: EnvVars = {
    auth_url: import.meta.env.VITE_AUTH_MICROSERVICE_URL,
    note_url: import.meta.env.VITE_NOTE_MICROSERVICE_URL
}