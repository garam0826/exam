import { Suspense, lazy } from "react";

import { createBrowserRouter } from "react-router-dom";
import Login from "./member/Login";
import Mainpage from "./Mainpage";
import WriteContent from "./board/WriteContent";
import EditContent from "./board/EditContent";
import ReadContent from "./board/ReadContent";

const Loading = <div>Loading...</div>
const Login1 = lazy(() => import("../src/member/Login"))
const SignUp = lazy(() => import("../src/member/Signup"))
const Memberinfo  = lazy(() => import("../src/member/no_redux/MemberInfo"))
const Memberresign= lazy(() => import("../src/member/no_redux/MemberResign"))
const Findid = lazy(() => import("../src/member/no_redux/FindId"))
const Animalinfo= lazy(() => import("../src/animal/animalInfo"))

const Mypage= lazy(() => import("../src/member/MyPage"))
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
    },
    {
        path: "resign",
        element: <Suspense fallback ={Loading}><Memberresign/></Suspense>
    },
    {
        path: "searchid",
        element: <Suspense fallback ={Loading}><Findid/></Suspense>
    },
    {
        path: "animal",
        element: <Suspense fallback ={Loading}><Animalinfo/></Suspense>
    },
    {
        path: "mypage",
        element: <Suspense fallback ={Loading}><Mypage/></Suspense>
    },
    {
        path: "read",
        element: <Suspense fallback ={Loading}><ReadContent/></Suspense>
    },
    {
        path: "write",
        element: <Suspense fallback ={Loading}><WriteContent/></Suspense>
    },
    {
        path: "edit",
        element: <Suspense fallback ={Loading}><EditContent/></Suspense>
    }
])

export default root;