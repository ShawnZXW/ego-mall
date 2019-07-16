## ego-mall 易购商城介绍

### 1、采用电商行业模式

- **B2C** ：商家到客户

### 2、易购商城的模式 

- 易购网上商城是一个综合性的 B2C 平台，类似京东商城、天猫商城。会员可以在商城浏览商品、下订单，以及参加各种活动。
- 管理员、运营可以在平台后台管理系统中管理商品、订单、会员等。 

- 客服可以在后台管理系统中处理用户的询问以及投诉。

#### 3、功能描述 

- 后台管理系统：管理商品、订单、类目、商品规格属性、用户管理以及内容发布等功能。 
- 前台系统：用户可以在前台系统中进行注册、登录、浏览商品、首页、下单等操作。 
- 会员系统：用户可以在该系统中查询已下的订单、收藏的商品、我的优惠券、团购等信息。 
- 订单系统：提供下单、查询订单、修改订单状态、定时处理订单。 
  搜索系统：提供商品的搜索功能。 
- 单点登录系统：为多个系统之间提供用户登录凭证以及查询登录用户的信息。 

### 4、技术架构

**分布式架构**：
把系统按照模块拆分成多个子系统。 

**优点**： 
1、把模块拆分，使用接口通信，降低模块之间的耦合度。 
2、把项目拆分成若干个子项目，不同的团队负责不同的子项目。 
3、增加功能时只需要再增加一个子项目，调用其他系统的接口就可以。 
4、可以灵活的进行分布式部署。 

**缺点**： 
系统之间交互需要使用远程通信，接口开发增加工作量。

### 5、技术选型

**Java（核心编程语言）** 
**Spring、SpringMVC、Mybatis（三大框架）** 
**Dubbo（分布式服务框架）** 
**Zookeeper（服务注册中心）** 
**Redis（缓存数据库）** 
**Elasticseach（搜索引擎）** 
**SSO（单点登录）** 
**MySql（数据库）** 
**Nginx（web 服务器）** 
**Vsftp（文件上传服务器）** 
**RabbitMQ（消息队列）** 
**Alipay（支付宝支付）** 
**Geetest（极验验证）** 
**jQuery、Bootstrap（前端框架）** 
**doT.js（模板引擎）**

**Google Kaptcha（图形验证码）**

**UEditor（富文本编辑器）** 

###  6、开发环境和工具

  IntelliJ IDEA 
Maven 3.6.0 
JDK 1.8.0_202 
Jetty 9.4.14.v20181114 
Tomcat 8.5.37 
MySql 5.7.20 
Nginx 1.14.2 
Dubbo 2.6.0 
Zookeeper 3.4.13 
Redis 5.0.3 
Elasticsearch 2.4.5 
Win10（开发环境）、Centos7.3-1611（部署环境）

### 7、人员配置

产品经理：3 人，确定需求以及给出产品原型图。 
项目经理：1 人，项目管理。 
架构师：2 人，负责解决技术难点与定制标准（开发准则、接口标准、技术选型等）。 
前端团队：5 人，根据产品经理给出的原型制作静态页面。 
安卓团队：5 人，开发 android 客户端。 
IOS 团队：5 人，开发 IOS 客户端。 
后端团队：20 人，实现整个系统业务功能。 
测试团队：5 人，测试所有的功能、性能、安装。 
运维团队：3 人，项目的发布以及维护。 
如果采用 oracle，需要配备 DBA：2 人。 

### 8、易购商城研发优势 

商城系统优势： 
1. 节约成本。 
   包括硬件成本和软件成本，硬件包括店面、房租、装修、印刷、纸张等最必须用品，
   软件包括网上商城购物系统、网络信息、图片、视屏等，都可长期使用、良性循环、
   经济和环保。 

2. 营销推广经济、便捷 

   传统媒体广告费用高昂，更适合于进行品牌塑造；而网络营销主要是策略与定位把
   控的问题，实惠很多，费用与传统媒体相比微乎其微，并且流量与用户也更加精准。 
3. 信息更加立体、全面 
   通过互联网，企业的信息展示、品牌塑造和形象宣传可以通过文字、图片、音频、
   视频等多维度进行现实与虚拟相结合的展示，使用户对企业的了解更加立体和全面，
   有助于形成良好的形象与口碑。 

4. 管理高效、便捷 
   运用信息化的数据库管理，各类信息精准、清晰、无误的保存，避免出现人工操作
   出现低级错误的情况，可随时查阅、核算、统计。 

### 9、功能点

1.   商家入驻 
商家入驻功能包括：商家入驻申请流程、商家店铺自定义装修功能、多套店铺模板
选择、商家店铺街展示、商家独立店铺功能、商铺报表统计功能、搜索店铺列表页、
商家自定义广告位、区分平台与商家分类、类型、订单分单功能（按商家）、订单
退换功能、商家订单佣金结算等。 
2.   会员中心 
会员中心功能包括：会员中个人主页美化、会员中心订单列表美化、会员中心收货
地址列表美化  、会员中心缺货登记、会员中心退换货、会员中心退换货详情页  、
用户信息、新增会员头像上传功能  、平台红包、物流跟踪功能、资金管理 
3.   购物车 
购物车功能包括：购物车选择购买功能、购物车加强功能、商品促销满减、满赠、
折扣功能、凑单功能、简化购物流程、购物车为空时去购物功能、提示购物车商品
是否有库存。 
4.   订单 
订单功能包括：商家自定义配送方式和运费、门店自提功能、发票功能、商品无库
存时提交订单弹窗提示继续购物、结算页面无货提交弹出框、快递配送方式选择如
韵达。 
5.   积分商城 
积分商城功能包括：积分商品列表、兑换商品排序、精品推荐、热门兑换、兑换商
品详情页。 
6.   拍卖活动 
拍卖活动功能包括：拍卖活动列表、竞拍商品排序和搜索、拍卖商品详情页、出价
记录、商家店铺。 
7.    优惠活动 
     优惠活动功能包括：优惠活动列表页、优惠范围、优惠方式。 
