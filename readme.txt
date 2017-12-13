该项目为HSF的一些特性使用用例
目前主要包含该的内容有：本地单元测试、异步调用、泛化调用、隐式传参特性

1.需要服务提供者时，请先启动服务提供者，在com.alibaba.demo.service包中的PubService.java
.需要服务消费者时，在com.alibaba.demo.service包中的SubService.java
2.在通过maven install时，请过滤掉test，编译方式：mvn install -DskipTests
3.测试的时候，请逐个测试方法进行测试
测试类说明：
加入开发容器(taobao-hsf.sar)
com.alibaba.demo.util.DemoUtils.java 该类里面的path变量是加入你的运行SAR，在本机测试使用

com.alibaba.hsf.feature.async.AsyncTest.java 异步测试，含future模式和callback模式
这里需要注意：
callback的测试需要采用tomcat方式，由于lightapi测试方式会导致edas-sdk中形成冲突，所以需要采用web方式测试即可，其他方式都可以通过lightapi测试
com.alibaba.hsf.feature.generic.GenericInvokeTest.java 泛化测试，提供基础类型和对象测试方式
com.alibaba.hsf.feature.unit.test.LightApiTest.java 单元测试，基于bean和schema两种方式
com.alibaba.hsf.feature.implicit.ImplicitTest.java 隐式传参测试，目前支持String类型传输