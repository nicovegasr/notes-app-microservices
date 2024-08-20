import { BrowserRouter } from "react-router-dom";
import './App.css';
import { AuthProvider } from "./features/auth/context/AuthProvider";
import Header from './features/commons/components/Header';
import { Router } from './routes/Router';
import { NextUIProvider } from "@nextui-org/react";
import { ToastProvider } from "./features/commons/contexts/ToastProvider";

const App = () =>
  <BrowserRouter>
    <ToastProvider>
      <AuthProvider>
        <NextUIProvider>
          <Header />
          <Router />
        </NextUIProvider>
      </AuthProvider >
    </ToastProvider>
  </BrowserRouter>

export default App
