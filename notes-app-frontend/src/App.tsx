import { NextUIProvider } from "@nextui-org/react";
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import './App.css';
import Header from './features/commons/components/Header';
import { Router } from './routes/Router';

const queryClient = new QueryClient()

const App = () => {

  return (
    <NextUIProvider>
      <Header />
      <QueryClientProvider client={queryClient} >
        <Router />
      </QueryClientProvider>
    </NextUIProvider>
  )
}

export default App
