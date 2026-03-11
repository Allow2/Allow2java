# Allow2 Java SDK (DEPRECATED)

> **This repository is deprecated and no longer maintained.**
>
> Use the **Kotlin/JVM SDK** instead: **https://github.com/Allow2/Allow2kotlin**
>
> The Kotlin SDK is fully compatible with Java projects and provides:
> - Complete Allow2 Parental Freedom device lifecycle
> - Coroutines + SharedFlow (Kotlin) and callback listener (Java) APIs
> - Maven Central distribution (`com.allow2:allow2-sdk`)
> - Active development and CI/CD

## Migration

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.allow2:allow2-sdk:2.0.0-alpha.1")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.allow2:allow2-sdk:2.0.0-alpha.1'
}
```

### Maven

```xml
<dependency>
    <groupId>com.allow2</groupId>
    <artifactId>allow2-sdk</artifactId>
    <version>2.0.0-alpha.1</version>
</dependency>
```

The Kotlin SDK works in pure Java projects. Use the `Allow2Listener` callback interface for Java-style event handling:

```java
import com.allow2.sdk.*;

Allow2Daemon daemon = new Allow2Daemon(
    "Living Room PC",
    activities,
    credentialStore,
    childResolver,
    60, 300, 300, null, null, null, null, null
);

daemon.addListener(new Allow2Listener() {
    @Override
    public void onPairingRequired(PairingInfo info) {
        System.out.println("Enter PIN: " + info.getPin());
    }

    @Override
    public void onSoftLock(String reason) {
        System.out.println("Time is up: " + reason);
    }
});
```

See the full documentation at **https://github.com/Allow2/Allow2kotlin**.
