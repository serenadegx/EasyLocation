# EasyLocation

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
