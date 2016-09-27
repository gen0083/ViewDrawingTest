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

Probably it was a bug on Spoon.
