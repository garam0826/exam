"use strict";(self.webpackChunkreactfront=self.webpackChunkreactfront||[]).push([[404],{404:(e,s,t)=>{t.r(s),t.d(s,{default:()=>r});var a=t(60),n=t(372),l=t(496);const r=()=>{const[e,s]=(0,a.useState)([]),[t,r]=(0,a.useState)(!1),[i,c]=(0,a.useState)(null),[d,h]=(0,a.useState)(""),[p,x]=(0,a.useState)(""),[o,j]=(0,a.useState)(""),[u,g]=(0,a.useState)(""),[C,v]=(0,a.useState)("");return(0,l.jsxs)("div",{children:[t&&(0,l.jsx)("div",{children:"Loading..."}),i&&(0,l.jsxs)("div",{children:["Error: ",i.message]}),(0,l.jsxs)("div",{children:[(0,l.jsx)("label",{children:"Start Date:"}),(0,l.jsx)("input",{type:"text",value:d,onChange:e=>h(e.target.value)})]}),(0,l.jsxs)("div",{children:[(0,l.jsx)("label",{children:"End Date:"}),(0,l.jsx)("input",{type:"text",value:p,onChange:e=>x(e.target.value)})]}),(0,l.jsxs)("div",{children:[(0,l.jsx)("label",{children:"kind:"}),(0,l.jsx)("input",{type:"text",value:o,onChange:e=>j(e.target.value)})]}),(0,l.jsxs)("div",{children:[(0,l.jsx)("label",{children:"UprCd:"}),(0,l.jsx)("input",{type:"text",value:u,onChange:e=>g(e.target.value)})]}),(0,l.jsxs)("div",{children:[(0,l.jsx)("label",{children:"OrgCd:"}),(0,l.jsx)("input",{type:"text",value:C,onChange:e=>v(e.target.value)})]}),(0,l.jsx)("button",{onClick:async()=>{try{r(!0),c(null);const e=await n.c.get("/api/animal/info?startDate=".concat(d,"&endDate=").concat(p,"&kind=").concat(o,"&uprCd=").concat(u,"&orgCd=").concat(C));s(e.data)}catch(i){console.error("Error fetching animal info:",i),c(i)}r(!1)},children:"Fetch Animal Info"}),0===e.length&&!t&&!i&&(0,l.jsx)("div",{children:"No data available"}),(0,l.jsx)("ul",{children:e.map(((e,s)=>(0,l.jsxs)("li",{children:[(0,l.jsxs)("p",{children:["Desertion No: ",e.desertionNo]}),(0,l.jsxs)("p",{children:["File Name: ",(0,l.jsx)("img",{src:e.filename,alt:"Animal"})]}),(0,l.jsxs)("p",{children:["Happen Date: ",e.happenDt]}),(0,l.jsxs)("p",{children:["Happen Place: ",e.happenPlace]}),(0,l.jsxs)("p",{children:["KindCd: ",e.kindCd]}),(0,l.jsxs)("p",{children:["ColorCd: ",e.colorCd]}),(0,l.jsxs)("p",{children:["Age: ",e.age]}),(0,l.jsxs)("p",{children:["Weight: ",e.weight]}),(0,l.jsxs)("p",{children:["Notice No: ",e.noticeNo]}),(0,l.jsxs)("p",{children:["Notice Start Date: ",e.noticeSdt]}),(0,l.jsxs)("p",{children:["Notice End Date: ",e.noticeEdt]}),(0,l.jsxs)("p",{children:["Popfile: ",(0,l.jsx)("img",{src:e.popfile,alt:"Animal"})]}),(0,l.jsxs)("p",{children:["Process State: ",e.processState]}),(0,l.jsxs)("p",{children:["SexCd: ",e.sexCd]}),(0,l.jsxs)("p",{children:["NeuterYn: ",e.neuterYn]}),(0,l.jsxs)("p",{children:["Special Mark: ",e.specialMark]}),(0,l.jsxs)("p",{children:["Care Name: ",e.careNm]}),(0,l.jsxs)("p",{children:["Care Tel: ",e.careTel]}),(0,l.jsxs)("p",{children:["Care Address: ",e.careAddr]}),(0,l.jsxs)("p",{children:["Org Name: ",e.orgNm]}),(0,l.jsxs)("p",{children:["Charge Name: ",e.chargeNm]}),(0,l.jsxs)("p",{children:["Office Tel: ",e.officetel]})]},s)))})]})}}}]);
//# sourceMappingURL=404.0c38ad9c.chunk.js.map