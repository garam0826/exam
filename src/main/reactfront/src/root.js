import { Suspense, lazy } from "react";

import { createBrowserRouter } from "react-router-dom";
import Login from "./member/Login";
import Mainpage from "./Mainpage";

const Loading = <div>Loading...</div>
const Main = lazy(() => import("../src/Mainpage"))
const Login1 = lazy(() => import("../src/member/Login"))
const SignUp = lazy(() => import("../src/member/Signup"))

const root = createBrowserRouter([
    {
        path: "",
        element: <Suspense fallback ={Loading}><Main/></Suspense>
    },
    {
        path: "login",
        element: <Suspense fallback ={Loading}><Login1/></Suspense>
    },
    {
        path: "signup",
        element: <Suspense fallback ={Loading}><SignUp/></Suspense>
    }
])

export default root;