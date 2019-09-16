# OKClient

[![](https://jitpack.io/v/com.sunzn/OKClient.svg)](https://jitpack.io/#com.sunzn/OKClient)
[![](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

### Gradle
```groovy
dependencies {
    implementation 'com.sunzn:OKClient:1.1.9'
}
```

### Notice

根据 OKHTTP 官方的 [发布说明](https://github.com/square/okhttp/blob/master/CHANGELOG.md#version-3130) ，从 OKHTTP Version 3.13.0 起，需要在 `build.gradle` 中增加对 Java 8 的支持。为了更好的支持 OKHTTP 的新特性，OKClient 从 Version 1.1.7 开始，基于 OKHTTP Version 4.0.1 进行二次开发，如果你项目中引用的 OKClient 版本为 Version 1.1.7 或更高版本，也需要在项目的 `build.gradle` 中增加对 Java 8 的支持。

```
android {
    compileOptions {
        targetCompatibility = "8"
        sourceCompatibility = "8"
    }
}
```