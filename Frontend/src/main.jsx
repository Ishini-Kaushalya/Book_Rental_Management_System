import React from "react";
import ReactDOM from "react-dom/client";
import "./styles.css"; // <-- CSS file only
import App from "./pages/App";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
