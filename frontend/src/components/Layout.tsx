import { Outlet } from 'react-router-dom'
import Header from './Header'


export const Layout = () => {

     return (
          <>

               <Header/>
               <div className="bg-gray-900 text-white min-h-screen">
                    <Outlet />
               </div>

          </>
     )
}
