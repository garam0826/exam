"use strict";(self.webpackChunkreactfront=self.webpackChunkreactfront||[]).push([[0],[(e,r,t)=>{t.r(r),t.d(r,{default:()=>d});var c=t(60),i=t(372),n=t(560),s=t(12),a=t(496);const d=function(){const[e,r]=(0,c.useState)([]),t=(0,n.i6)();return(0,c.useEffect)((()=>{i.c.get("/api/freeboard/list").then((e=>{r(e.data)})).catch((e=>{console.error("There was an error retrieving the board list: ",e)}))}),[]),(0,a.jsxs)("div",{children:[(0,a.jsx)("h2",{children:"\uac8c\uc2dc\uae00 \ubaa9\ub85d"}),(0,a.jsx)("ul",{children:e.map((e=>(0,a.jsxs)("li",{children:[(0,a.jsx)(s.cH,{to:"board/list/read/".concat(e.idx),children:e.title})," - ",e.reg_name]},e.idx)))}),(0,a.jsx)("div",{children:(0,a.jsx)("button",{onClick:()=>t("/write"),children:"\uae00 \uc791\uc131\ud558\uae30"})})]})}}]]);
//# sourceMappingURL=0.4e88ebdd.chunk.js.map