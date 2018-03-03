group = "net.winterly.dropwizard"
version = "1.0.4"

plugins {
    java
    maven
    id("com.gradle.plugin-publish") version "0.9.10"
}

buildscript {
    repositories.gradlePluginPortal()
    dependencies {
        classpath("com.gradle.publish:plugin-publish-plugin")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile(gradleApi())
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pluginBundle {
    website = "https://github.com/alex-shpak/dropwizard-gradle-plugin"
    vcsUrl = "https://github.com/alex-shpak/dropwizard-gradle-plugin"
    description = "Gradle plugin that creates dropwizard specific tasks"
    tags = listOf("dropwizard", "task")

    plugins {
        create("dropwizardPlugin") {
            id = "net.winterly.dropwizard"
            displayName = "Dropwizard CLI Plugin"
        }
    }
}

