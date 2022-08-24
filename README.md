# Java Client of Mocoapp API

This project is a Java client for the public [Mocoapp API](https://github.com/hundertzehn/mocoapp-api-docs). 
The client is based on a fluent java api that maps the cascade of methods to the Mocoapp's api endpoints. For example:

## Compilation

```shell
mvn compile
```

## Dependency

Thank's to [jitpack](https://jitpack.io/#rocketbase-io/mocoapp-api) it's very easy to use current builds

Step 1. Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Step 2. Add the dependency
```xml
<dependency>
    <groupId>com.github.rocketbase-io</groupId>
    <artifactId>mocoapp-api</artifactId>
    <version>mocoapp-api-1.0.0</version>
</dependency>
```