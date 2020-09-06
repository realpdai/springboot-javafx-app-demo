# springboot-javafx-app-demo

## Spring Boot - JavaFX 2.0应用

> 很多人对Java开发native程序第一反应还停留在暗灰色单一风格的Java GUI界面，开发方式还停留在AWT或者Swing。本文主要基于SpringBoot和JavaFX开发一个Demo给你展示Java Native应用可以做到什么样的程度。当然JavaFX 2.0没有流行起来也是有原因的，而且目前native的选择很多，前端是个框架都会搞个native... @pdai

- [Spring Boot - JavaFX 2.0应用](#spring-boot---javafx-20%e5%ba%94%e7%94%a8)
  - [技术背景 - Java 8的新特性之JavaFX2.0](#%e6%8a%80%e6%9c%af%e8%83%8c%e6%99%af---java-8%e7%9a%84%e6%96%b0%e7%89%b9%e6%80%a7%e4%b9%8bjavafx20)
    - [全新现代主题：Modena](#%e5%85%a8%e6%96%b0%e7%8e%b0%e4%bb%a3%e4%b8%bb%e9%a2%98modena)
    - [用于 CSS 结构的公共 API](#%e7%94%a8%e4%ba%8e-css-%e7%bb%93%e6%9e%84%e7%9a%84%e5%85%ac%e5%85%b1-api)
    - [WebView 增强功能](#webview-%e5%a2%9e%e5%bc%ba%e5%8a%9f%e8%83%bd)
    - [JavaFX Scene Builder 2.0](#javafx-scene-builder-20)
    - [JavaFX 3D](#javafx-3d)
    - [富文本](#%e5%af%8c%e6%96%87%e6%9c%ac)
    - [TreeTableView](#treetableview)
    - [日期控件DatePicker](#%e6%97%a5%e6%9c%9f%e6%8e%a7%e4%bb%b6datepicker)
  - [Spring Boot+JavaFX2 Demo介绍](#spring-bootjavafx2-demo%e4%bb%8b%e7%bb%8d)
    - [程序加载 - Loader](#%e7%a8%8b%e5%ba%8f%e5%8a%a0%e8%bd%bd---loader)
    - [和WEB一样风格的GUI](#%e5%92%8cweb%e4%b8%80%e6%a0%b7%e9%a3%8e%e6%a0%bc%e7%9a%84gui)
    - [对话框 - Popup](#%e5%af%b9%e8%af%9d%e6%a1%86---popup)
    - [Web应用嵌入 - Webview](#web%e5%ba%94%e7%94%a8%e5%b5%8c%e5%85%a5---webview)
    - [多种主题切换 - Theme](#%e5%a4%9a%e7%a7%8d%e4%b8%bb%e9%a2%98%e5%88%87%e6%8d%a2---theme)
    - [消息及配置悬浮框 - Message/Configuration...](#%e6%b6%88%e6%81%af%e5%8f%8a%e9%85%8d%e7%bd%ae%e6%82%ac%e6%b5%ae%e6%a1%86---messageconfiguration)
    - [全屏最大化最小化 - FullScreen/Max/Min/Close](#%e5%85%a8%e5%b1%8f%e6%9c%80%e5%a4%a7%e5%8c%96%e6%9c%80%e5%b0%8f%e5%8c%96---fullscreenmaxminclose)
  - [示例代码](#%e7%a4%ba%e4%be%8b%e4%bb%a3%e7%a0%81)

> <span style='color:red;font-size:25px;'>最全的Java后端知识体系</span><span style='color:red;font-size:25px;'> [https://www.pdai.tech](https://www.pdai.tech)</span>, <span style='color:red;font-size:25px;'>每天更新中...</span>。

### 技术背景 - Java 8的新特性之JavaFX2.0

#### 全新现代主题：Modena

新的Modena主题来替换原来的Caspian主题。不过在Application的start()方法中，可以通过setUserAgentStylesheet(STYLESHEET_CASPIAN)来继续使用Caspian主题。

参考http://fxexperience.com/2013/03/modena-theme-update/


#### 用于 CSS 结构的公共 API

 + CSS 样式设置是 JavaFX 的一项主要特性
 + CSS 已专门在私有 API 中实现（com.sun.javafx.css 软件包）
 + 多种工具（例如 Scene Builder）需要 CSS 公共 API
 + 开发人员将能够定义自定义 CSS 样式

#### WebView 增强功能

+ Nashorn JavaScript 引擎 https://blogs.oracle.com/nashorn/entry/open_for_business
+ WebSocket http://javafx-jira.kenai.com/browse/RT-14947
+ Web Workers http://javafx-jira.kenai.com/browse/RT-9782

#### JavaFX Scene Builder 2.0

可视化工具，加速JavaFX图形界面的开发:

JavaFX Scene Builder如同NetBeans一般，通过拖拽的方式配置界面，待完成界面之後，保存为FXML格式文件，此文件以XML描述物件配置，再交由JavaFX程式处理，因此可減少直接以JavaFX编写界面的困難度。

JavaFX Scene Builder 2.0新增JavaFX Theme预览功能，菜单「Preview」→「JavaFX Theme」选择不同的主題，包括：

+ Modena (FX8).
+ Modena Touch (FX8).
+ Modena High Contrast – Black on White (FX8).
+ Modena High Contrast – White on Black (FX8).
+ Modena High Contrast – Yellow on Black (FX8).
+ Caspian (FX2).
+ Caspian Embedded (FX2).
+ Caspian Embedded QVGA (FX2).

#### JavaFX 3D

在JavaFX8中提供了3D图像处理API，包括Shape3D (Box, Cylinder, MeshView, Sphere子类),SubScene, Material, PickResult, LightBase (AmbientLight 和PointLight子类),SceneAntialiasing等。Camera类也得到了更新。从JavaDoc中可以找到更多信息。

#### 富文本

强化了富文本的支持

#### TreeTableView

TreeTable支持

#### 日期控件DatePicker

增加日期控件


### Spring Boot+JavaFX2 Demo介绍


#### 程序加载 - Loader

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucGRhaS50ZWNoL19pbWFnZXMvc3ByaW5nL3NwcmluZ2Jvb3QtamF2YWZ4LWFwcC0xLnBuZw?x-oss-process=image/format,png)

#### 和WEB一样风格的GUI

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucGRhaS50ZWNoL19pbWFnZXMvc3ByaW5nL3NwcmluZ2Jvb3QtamF2YWZ4LWFwcC0yLnBuZw?x-oss-process=image/format,png)

#### 对话框 - Popup

![](https://www.pdai.tech/_images/spring/springboot-javafx-app-3.png)

#### Web应用嵌入 - Webview

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucGRhaS50ZWNoL19pbWFnZXMvc3ByaW5nL3NwcmluZ2Jvb3QtamF2YWZ4LWFwcC00LnBuZw?x-oss-process=image/format,png)

#### 多种主题切换 - Theme

![](https://www.pdai.tech/_images/spring/springboot-javafx-app-5.png)

#### 消息及配置悬浮框 - Message/Configuration...

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucGRhaS50ZWNoL19pbWFnZXMvc3ByaW5nL3NwcmluZ2Jvb3QtamF2YWZ4LWFwcC02LnBuZw?x-oss-process=image/format,png)

#### 全屏最大化最小化 - FullScreen/Max/Min/Close
> 包括全屏是基于JavaFX的一个组件，不是原生。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucGRhaS50ZWNoL19pbWFnZXMvc3ByaW5nL3NwcmluZ2Jvb3QtamF2YWZ4LWFwcC03LnBuZw?x-oss-process=image/format,png)

### 如何部署
> 收到几个开发问如何进行运行和部署，统一回复下：

+ 安装jar到本地的maven库

![](https://www.pdai.tech/_images/spring/springboot-javafx-app-10.png)

具体执行maven安装的脚本如下（这里D:\git\github\springboot-javafx-app-demo是我本地的项目目录，需要改成你自己的）：

```bash
mvn install:install-file -DgroupId=gn -DartifactId=GNCalendar -Dversion=v1.0 -Dpackaging=jar -Dfile=D:\git\github\springboot-javafx-app-demo\lib\GNCalendar-1.0-alpha.jar

mvn install:install-file -DgroupId=gn -DartifactId=GNButton -Dversion=v1.1.0 -Dpackaging=jar -Dfile=D:\git\github\springboot-javafx-app-demo\lib\GNButton-1.1.0.jar

mvn install:install-file -DgroupId=gn -DartifactId=GNCarousel -Dversion=v2.1.5 -Dpackaging=jar -Dfile=D:\git\github\springboot-javafx-app-demo\lib\GNCarousel-2.1.5.jar

mvn install:install-file -DgroupId=gn -DartifactId=GNDecorator -Dversion=v2.1.2-alpha -Dpackaging=jar -Dfile=D:\git\github\springboot-javafx-app-demo\lib\GNDecorator-2.1.2-alpha.jar

mvn install:install-file -DgroupId=gn -DartifactId=GNAvatarView -Dversion=v1.0-rc -Dpackaging=jar -Dfile=D:\git\github\springboot-javafx-app-demo\lib\GNAvatarView-1.0-rc.jar

```

在这里执行：

![](https://www.pdai.tech/_images/spring/springboot-javafx-app-8.png)


+ 编译的maven插件

![](https://www.pdai.tech/_images/spring/springboot-javafx-app-9.png)


+ pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.4.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>spring-fx-app</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-fx-app</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<!-- mvn install:install-file -DgroupId=gn -DartifactId=GNAvatarView -Dversion=v1.0-rc -Dpackaging=jar -Dfile=D:\git\github\springb
oot-javafx-app-demo\lib\GNAvatarView-1.0-rc.jar
-->
		<dependency>
			<groupId>gn</groupId>
			<artifactId>GNAvatarView</artifactId>
			<version>v1.0-rc</version>
		</dependency>
		<dependency>
			<groupId>gn</groupId>
			<artifactId>GNButton</artifactId>
			<version>v1.1.0</version>
		</dependency>
		<dependency>
			<groupId>gn</groupId>
			<artifactId>GNCalendar</artifactId>
			<version>v1.0</version>
		</dependency>
		<dependency>
			<groupId>gn</groupId>
			<artifactId>GNCarousel</artifactId>
			<version>v2.1.5</version>
		</dependency>
		<dependency>
			<groupId>gn</groupId>
			<artifactId>GNDecorator</artifactId>
			<version>v2.1.2-alpha</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.github.typhon0/AnimateFX -->
		<dependency>
			<groupId>io.github.typhon0</groupId>
			<artifactId>AnimateFX</artifactId>
			<version>1.2.1</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.controlsfx/controlsfx -->
		<dependency>
			<groupId>org.controlsfx</groupId>
			<artifactId>controlsfx</artifactId>
			<version>8.40.14</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/de.jensd/fontawesomefx -->
		<dependency>
			<groupId>de.jensd</groupId>
			<artifactId>fontawesomefx</artifactId>
			<version>8.9</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.jfoenix/jfoenix -->
		<dependency>
			<groupId>com.jfoenix</groupId>
			<artifactId>jfoenix</artifactId>
			<version>8.0.7</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/eu.hansolo/tilesfx -->
		<dependency>
			<groupId>eu.hansolo</groupId>
			<artifactId>tilesfx</artifactId>
			<version>1.6.5</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/eu.hansolo/colors -->
		<dependency>
			<groupId>eu.hansolo</groupId>
			<artifactId>colors</artifactId>
			<version>1.4</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
<!--			<plugin>-->
<!--				<groupId>org.springframework.boot</groupId>-->
<!--				<artifactId>spring-boot-maven-plugin</artifactId>-->
<!--			</plugin>-->

				<plugin>
					<groupId>com.zenjava</groupId>
					<artifactId>javafx-maven-plugin</artifactId>
					<version>8.8.3</version>
					<configuration>
						<vendor>pdai</vendor>
						<mainClass>com.pdai.javafx.app.SpringFxAppApplication</mainClass>
						<allPermissions>true</allPermissions>
					</configuration>
				</plugin>
		</plugins>
	</build>

</project>
```

+ 以jar运行为例：

```bash
D:\git\github\springboot-javafx-app-demo>java -jar D:\git\github\springboot-javafx-app-demo\target\jfx\native\spring-fx-app-0.0.1-SNAPSHOT\app\spring-fx-app-0.0.1-SNAPSHOT-jfx.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.4.RELEASE)

2020-07-01 06:27:46.091  INFO 144952 --- [onPool-worker-1] o.s.boot.SpringApplication               : Starting application on pdai with PID 144952 (started by pdai in D:\git\github\springboot-javafx-app-demo)
2020-07-01 06:27:46.099  INFO 144952 --- [onPool-worker-1] o.s.boot.SpringApplication               : No active profile set, falling back to default profiles: default
2020-07-01 06:27:47.099  INFO 144952 --- [onPool-worker-1] o.s.boot.SpringApplication               : Started application in 1.784 seconds (JVM running for 2.838)
2020-07-01 06:27:47.163  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:51.932  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:53.084  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:54.166  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:54.207  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:54.263  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:54.322  WARN 144952 --- [JavaFX-Launcher] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:56.569  WARN 144952 --- [lication Thread] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:56.590  WARN 144952 --- [lication Thread] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:56.694  WARN 144952 --- [lication Thread] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
2020-07-01 06:27:56.707  WARN 144952 --- [lication Thread] javafx                                   : Loading FXML document with JavaFX API of version 8.0.171 by JavaF
X runtime of version 8.0.65
```

### 示例代码

@See https://github.com/realpdai/springboot-javafx-app-demo
