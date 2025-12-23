import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import './style/App.css'
import { Layout } from './components/Layout'
import Login from './components/Login'
import Product from './components/Product'
import Order from './components/Order'
import Signup from './components/Signup'
import Admin from './components/Admin'
import { useEffect } from 'react'
import toast, { Toaster } from 'react-hot-toast'

const router = createBrowserRouter([
  {
    element: <Layout />,
    errorElement: <div>404 PAGE..</div>,
    children: [
      {
        path: '/',
        element: <Product />
      },
      {
        path: '/order',
        element: <Order />
      },
      {
        path: '/signup',
        element: <Signup />
      },
      {
        path: '/login',
        element: <Login />
      },
      {
        path: '/admin',
        element: <Admin />
      }

    ]
  },
])




function App() {

  useEffect(() => {
    const handleUnauthorized = (event: CustomEvent) => {
      toast.error(event.detail.message, { duration: 5000 });
      window.location.href = '/'
    };
    const handleAuthorized = (event: CustomEvent) => {
      toast.success(event.detail.message, { duration: 5000, });
    };

    const handleNewUser = (event: CustomEvent) => {
      toast.success(event.detail.message, { duration: 5000, });
    };
    const handleNewOrder = (event: CustomEvent) => {
      toast.success(event.detail.message, { duration: 5000, });
    };
    const handleNewProduct = (event: CustomEvent) => {
      toast.success(event.detail.message, { duration: 5000, });
      // window.location.href = '/'
    };
    const handleDeleteProduct = (event: CustomEvent) => {
      toast.success(event.detail.message, { duration: 5000, });
      // window.location.href = '/'
    };
    window.addEventListener('unauthorized', handleUnauthorized as EventListener);
    window.addEventListener('authorized', handleAuthorized as EventListener);
    window.addEventListener('newuser', handleAuthorized as EventListener);
    window.addEventListener('deleteproduct', handleDeleteProduct as EventListener);
    window.addEventListener('newproduct', handleNewProduct as EventListener);

    return () => {
      window.removeEventListener('unauthorized', handleUnauthorized as EventListener);
      window.addEventListener('authorized', handleAuthorized as EventListener);
      window.addEventListener('newuser', handleNewUser as EventListener);
      window.addEventListener('neworder', handleNewOrder as EventListener);
      window.addEventListener('newproduct', handleNewProduct as EventListener);
      window.addEventListener('deleteproduct', handleDeleteProduct as EventListener);
    };
  }, []);


  return (
    <>
      <Toaster position='top-center' />
      <RouterProvider router={router} />
    </>
  )
}

export default App
