# dora

简介：这是一个为Android应用开发者量身定做的专业debug框架，专注于崩溃日志的收集。

优势：轻量级、功能强大、持续更新

allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    implementation 'com.github.JackWHLiu:dora:2.0.5'
}

示例代码：https://github.com/JackWHLiu/DoraDemo.git

示例策略：					

	LogPolicy	//日志策略，仅供入门学习
					
	StoragePolicy	//SD卡存储策略，推荐
	
	WebPolicy	//网页查看策略，需要联网
	
	EmailPolicy	//邮件接收策略，被动、及时
	
	MessagePolicy	//短信策略，有一定成本

示例过滤器：

	DefaultFilter 	//不做过滤处理
	
	TimeFilter	//只处理上班时间产生的崩溃信息	
	
	ActivityThreadFilter	//只处理主线程的崩溃信息

示例分组：
	
	DefaultGroup 	//默认分组，则不分组
	
	AndroidVersionGroup 	//按Android手机版本号分组，比如Android10
	
	BrandGroup		//按Android手机品牌分组，比如vivo、OPPO
	
	ComplexGroup		//按交集、并集或补集来组合2种以上分组
	
Extras, you can provide us your suggestions in order to help us develop future products and services.
另外，你可以提供给我们你的建议，为了帮助我们未来的产品和服务的开发。

Email : 924666990@qq.com
