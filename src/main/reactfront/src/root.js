import { Suspense, lazy } from "react";

import { createBrowserRouter } from "react-router-dom";
import Login from "./member/Login";
import Mainpage from "./Mainpage";

const Loading = <div>Loading...</div>
const Login1 = lazy(() => import("../src/member/Login"))
const SignUp = lazy(() => import("../src/member/Signup"))
const Memberinfo  = lazy(() => import("../src/member/no_redux/MemberInfo"))

const Board = lazy(() => import("../src/board/BoardList"))
const root = createBrowserRouter([
    {
        path: "",
        element: <Suspense fallback ={Loading}><Mainpage/></Suspense>
    },
    {
        path: "login",
        element: <Suspense fallback ={Loading}><Login1/></Suspense>
    },
    {
        path: "signup",
        element: <Suspense fallback ={Loading}><SignUp/></Suspense>
    },
    {
        path: "board",
        element: <Suspense fallback ={Loading}><Board/></Suspense>
    },
    {
        path: "memberinfo",
        element: <Suspense fallback ={Loading}><Memberinfo/></Suspense>
    }
])

export default root;