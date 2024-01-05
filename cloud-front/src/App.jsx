import * as ReactDOM from "react-dom/client";
import Login from './pages/Login';
import Register from "./pages/Register";
import Error  from "./pages/Error";
import Cloud from "./pages/Cloud"
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";


const router = createBrowserRouter([
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/cloud",
    element: <Cloud />,
  },
  {
    path: "*",
    element: <Error /> ,
  }
]);



function App() {
  ReactDOM.createRoot(document.getElementById("root")).render(
    <RouterProvider router={router}>
      <App />
    </RouterProvider>
  );
}

export default App
