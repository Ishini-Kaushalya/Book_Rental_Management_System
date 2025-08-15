/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        'elegant': '0 10px 25px rgba(0,0,0,0.08)',
      }
    },
  },
  plugins: [],
}
