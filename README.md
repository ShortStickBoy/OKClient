# OKClient

# Notice

根据 OKHttp 官方的 [发布说明](https://github.com/square/okhttp/blob/master/CHANGELOG.md#version-3130) ，从 Version 3.13 起，需要在 `build.gradle` 中增加对 Java 8 的支持。

```
android {
    compileOptions {
        targetCompatibility = "8"
        sourceCompatibility = "8"
    }
}
```