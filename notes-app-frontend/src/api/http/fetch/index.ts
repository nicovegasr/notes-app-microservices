type Headers = { [key: string]: string }

const get = async <T>(url: string, headers?: Headers) => {
    const response = await fetch(`${url}`, {
        method: 'GET',
        headers: { ...headers }
    })
    return await response.json() as T
}

const post = async <T, K>(url: string, body: K, headers?: Headers) => {
    const response = await fetch(`${url}`, {
        method: 'POST',
        headers: {
            ...headers,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
    return await response.json() as T
}

export const http = {
    get,
    post
}
