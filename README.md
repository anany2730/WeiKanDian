# WeiKanDian
微看点Android客户端

![](http://7xijtp.com1.z0.glb.clouddn.com/wkd_pic.png?imageView/1/w/480/h/800)

## 项目介绍
* 主页架构采用 FragmentTabHost 实现启动主页 activity 默认只加载一页 fragment，点击底部 tab 切换 fragment 实现了复用
* 采用 Retrofit2.0 进行网络访问，Rxjava 进行线程切换
* 数据库采用 GreenDao 进行本地缓存
* 采用 RecyclerView 实现首页新闻多 item 布局
* 采用 TabLayout 实现首页顶部 Tab 切换
* 顶部 Tab 切换的 adapter 实现了延迟加载、并复用了加载过的Tab
* 采用 Glide 加载图片
* 采用 Robolectric 进行单元测试

## 关于
本项目是练手项目，尚未完善，本着可以学习较前沿的技术为目的。
