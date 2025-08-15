// A tiny Axios client to talk to the backend.
// Change BASE_URL if your backend runs on a different host/port or is deployed.
import axios from "axios";

export const BASE_URL =
  import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

const api = axios.create({
  baseURL: `${BASE_URL}`,
});

export default api;
