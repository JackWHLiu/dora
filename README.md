# dora

简介：这是一个为Android应用开发者量身定做的专业debug框架，专注于崩溃日志的收集。

优势：轻量级、功能强大、持续更新

allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    implementation 'com.github.JackWHLiu:dora:1.2'
}

示例代码：https://github.com/JackWHLiu/DoraDemo.git

示例策略：LogPolicy	//日志策略，仅供入门学习

					StoragePolicy	//SD卡存储策略，推荐
	
					WebPolicy	//网页查看策略，需要联网
	
					EmailPolicy	//邮件接收策略，被动、及时
	
					MessagePolicy	//短信策略，有一定成本



Extras, you can provide us your suggestions in order to help us develop future products and services.
另外，你可以提供给我们你的建议，为了帮助我们未来的产品和服务的开发。
