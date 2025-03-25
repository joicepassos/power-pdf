/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        powerblue: '#0259BB',
        powerblueLight: '#1773F3',
        powerDark: '#435265',
        success: '#16a34a',
        danger: '#dc2626',
      },
    },
  },
  plugins: [],
}