import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  // added for dockercompose
  server: {
    host: true, // Listen on all addresses, including LAN and public addresses
    port: 5173,
    watch: {
      usePolling: true, // Required for hot-reload on some systems (like Windows WSL)
    },
  },
});
