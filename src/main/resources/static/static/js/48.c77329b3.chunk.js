"use strict";(self.webpackChunkreactfront=self.webpackChunkreactfront||[]).push([[48],{48:(e,t,a)=>{a.r(t),a.d(t,{default:()=>r});var n=a(60),s=a(372),l=a(560),u=a(496);const r=function(){const[e,t]=(0,n.useState)(""),[a,r]=(0,n.useState)(""),[i,p]=(0,n.useState)(""),[h,o]=(0,n.useState)(""),[c,x]=(0,n.useState)(""),[d,g]=(0,n.useState)(""),j=(0,l.i6)();return(0,u.jsxs)("div",{children:[(0,u.jsx)("h2",{children:"Signup"}),(0,u.jsxs)("form",{onSubmit:t=>{t.preventDefault();const n={mem_id:e,mem_pw:a,mem_name:i,birth:h,phone:c,email:d};s.c.post("/api/member/signup",n).then((e=>{alert("\ud68c\uc6d0\uac00\uc785 \uc131\uacf5!"),j("/login")})).catch((e=>{console.error("Signup failed: ",e),alert("\ud68c\uc6d0\uac00\uc785 \uc2e4\ud328")}))},children:[(0,u.jsxs)("label",{children:["Username:",(0,u.jsx)("input",{type:"text",value:e,onChange:e=>t(e.target.value)})]}),(0,u.jsxs)("label",{children:["Password:",(0,u.jsx)("input",{type:"password",value:a,onChange:e=>r(e.target.value)})]}),(0,u.jsxs)("label",{children:["name:",(0,u.jsx)("input",{type:"text",value:i,onChange:e=>p(e.target.value)})]}),(0,u.jsxs)("label",{children:["birth:",(0,u.jsx)("input",{type:"text",value:h,onChange:e=>o(e.target.value)})]}),(0,u.jsxs)("label",{children:["phone:",(0,u.jsx)("input",{type:"text",value:c,onChange:e=>x(e.target.value)})]}),(0,u.jsxs)("label",{children:["email:",(0,u.jsx)("input",{type:"text",value:d,onChange:e=>g(e.target.value)})]}),(0,u.jsx)("button",{type:"submit",children:"Signup"})]})]})}}}]);
//# sourceMappingURL=48.c77329b3.chunk.js.map