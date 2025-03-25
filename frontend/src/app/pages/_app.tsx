import type { AppProps } from 'next/app';
import { PrimeReactProvider } from 'primereact/api';
import "./globals.css";
import "primeicons/primeicons.css";
import "primereact/resources/primereact.min.css";

function MyApp({ Component, pageProps }: AppProps) {
    return (
            <Component {...pageProps} />
    );
}

export default MyApp;
