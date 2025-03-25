import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import PrimeProvider from "./provider/PrimeReactProvider";

const inter = Inter({
  variable: "--font-inter",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "PowerPDF",
  description: "Uma ferramenta para manipulação de PDFs",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={`${inter.variable} antialiased`}>
        <PrimeProvider>{children}</PrimeProvider>
      </body>
    </html>
  );
}
