import React from 'react';
import Login from './pages/Login';
import Register from './pages/Register';
import Error from './pages/Error';
import Cloud from './pages/Cloud';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.css';

const router = createBrowserRouter([
  {
    path: '/register',
    element: <Register />,
  },
  {
    path: '/',
    element: <Cloud />,
  },
  {
    path: '/login',
    element: <Login />,
  },
  {
    path: '/cloud',
    element: <Cloud />,
  },
  {
    path: '*',
    element: <Error />,
  }
]);


const App = () => (
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);


export default App;
