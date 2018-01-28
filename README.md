# Dropwizard Gradle Plugin
### Apply plugin
```groovy
apply plugin: 'application'
apply plugin: 'net.winterly.gradle.dropwizard'
```


### Minimal configuration
```groovy
application {
    mainClassName = "com.example.Application"
}

dropwizard {
    configuration = "local.yml"
}
```

This will create basic dropwizard tasks:
 * dropwizardCheck as `check local.yml`
 * dropwizardDropAll as `db drop-all --confirm-delete-everything local.yml`
 * dropwizardMigrate as `db migrate local.yml`


### Full configuration
```groovy
dropwizard {
    main = 'com.example.Application'
    configuration = "local.yml"
    prefix = 'dropwizard'
    classpath = 'main'
    
    command("custom") {
        args = ['custom', 'local.yml']
        // jvmArgs = []
        // etc.
    }
}
```

### Configuration reference
| Property      | Description   |
| ---           | ---           |
| main          | Main class name if not configured by `application` plugin |
| classpath     | Name of gradle classpath to use, `main` by default        |
| prefix        | Task names prefix, `dropwizard` by default, can be empty  |
| configuration | Relative path to configuration file                       |