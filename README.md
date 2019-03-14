# OKClient

# 注意

根据 OKHTTP 官方的 [发布说明](https://github.com/square/okhttp/blob/master/CHANGELOG.md#version-3130) ，从 OKHTTP Version 3.13.0 起，需要在 `build.gradle` 中增加对 Java 8 的支持。为了支持和使用 OKHTTP 的更多特性，OKClient 从 Version 1.1.4 开始，基于 OKHTTP Version 3.13.1 进行开发，如果你项目中引用的 OKClient 版本为 Version 1.1.4 或更高版本，也需要在项目的 `build.gradle` 中增加对 Java 8 的支持。

```
android {
    compileOptions {
        targetCompatibility = "8"
        sourceCompatibility = "8"
    }
}
```