# easyview
这是一个自定义的textview，让你摆脱各种shape，selector文件。只需要在布局文件设置相应属性即可。
# 使用方式
在app gradle中:
	dependencies {
	
		compile 'com.github.duolaimiha:easyview:1.0.0'
		
	}
	
在项目根 build.gradle中：

	allprojects {
	
		repositories {
		
			...
			
			maven { url 'https://jitpack.io' }
		
		}
		
	}
  
# xml中设置：
  solidColor             实体颜色，也就是背景颜色
  
  touchSolidColor	 按住按下时候背景颜色
  
  touchTextColor 	 按下后文字颜色
  
  cornesRadius 		 设置整体弧度
  
  topLeftRadius topRightRadius bottomLeftRadius bottomRightRadius	分别设置四个角的弧度
  
  stroke_Width	 设置边框大小
  
  stroke_Color	 设置边框颜色
  
  enableColor	 设置enable为false的时候背景颜色
  
  enableTextColor 设置enable为false的时候文字颜色
  
  shapeType     OVAL表示设置圆形
