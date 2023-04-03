# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.0.10](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.9...@inventory-service-app/v0.0.10) (2023-04-03)


### Features

* add api gateway k8s config ([a56bece](https://github.com/kkhanhluu/e-shop/commit/a56bece4b967057802b3ba8410b377c38bdf72b6))
* add api gateway k8s config ([c034629](https://github.com/kkhanhluu/e-shop/commit/c0346299675f536bb90d870dffa36f9045b89ff9))
* add authentication to api gateway ([4b080c0](https://github.com/kkhanhluu/e-shop/commit/4b080c07637fe3b80cf723e2cdeaa1de10554b4d))
* add dockerfile for api gateway ([91c0f9d](https://github.com/kkhanhluu/e-shop/commit/91c0f9d0e98eaa43d0365f41ab47e1c0b918e6a0))
* add dockerfile for api gateway ([fa1c652](https://github.com/kkhanhluu/e-shop/commit/fa1c6523c22561502137d0c1f957461dd3197fc4))
* add grpc port ([a930e03](https://github.com/kkhanhluu/e-shop/commit/a930e0321ba571857f965e6dd765eace51a586fd))
* add k8s config for databases ([987b423](https://github.com/kkhanhluu/e-shop/commit/987b4237bcb87ad5134b1b62ad58f8291bdabae2))
* add k8s config for eventstore ([d8b2dfe](https://github.com/kkhanhluu/e-shop/commit/d8b2dfe848add14907595dd2a4613f5b3e1a0d1e))
* add k8s for inventory service ([8c7c4a4](https://github.com/kkhanhluu/e-shop/commit/8c7c4a4e4cd7353dcf8b778422be094036ad9094))
* add k8s for payment service ([1aa7842](https://github.com/kkhanhluu/e-shop/commit/1aa78425dd629724a19e3e76c39ad4d73852ab44))
* add k8s for product service ([c441f16](https://github.com/kkhanhluu/e-shop/commit/c441f168c04ffa32740f1fa62da94cde04a1f237))
* add k8s for review service ([a304d8a](https://github.com/kkhanhluu/e-shop/commit/a304d8a06c78d9ff42092e6fb007886df22ce711))
* add k8s for user service ([d125fea](https://github.com/kkhanhluu/e-shop/commit/d125fea2dc85d79ade2368d96bd2495bda64df72))
* add k8s for user service ([6366b71](https://github.com/kkhanhluu/e-shop/commit/6366b71a17674f5df0a56dbbf2593feca8c2345f))
* add seed script for client and user ([0edd581](https://github.com/kkhanhluu/e-shop/commit/0edd58188f1c8e325b1d7cf03787f96772b31350))


### Bug Fixes

* add claims to access token ([45887cd](https://github.com/kkhanhluu/e-shop/commit/45887cdc06ed6ae46e9474f0c830c5454c7656c3))
* fix db migration ([a1f9a1d](https://github.com/kkhanhluu/e-shop/commit/a1f9a1d9009be8f9b0ec4787f6b62dc53db798e7))
* fix product service test ([73afb1b](https://github.com/kkhanhluu/e-shop/commit/73afb1bea5a3c167c43db1e6c0d475b07bb5b0ae))
* fix test for payment service ([4f55f47](https://github.com/kkhanhluu/e-shop/commit/4f55f479a00f0b7f4329becd4dfe9d61ea614ed9))
* remove console.log ([16e2bcf](https://github.com/kkhanhluu/e-shop/commit/16e2bcf60edf5eebd76466565f38577624517801))
* udpate api gateway k8s config for authentication ([6cb466d](https://github.com/kkhanhluu/e-shop/commit/6cb466d1a31a3f7677c374bbeb1be6876f95b2b4))

### [0.0.9](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.7...@inventory-service-app/v0.0.9) (2023-03-23)


### Features

* add compensate payment endpoint ([7c000aa](https://github.com/kkhanhluu/e-shop/commit/7c000aad9d2a4a5ec7ee82d4de519965c58ab357))
* add grpc client ([6f12755](https://github.com/kkhanhluu/e-shop/commit/6f12755048b622d955838b58c991fbc870c535fc))
* add grpc client for review service ([3d447a9](https://github.com/kkhanhluu/e-shop/commit/3d447a94638685e8babf79ef5dc23cbc0d3f30fb))
* add grpc server for review service ([5e4f1c4](https://github.com/kkhanhluu/e-shop/commit/5e4f1c4ad64d2e014679eab2c8e85504a12ef37a))
* add grpc server for review service ([fe436bb](https://github.com/kkhanhluu/e-shop/commit/fe436bbc96ddcb4c58165cc5e326d81fdfa7af3d))
* add more grpc defintions ([8106f00](https://github.com/kkhanhluu/e-shop/commit/8106f001ab691596ce36d2db61f01215042c9ccc))
* add outbox table ([b665730](https://github.com/kkhanhluu/e-shop/commit/b6657307d048e92d72905e026145f1e35977007e))
* compensating transaction by queue ([1190945](https://github.com/kkhanhluu/e-shop/commit/1190945653c3898f3e516ebd8aa33a730cbb51f5))
* get order by id using grpc ([13d1942](https://github.com/kkhanhluu/e-shop/commit/13d19420bc6d6bba04d863e14d13d18f14aea2ef))
* implement create order with grpc ([8b93ec3](https://github.com/kkhanhluu/e-shop/commit/8b93ec39b3db571df3bb95fb5a99902ae6c2e897))
* implement create order with grpc ([476d80f](https://github.com/kkhanhluu/e-shop/commit/476d80fa6a92607e1760d128950bc284639bd753))
* implement grpc client for inventory service in gateway ([0803257](https://github.com/kkhanhluu/e-shop/commit/0803257c92b7f80fd4f1b1610578cdb413f01dfa))
* implement grpc client for payment service in gateway ([5a92f87](https://github.com/kkhanhluu/e-shop/commit/5a92f871cb9b5c54a49d97abc15dc26e737e5915))
* implement grpc client for product service ([17996fe](https://github.com/kkhanhluu/e-shop/commit/17996fea575e17b210c96e19d69a79cb48fe1b90))
* implement grpc client in gateway ([4ac99bd](https://github.com/kkhanhluu/e-shop/commit/4ac99bd55443b826203a7f2c993c6da9d931d10a))
* implement grpc server in inventory service ([7bd39b4](https://github.com/kkhanhluu/e-shop/commit/7bd39b433f570750fce85afe232dc7df67315860))
* implement grpc server in payment service ([15d675a](https://github.com/kkhanhluu/e-shop/commit/15d675a9c80d62c895a7e7b4f5d219611ae35a8d))
* implement grpc server in product service ([d4c7356](https://github.com/kkhanhluu/e-shop/commit/d4c73568c3576a84197ea29b7f0e47b9f3b56979))
* save pyament to mongodb ([10f1240](https://github.com/kkhanhluu/e-shop/commit/10f1240e1919f68cda1158d81f5a52e938a28aae))
* saving outbox messages in database and schedule sending them ([8140d7b](https://github.com/kkhanhluu/e-shop/commit/8140d7bdd8dad59d2bc390b72380146295762668))
* send request to compensate payment from order serivce ([089ba97](https://github.com/kkhanhluu/e-shop/commit/089ba975b8f6fa201b543a77471b75eee35b659d))
* setup trpc server ([b42cc23](https://github.com/kkhanhluu/e-shop/commit/b42cc2307d8619d57e192db29a74dfe925cb3869))
* update proto file for product service ([e16a2ea](https://github.com/kkhanhluu/e-shop/commit/e16a2ea7f910f569a15870348de27a256d6402a4))


### Bug Fixes

* migrate to nx 15.8.7 ([7d08922](https://github.com/kkhanhluu/e-shop/commit/7d089228c4e9f830d34f87e03bb927644cea416a))
* migrate to nx 15.8.7 ([efaa338](https://github.com/kkhanhluu/e-shop/commit/efaa338cf6fb8a4a2da6d3533e07b61ae946523d))
* remove overhead logs ([9492cdb](https://github.com/kkhanhluu/e-shop/commit/9492cdb95698929b491687e493e7ef5437bacb1c))
* update bom ([2da3d81](https://github.com/kkhanhluu/e-shop/commit/2da3d818bb835cdf66f519f54389f9a2a8f1afa6))
* update proto file for review service ([cc5f0c6](https://github.com/kkhanhluu/e-shop/commit/cc5f0c6f2b3f005879a04ffe240c0e59dbc1d63f))

### [0.0.8](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.7...@inventory-service-app/v0.0.8) (2023-03-20)


### Features

* add compensate payment endpoint ([7c000aa](https://github.com/kkhanhluu/e-shop/commit/7c000aad9d2a4a5ec7ee82d4de519965c58ab357))
* add outbox table ([b665730](https://github.com/kkhanhluu/e-shop/commit/b6657307d048e92d72905e026145f1e35977007e))
* compensating transaction by queue ([1190945](https://github.com/kkhanhluu/e-shop/commit/1190945653c3898f3e516ebd8aa33a730cbb51f5))
* save pyament to mongodb ([10f1240](https://github.com/kkhanhluu/e-shop/commit/10f1240e1919f68cda1158d81f5a52e938a28aae))
* saving outbox messages in database and schedule sending them ([8140d7b](https://github.com/kkhanhluu/e-shop/commit/8140d7bdd8dad59d2bc390b72380146295762668))
* send request to compensate payment from order serivce ([089ba97](https://github.com/kkhanhluu/e-shop/commit/089ba975b8f6fa201b543a77471b75eee35b659d))


### Bug Fixes

* remove overhead logs ([9492cdb](https://github.com/kkhanhluu/e-shop/commit/9492cdb95698929b491687e493e7ef5437bacb1c))
* update bom ([2da3d81](https://github.com/kkhanhluu/e-shop/commit/2da3d818bb835cdf66f519f54389f9a2a8f1afa6))

### [0.0.7](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.4...@inventory-service-app/v0.0.7) (2023-03-18)


### Features

* add compensate payment queue ([dadbb39](https://github.com/kkhanhluu/e-shop/commit/dadbb398df494ad449da0803d860c461dce98714))
* add prisma to order service ([7a7bc88](https://github.com/kkhanhluu/e-shop/commit/7a7bc880377199bf885378fb29f5abfdd1c46922))
* add prisma to product service ([d2e64a4](https://github.com/kkhanhluu/e-shop/commit/d2e64a465c57e93ba0bca8911ffdc1cbd186599e))
* add prisma to product service ([2047e94](https://github.com/kkhanhluu/e-shop/commit/2047e94357a45280f7d5b502149b3fa9df0bed27))
* add prisma to user service ([eca4245](https://github.com/kkhanhluu/e-shop/commit/eca4245be3f311dbac0884227eb765d866418ba6))
* add seed script ([0a36ec0](https://github.com/kkhanhluu/e-shop/commit/0a36ec0e07b2b9f8cd88eb8948a9c4c711cec638))
* add seed script for reviews ([cc90cdc](https://github.com/kkhanhluu/e-shop/commit/cc90cdce0933a3b54c552a89fc994e8148bb6c49))
* implement allocation in inventory service ([49ca846](https://github.com/kkhanhluu/e-shop/commit/49ca846b7e467ad560936ca4a4aaafc3a51d6bf1))
* implement allocation process in order service ([6aafb56](https://github.com/kkhanhluu/e-shop/commit/6aafb56f4da1a3ee9ae05bd72eb15adbc56f74d8))
* implement validate order in aggregate ([9353e3b](https://github.com/kkhanhluu/e-shop/commit/9353e3b0b7fd7d246595cd4a7a9122093a1fb1d8))
* implement validate order in inventory service ([8e1088b](https://github.com/kkhanhluu/e-shop/commit/8e1088bfb19b595e2965a5c911241ceab80685cf))
* implement validation process ([e6480ee](https://github.com/kkhanhluu/e-shop/commit/e6480ee85022f8e0f8a38857b154445d0b7d8ec6))
* send validate order event to queue ([a35aba5](https://github.com/kkhanhluu/e-shop/commit/a35aba5d2baa72327b5d50f742d4fad74a71432e))


### Bug Fixes

* intergration test are failing ([1003c73](https://github.com/kkhanhluu/e-shop/commit/1003c731d2efb3aa853e2b49a9819fafa48452b9))
* prevent event duplication ([b6b2305](https://github.com/kkhanhluu/e-shop/commit/b6b2305b6551cc20895232e704529c3b5b0db70c))
* remove unused events and status ([ef0a8ca](https://github.com/kkhanhluu/e-shop/commit/ef0a8ca4f5a06c5952926b939b83941aa86e1ee9))
* replace prisma in inventory service ([cb7fce6](https://github.com/kkhanhluu/e-shop/commit/cb7fce666dd0105b59bfa546ba2e8b63f76c2ddb))

### [0.0.6](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.5...@inventory-service-app/v0.0.6) (2023-03-16)


### Features

* implement validate order in aggregate ([9353e3b](https://github.com/kkhanhluu/e-shop/commit/9353e3b0b7fd7d246595cd4a7a9122093a1fb1d8))
* implement validate order in inventory service ([8e1088b](https://github.com/kkhanhluu/e-shop/commit/8e1088bfb19b595e2965a5c911241ceab80685cf))
* implement validation process ([e6480ee](https://github.com/kkhanhluu/e-shop/commit/e6480ee85022f8e0f8a38857b154445d0b7d8ec6))
* send validate order event to queue ([a35aba5](https://github.com/kkhanhluu/e-shop/commit/a35aba5d2baa72327b5d50f742d4fad74a71432e))


### Bug Fixes

* intergration test are failing ([1003c73](https://github.com/kkhanhluu/e-shop/commit/1003c731d2efb3aa853e2b49a9819fafa48452b9))

### [0.0.5](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.4...@inventory-service-app/v0.0.5) (2023-03-15)


### Features

* add prisma to order service ([7a7bc88](https://github.com/kkhanhluu/e-shop/commit/7a7bc880377199bf885378fb29f5abfdd1c46922))
* add prisma to product service ([d2e64a4](https://github.com/kkhanhluu/e-shop/commit/d2e64a465c57e93ba0bca8911ffdc1cbd186599e))
* add prisma to product service ([2047e94](https://github.com/kkhanhluu/e-shop/commit/2047e94357a45280f7d5b502149b3fa9df0bed27))
* add prisma to user service ([eca4245](https://github.com/kkhanhluu/e-shop/commit/eca4245be3f311dbac0884227eb765d866418ba6))
* add seed script ([0a36ec0](https://github.com/kkhanhluu/e-shop/commit/0a36ec0e07b2b9f8cd88eb8948a9c4c711cec638))


### Bug Fixes

* replace prisma in inventory service ([cb7fce6](https://github.com/kkhanhluu/e-shop/commit/cb7fce666dd0105b59bfa546ba2e8b63f76c2ddb))

### [0.0.4](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.3...@inventory-service-app/v0.0.4) (2023-03-11)

### [0.0.3](https://github.com/kkhanhluu/e-shop/compare/@inventory-service-app/v0.0.2...@inventory-service-app/v0.0.3) (2023-03-09)


### Features

* add payment service ([1823429](https://github.com/kkhanhluu/e-shop/commit/18234290edc2c4e8d98fcfbb6b4325855257b3c4))
* add projection ([dfb7571](https://github.com/kkhanhluu/e-shop/commit/dfb75716b849b28468b32faeb5e1a80d0f9833a9))
* add subscription and background worker ([0908de3](https://github.com/kkhanhluu/e-shop/commit/0908de3768664bf38786d6a7ff4d2128c899756e))
* implement create order saga step ([254bcd7](https://github.com/kkhanhluu/e-shop/commit/254bcd7668752d0ed87b23e75453bdc87c0fbbf3))
* implement payment saga step ([97fc84b](https://github.com/kkhanhluu/e-shop/commit/97fc84bd32fc803d28c7e4e0eef76a1939b0365d))
* implement payment state transition ([3c86d14](https://github.com/kkhanhluu/e-shop/commit/3c86d1441eb043aaefe0ed0845f0fc2ed3033e3d))
* implementing cqrs pattern ([9e13517](https://github.com/kkhanhluu/e-shop/commit/9e135176cb07cf4dd53367196d5daa5136e19909))
* implementing event sourcing ([a353014](https://github.com/kkhanhluu/e-shop/commit/a35301449939c09f62fad0648febd718dcbb053e))
* implemnting cqrs pattern ([ce4d678](https://github.com/kkhanhluu/e-shop/commit/ce4d678957e7cf895490c3e162d9ebee339dfed8))
* setup state machine in order service ([5a34c8c](https://github.com/kkhanhluu/e-shop/commit/5a34c8c565436ef6bf2aa9f5a79304f9563eacdd))


### Bug Fixes

* add event store and event serializer ([6a71ba1](https://github.com/kkhanhluu/e-shop/commit/6a71ba1e305d42fdbf64b1ff903b6ebc28e63ff7))
* do not create duplicated projections ([c693028](https://github.com/kkhanhluu/e-shop/commit/c693028abf810bdc6d77e452ba4fc12bf2d4ddc9))
* refactoring project structure ([4cc3ec1](https://github.com/kkhanhluu/e-shop/commit/4cc3ec1dfa35f68b7e4e164df46c0adff7cab239))
* update bom ([1142dd9](https://github.com/kkhanhluu/e-shop/commit/1142dd91d4d24274567587c0a25aba2243bd6e45))
* update bom version and fix order service test ([92bcc55](https://github.com/kkhanhluu/e-shop/commit/92bcc55482c309a7c9a2a959b974e43386412776))

### 0.0.2 (2023-02-10)


### Features

* add api docs for user service ([05de24c](https://github.com/kkhanhluu/e-shop/commit/05de24c223e0e245c3f42b4b875777b8726d6503))
* add api util library ([64305a2](https://github.com/kkhanhluu/e-shop/commit/64305a2bc1362c44d1c1b6f357f893fd28785d73))
* add authentication endpoints ([666cd9b](https://github.com/kkhanhluu/e-shop/commit/666cd9b105c31adf00521c8407ccea4b296c8e54))
* add create and delete product api ([26060c8](https://github.com/kkhanhluu/e-shop/commit/26060c8d45aac8ea4649df7aba52062c6c16fa6a))
* add inventory service ([5025846](https://github.com/kkhanhluu/e-shop/commit/5025846136533644d36e961cfe142c99ee762bbb))
* add list api to product service ([0900862](https://github.com/kkhanhluu/e-shop/commit/0900862ee397ddd90e65f574560f35de83adf748))
* add order service ([a0827e1](https://github.com/kkhanhluu/e-shop/commit/a0827e1f094bbc3179b138f74cf4c23abf8ab675))
* add product api and test ([eb58ceb](https://github.com/kkhanhluu/e-shop/commit/eb58ceb056775820076c4432327a4330a1b3b714))
* add register api ([c955c0e](https://github.com/kkhanhluu/e-shop/commit/c955c0ef4766149762d1d885666a677921d3c88d))
* add swagger documentation ([d28e634](https://github.com/kkhanhluu/e-shop/commit/d28e6342bdbb3c96023cc7621d68e6f032aa7e25))
* add user role ([09ebd0e](https://github.com/kkhanhluu/e-shop/commit/09ebd0ebf78517fb76293ffd996bb7714d709777))
* add user service ([4fd746e](https://github.com/kkhanhluu/e-shop/commit/4fd746e007eb6c23fa5a2694b529b3ab23564cda))
* add user service e2e test ([d124545](https://github.com/kkhanhluu/e-shop/commit/d124545d3aeb5199aee36a039f5c7df931bf6f5b))
* configure authorization server ([fae7cd8](https://github.com/kkhanhluu/e-shop/commit/fae7cd82ded259ef473ad578930520efaf06416d))
* setup api gateway ([4a3e4cc](https://github.com/kkhanhluu/e-shop/commit/4a3e4ccca4c710efd9597d0e035cf541049e82f7))
* setup bom ([f1b190a](https://github.com/kkhanhluu/e-shop/commit/f1b190a4d9c38b6b5a9131c235c76ac73cd37874))
* setup cd pipeline ([a02e9e6](https://github.com/kkhanhluu/e-shop/commit/a02e9e6ba6cf10915c533efad2caa93eb328e27c))
* setup cd pipeline ([7ba3ee2](https://github.com/kkhanhluu/e-shop/commit/7ba3ee2715d79b1d4c8616c005d8426ad422ff25))
* setup cd pipeline ([91ad6d5](https://github.com/kkhanhluu/e-shop/commit/91ad6d5730af1223c670a2328f20862c53613f00))
* setup cd pipeline ([eb60abe](https://github.com/kkhanhluu/e-shop/commit/eb60abe86352361dc1bb9ddcefd212a56e495a85))
* setup cd pipeline ([be300ac](https://github.com/kkhanhluu/e-shop/commit/be300acb7f555fbcd428cea7dffcf0deeeda9ec9))
* setup cd pipeline ([7fdb07b](https://github.com/kkhanhluu/e-shop/commit/7fdb07b7c665fbc16b4601e550b8f272ca31a956))
* setup cd pipeline ([3cc0a80](https://github.com/kkhanhluu/e-shop/commit/3cc0a8089c0b78213d29b62223ad26e71c317192))
* setup cd pipeline ([3f9de4d](https://github.com/kkhanhluu/e-shop/commit/3f9de4dde365df9dd08ed053916a3b58f9d78163))
* setup cd pipeline ([c87dc5f](https://github.com/kkhanhluu/e-shop/commit/c87dc5fb24b9697a9dd2aa9acfc79e452e15f11f))
* setup cd pipeline ([7909d3b](https://github.com/kkhanhluu/e-shop/commit/7909d3b965def129e5203f32d453b653cf294ca8))
* setup github actions ([db07821](https://github.com/kkhanhluu/e-shop/commit/db07821d359ee0fcfd692660d6a7509773a7515f))
* setup github actions ([f8c3cf6](https://github.com/kkhanhluu/e-shop/commit/f8c3cf64669ed1df3e6d0f03fc6c4a210ca88b27))
* setup product service ([40e13fd](https://github.com/kkhanhluu/e-shop/commit/40e13fdf6c94369f5770fbf6c28d210338a27962))
* setup review service ([cd6e735](https://github.com/kkhanhluu/e-shop/commit/cd6e735999d6001c42cad3f5e5ff4bd54606a7a3))
* store user and oauth2 client in database ([2ed20fa](https://github.com/kkhanhluu/e-shop/commit/2ed20fab6f59215020a5bd0404e48f4d09b30999))


### Bug Fixes

* add api utils as dependency ([2b1bffb](https://github.com/kkhanhluu/e-shop/commit/2b1bffb788dde3aa92c65743018ee5e35b30e9a0))
* add target command to user service app ([6bb0384](https://github.com/kkhanhluu/e-shop/commit/6bb0384e1985416244da383370836426b14e3d55))
* change to property yaml files ([31db64c](https://github.com/kkhanhluu/e-shop/commit/31db64c34c9501220ecd87df786d2e2eba25b651))
* connect to testcontainer while testing user service ([a2af3c1](https://github.com/kkhanhluu/e-shop/commit/a2af3c1631a790d607de3ad94fca3d8ae1542647))
* exclude snakeyml in faker dependecy ([f385b16](https://github.com/kkhanhluu/e-shop/commit/f385b16c8c2f01cc0e0304d1945588e1da9aa48c))
* fix global controller exception handler ([1aecb92](https://github.com/kkhanhluu/e-shop/commit/1aecb92c0404299e977f3cf0a3268d23275037f8))
* fix product service tests ([0399a29](https://github.com/kkhanhluu/e-shop/commit/0399a299413613f75672dcf02ed0a747de974616))
* fix release pipeline ([d8da24f](https://github.com/kkhanhluu/e-shop/commit/d8da24f385402782037e15923d15c86681880a1c))
* fix release pipeline ([96d739c](https://github.com/kkhanhluu/e-shop/commit/96d739ca99cf841899376a2eca5b1cac020fee51))
* fix release pipeline ([5000231](https://github.com/kkhanhluu/e-shop/commit/50002317b0f39d80c188b539e0424ad6bd02e924))
* fix test case for product controller ([b23c03e](https://github.com/kkhanhluu/e-shop/commit/b23c03eecf2e77d2330605a6ad6a00d3f3d3af52))
* fix user service test ([a6a0558](https://github.com/kkhanhluu/e-shop/commit/a6a0558b95864cc06d438cb46388b8743602807b))
* setup java version in ci pipelines ([9e44c10](https://github.com/kkhanhluu/e-shop/commit/9e44c10d6caa871f06ba4861fc77fe8388825d7c))
* update api util version and add actuator ([6e7456e](https://github.com/kkhanhluu/e-shop/commit/6e7456e2420d88f03fef55dfb86f9a74caa894fa))
* update bom ([0f3490b](https://github.com/kkhanhluu/e-shop/commit/0f3490b69a632c1810b51cea193ab1f9d2b9bda8))
* update bom version ([8b8fa78](https://github.com/kkhanhluu/e-shop/commit/8b8fa7872904f5160e1de95fb0c3731aa94e7a0a))
