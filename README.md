# EasyLocation

1.灵活选择GPS或者NetWork进行定位

2.验证6.0权限，无权限有回调，自行处理

3.无可用定位也有回调，自行处理（可通知用户自己打开定位功能）

4.附赠封装6.0权限Activity类

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency

	dependencies {
	        implementation 'com.github.serenadegx:EasyLocation:Tag'
	}

use:

	EasyLocation.with(this).location().start();


remove：

@Override
protected void onDestroy() {

      super.onPause();
  
      EasyLocation.with(this).location().stop();
  
}
