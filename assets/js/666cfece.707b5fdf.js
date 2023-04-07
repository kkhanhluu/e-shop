"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[388],{3905:(e,t,n)=>{n.d(t,{Zo:()=>p,kt:()=>f});var r=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var l=r.createContext({}),c=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},p=function(e){var t=c(e.components);return r.createElement(l.Provider,{value:t},e.children)},d="mdxType",m={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},u=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,l=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),d=c(n),u=a,f=d["".concat(l,".").concat(u)]||d[u]||m[u]||i;return n?r.createElement(f,o(o({ref:t},p),{},{components:n})):r.createElement(f,o({ref:t},p))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,o=new Array(i);o[0]=u;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s[d]="string"==typeof e?e:a,o[1]=s;for(var c=2;c<i;c++)o[c]=n[c];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}u.displayName="MDXCreateElement"},7951:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>m,frontMatter:()=>i,metadata:()=>s,toc:()=>c});var r=n(7462),a=(n(7294),n(3905));const i={title:"Simplified CQRS",slug:"simplified-cqrs",sidebar_position:3},o="Simplified CQRS",s={unversionedId:"explore/code/simplified-cqrs",id:"explore/code/simplified-cqrs",title:"Simplified CQRS",description:"Context and problem",source:"@site/docs/explore/code/simplified-cqrs.md",sourceDirName:"explore/code",slug:"/explore/code/simplified-cqrs",permalink:"/e-shop/explore/code/simplified-cqrs",draft:!1,editUrl:"https://github.com/kkhanhluu/eshop/docs/explore/code/simplified-cqrs.md",tags:[],version:"current",sidebarPosition:3,frontMatter:{title:"Simplified CQRS",slug:"simplified-cqrs",sidebar_position:3},sidebar:"docs",previous:{title:"Event sourcing",permalink:"/e-shop/explore/code/event-sourcing"},next:{title:"Transactional outbox pattern",permalink:"/e-shop/explore/code/transactional-outbox-pattern"}},l={},c=[{value:"Context and problem",id:"context-and-problem",level:2},{value:"Conceptual overview of CQRS",id:"conceptual-overview-of-cqrs",level:2},{value:"Code details",id:"code-details",level:2}],p={toc:c},d="wrapper";function m(e){let{components:t,...i}=e;return(0,a.kt)(d,(0,r.Z)({},p,i,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"simplified-cqrs"},"Simplified CQRS"),(0,a.kt)("h2",{id:"context-and-problem"},"Context and problem"),(0,a.kt)("p",null,"In complex applications, there is often a mismatch between the read and write presentations of the data. On the read side, the application may perform different queries to return different data transfer objects (DTOs). On the write side, the model may implement complex validation and business logic. As a result, it can end up with a very complex data model."),(0,a.kt)("h2",{id:"conceptual-overview-of-cqrs"},"Conceptual overview of CQRS"),(0,a.kt)("p",null,"The Command and Query Responsibility Segregation (CQRS) pattern can be a solution to the above problem. In simple terms, we separate reads and writes using ",(0,a.kt)("inlineCode",{parentName:"p"},"commands")," and ",(0,a.kt)("inlineCode",{parentName:"p"},"queries"),"."),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"Commands")," are responsible for changing the application state, i.e., creating, updating, and deleting data."),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"Queries")," are responsible for reading the application state.")),(0,a.kt)("p",null,"When handling ",(0,a.kt)("inlineCode",{parentName:"p"},"commands"),", the application model is usually represented by an ",(0,a.kt)("inlineCode",{parentName:"p"},"aggregate"),". An ",(0,a.kt)("inlineCode",{parentName:"p"},"aggregate")," is a cluster of associated objects that we treat as a unit for the purpose of data changes. We can take an order as an example. An order is a domain object that consists of several objects: the status, the list of items that the user already purchased, and the user who purchased the order. In this example, the invoice is the ",(0,a.kt)("inlineCode",{parentName:"p"},"aggregate"),". An ",(0,a.kt)("inlineCode",{parentName:"p"},"aggregate")," is also a consistency guard. It takes the current state, verifies rules for the particular operation, applies the business logic, and returns the new state."),(0,a.kt)("h2",{id:"code-details"},"Code details"),(0,a.kt)("p",null,"The CQRS pattern can be checked in Order Service."),(0,a.kt)("p",null,(0,a.kt)("img",{alt:"cqrs commands",src:n(2559).Z,width:"439",height:"401"})),(0,a.kt)("p",null,"Commands are read-only DTOs that contain all the data required to execute the operation."),(0,a.kt)("p",null,"Each command has a specific command handler that is responsible for executing the operations intended for the command."),(0,a.kt)("p",null,(0,a.kt)("img",{alt:"cqrs command handlers",src:n(1680).Z,width:"1082",height:"431"})),(0,a.kt)("p",null,"On the other hand, queries just return whatever the client needs, which could be a domain object."),(0,a.kt)("p",null,(0,a.kt)("img",{alt:"cqrs entities",src:n(8495).Z,width:"408",height:"507"})))}m.isMDXComponent=!0},1680:(e,t,n)=>{n.d(t,{Z:()=>r});const r=n.p+"assets/images/cqrs-command-handler-f578450985c087efc6353adecb6701e6.png"},2559:(e,t,n)=>{n.d(t,{Z:()=>r});const r=n.p+"assets/images/cqrs-commands-1fc6f16fb08ce6206ff5c78eaba28e8b.png"},8495:(e,t,n)=>{n.d(t,{Z:()=>r});const r=n.p+"assets/images/cqrs-entities-819b41258f6cde0c7832757e63d9c4c4.png"}}]);