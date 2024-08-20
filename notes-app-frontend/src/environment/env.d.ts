/// <reference types="vite/client" />

interface ImportMetaEnv {
    readonly VITE_AUTH_MICROSERVICE_URL: string
    readonly VITE_NOTE_MICROSERVICE_URL: string
  }
  
  interface ImportMeta {
    readonly env: ImportMetaEnv
  }