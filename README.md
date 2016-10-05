# ViewDrawingTest
Custom view test to draw as expected

How do you test your custom view draw as expected?

This is a sample project for custom view testing.

Using Espresso and Spoon then took screenshots it.

Spoon is great useful but it needs `WRITE_EXTERNAL_STORAGE` permission.

I don't want to add it in product.

Then I add productFlavors `UiTest` and add the permission only in it.

### Create productFlavor for view drawing test

e.g. Add flavor `UiTest`

Add your app/build.gradle:

```
android {
    productFlavors {
        Default {
        }
        UiTest {
            applicationIdSuffix ".uiTest"
        }
    }
    // UiTestRelease is not necessary
    android.variantFilter { variant ->
        if(variant.buildType.name.equals('release')
                && variant.getFlavors().get(0).name.equals('UiTest')) {
            variant.setIgnore(true);
        }
    }
}
```

### Set up Espresso and Spoon

Add project root build.gradle:

```
        classpath 'com.stanfy.spoon:spoon-gradle-plugin:1.2.2'
```

Add app/build.gradle:

```
apply plugin: 'spoon'


android {
    defaultConfig {
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    androidTestCompile('com.android.support.test:runner:0.5', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    androidTestCompile 'com.squareup.spoon:spoon-client:1.6.4'
}
```

### Create directory for flavor you added

Change scope to `Project` on project tool window.

Create directories UiTest and androidTestUiTest and put these files.

```
<project root>
+-app
  +-src
    +-androidUiTest
    | +-java
    |   +-<your package>
    |      +-Your test code for view drawing test
    +-UiTest
      +- AndroidManifest.xml
      +-java
      | +-<your package>
      |   +-Activity for view drawing test
      +-res // If you want to use layout.xml for activity
```

### Add AndroidManifest.xml

In AndroidManifest.xml, you do these:

- Add uses-permission `WRITE_EXTERNAL_STORAGE`
- Define activity you added for view drawing test

### Write test code and activity

Write test code and activity for your custom view.

Test code dose not need assertions, so it's quite easy.

## Usage

1. Change buildVariant to `UiTestDebug`.
1. Run `./gradlew :app:assembleUiTestDebug`
1. Run `./gradlew :app:assembleUiTestDebugAndroidTest`
1. Run `./gradlew :app:spoonUiTestDebugAndroidTest`
1. Then reports are generated in app/build/spoon/UiTest directory.
1. Open index.html with your browser.

## Issue?

Screenshots took by spoon are different from on real device.

Here is a sample screenshot by Android Studio.
Lines are drawn both side of TextView.

![On real device](./images/actualy_displayed_on_device.png)

The same screen took by spoon is here.
Right line was not drawn.

![By Spoon](./images/screenshot_on_spoon.png)

Why the difference is occurred?

Probably it causes that different canvas were passed `onDraw()` method to androidTest and non test.

When run androidTest `getHeight()` returned too large value (i.e. 1436).

So underline in screenshots took by Spoon was out of window.

### Debug log

On app-UiTest-debug.apk:

```
10-05 17:35:09.972 1992-1992/jp.gcreate.sample.viewdrawingtest.uiTest D/test: canvas:android.view.GLES20RecordingCanvas@30073153, height:96, width:983
10-05 17:35:15.412 1992-1992/jp.gcreate.sample.viewdrawingtest.uiTest D/test: canvas:android.view.GLES20RecordingCanvas@2a498faf, height:96, width:983
```

On app-UiTest-debug-androidTest.apk:
```
0-05 17:37:37.955 3888-3888/jp.gcreate.sample.viewdrawingtest.uiTest D/test: canvas:android.view.GLES20RecordingCanvas@375e49fb, height:1436, width:983
10-05 17:37:37.982 3888-3888/jp.gcreate.sample.viewdrawingtest.uiTest D/test: canvas:android.graphics.Canvas@1cad5ead, height:1919, width:1079
```