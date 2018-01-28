# Dropwizard Gradle Plugin

## Apply plugin
```groovy
apply plugin: 'net.winterly.gradle.dropwizard'
```

## Configure
```groovy
dropwizard {
    main = 'com.example.Application'
    command("check") {
        args = ['check', 'local.yml']
    }
}
```
`main` parameter is optional if you configured `application` plugin