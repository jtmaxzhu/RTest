# RTest
6.86寸模组UI自动化测试

1.该项目为6.86寸智能模组的UI自动化测试代码库
目录说明：  
androidTest文件夹存放UI自动化代码的，其他的main和test是安卓开发的文件夹  
androidTest-java-base：主要是存放测试基类代码，这里做一下测试前的准备工作，比如注册监听器，初始化相关全局变量，分为APP和PAD两部分，这里主要是PAD  
androidTest-java-config：主要是定义目标应用的包名活动名称变量，操作延时全局变量  
androidTest-java-framework（了解）：这里是一些核心配置文件，主要是失败重试  
androidTest-java-lib-adblib（了解）：网络adb代码主要是为后期性能数据采集使用  
androidTest-java-lib-perftool（了解）：一些性能测试工具类  
androidTest-java-lib-pectool（了解）：图片对比工具类，暂未使用  
androidTest-java-page（掌握）：页面对象封装，分为APP和PAD，封装单个页面的元素和操作，命名方式为Page_xxx,图片是具体哪个页面，便于理解  
androidTest-java-suite（掌握）：测试套件类，定义运行哪几个testcase  
androidTest-java-testcase（掌握）：测试用例，具体的测试逻辑在这里编写，分为APP和PAD，二级目录分为uitest（UI自动化）和perftest（UI驱动的性能自动化逻辑，无需关注），代码逻辑
在uitest下编写，命名方式为RokiPad_xxx。  
RokiPad_center 代表个人中心模块的所有UI逻辑测试用例  
RokiPad_recipe 代表菜谱模块的所有UI逻辑测试用例  
RokiPad_wifi 代表wifi模块的所有UI逻辑测试用例  
RokiPad_smart 代表智能设定模块的所有UI逻辑测试用例，分5916s和8236s  
RokiPad_login 代表用户登录模块的所有UI逻辑测试用例  
RokiPad_fan 代表风量档位控制模块的所有UI逻辑测试用例  


