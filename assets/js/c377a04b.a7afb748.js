"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[971],{3905:(e,t,r)=>{r.d(t,{Zo:()=>p,kt:()=>h});var a=r(7294);function n(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function o(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,a)}return r}function i(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?o(Object(r),!0).forEach((function(t){n(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):o(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function s(e,t){if(null==e)return{};var r,a,n=function(e,t){if(null==e)return{};var r,a,n={},o=Object.keys(e);for(a=0;a<o.length;a++)r=o[a],t.indexOf(r)>=0||(n[r]=e[r]);return n}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)r=o[a],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(n[r]=e[r])}return n}var c=a.createContext({}),l=function(e){var t=a.useContext(c),r=t;return e&&(r="function"==typeof e?e(t):i(i({},t),e)),r},p=function(e){var t=l(e.components);return a.createElement(c.Provider,{value:t},e.children)},u="mdxType",m={inlineCode:"code",wrapper:function(e){var t=e.children;return a.createElement(a.Fragment,{},t)}},d=a.forwardRef((function(e,t){var r=e.components,n=e.mdxType,o=e.originalType,c=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),u=l(r),d=n,h=u["".concat(c,".").concat(d)]||u[d]||m[d]||o;return r?a.createElement(h,i(i({ref:t},p),{},{components:r})):a.createElement(h,i({ref:t},p))}));function h(e,t){var r=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var o=r.length,i=new Array(o);i[0]=d;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s[u]="string"==typeof e?e:n,i[1]=s;for(var l=2;l<o;l++)i[l]=r[l];return a.createElement.apply(null,i)}return a.createElement.apply(null,r)}d.displayName="MDXCreateElement"},1269:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>c,contentTitle:()=>i,default:()=>m,frontMatter:()=>o,metadata:()=>s,toc:()=>l});var a=r(7462),n=(r(7294),r(3905));const o={sidebar_position:1},i="Home",s={unversionedId:"index",id:"index",title:"Home",description:"This project is a practical microservices reference example for demonstrating the basic of Command Query Responsibility Segregation (CQRS), Event Sourcing and Saga pattern. Application consists of loosely coupled components that communicate using Message Queue or gRPC.",source:"@site/docs/index.md",sourceDirName:".",slug:"/",permalink:"/e-shop/",draft:!1,editUrl:"https://github.com/kkhanhluu/eshop/docs/index.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"docs",next:{title:"Getting started",permalink:"/e-shop/getting-started"}},c={},l=[{value:"Ideas for the application",id:"ideas-for-the-application",level:2},{value:"Architecture overview",id:"architecture-overview",level:2},{value:"Tech stack",id:"tech-stack",level:2}],p={toc:l},u="wrapper";function m(e){let{components:t,...o}=e;return(0,n.kt)(u,(0,a.Z)({},p,o,{components:t,mdxType:"MDXLayout"}),(0,n.kt)("h1",{id:"home"},"Home"),(0,n.kt)("p",null,"This project is a practical microservices reference example for demonstrating the basic of Command Query Responsibility Segregation (CQRS), Event Sourcing and Saga pattern. Application consists of loosely coupled components that communicate using Message Queue or gRPC."),(0,n.kt)("h2",{id:"ideas-for-the-application"},"Ideas for the application"),(0,n.kt)("p",null,"This example illustrates several important concepts:"),(0,n.kt)("ul",null,(0,n.kt)("li",{parentName:"ul"},"Decompose a big, monolithic application into microservices."),(0,n.kt)("li",{parentName:"ul"},"Using an event-driven architecture to loose coupling between components, which communicate with each other by events"),(0,n.kt)("li",{parentName:"ul"},"Apply transactional outbox pattern to update database and messages/events atomically"),(0,n.kt)("li",{parentName:"ul"},"Using event sourcing to store data as event instead of justing storing entity's state in database"),(0,n.kt)("li",{parentName:"ul"},"Using Command Query Responsibility Segregation (CQRS) - update requests (commands) and view requests (queries) are handled by separate services.")),(0,n.kt)("h2",{id:"architecture-overview"},"Architecture overview"),(0,n.kt)("p",null,(0,n.kt)("img",{alt:"Eshop architecture",src:r(4057).Z,width:"12472",height:"6479"})),(0,n.kt)("p",null,"Information and details to help you getting to know e-shop from these points of view:"),(0,n.kt)("ul",null,(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"/docs/tutorial-basics/congratulations"},"Architecture")),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"/docs/tutorial-basics/congratulations"},"Code"))),(0,n.kt)("h2",{id:"tech-stack"},"Tech stack"),(0,n.kt)("ul",null,(0,n.kt)("li",{parentName:"ul"},"Spring boot: Java framework to build web server"),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://docs.spring.io/spring-authorization-server/docs/current/reference/html/index.html"},"Spring oauth2 authorization server"),": Framework that provides implementation of OAuth 2.1 and OpenID specifications."),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://expressjs.com/"},"Express.js"),": framework to build web server in Node.js"),(0,n.kt)("li",{parentName:"ul"},"PostgresSQL: SQL database"),(0,n.kt)("li",{parentName:"ul"},"MongoDB: NoSQL database"),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://www.eventstore.com/"},"Event store"),": state-transition database to store events"),(0,n.kt)("li",{parentName:"ul"},"RabbitMQ: Message broker"),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://grpc.io/"},"gRPC"),": a high performance framework for Remote Procedure Call (RPC)"),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://trpc.io/"},"tRPC"),": a library to create fully type-safe API with typescript. HTTP is still used under the hood to communicate client and server."),(0,n.kt)("li",{parentName:"ul"},"Kubernetes: container orchestration")))}m.isMDXComponent=!0},4057:(e,t,r)=>{r.d(t,{Z:()=>a});const a=r.p+"assets/images/architecture-64ba597d4898075c06b04b8ee331a9d4.png"}}]);