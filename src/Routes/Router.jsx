import {
    createBrowserRouter,
  } from "react-router-dom";
  import App from "../App";
import Listado from "../Components/Producto/Listado";
import StockManagement from "../Components/Producto/Stock";
  export const router = createBrowserRouter([
      {
        path: "/",
        element:<App/>
      },
      {
        children: [
          {
            path: "productos/listado",
            element: <Listado/>
          },
          {
            path: "productos/stock",
            element:  <StockManagement/>
          }
        ]
      }
]);