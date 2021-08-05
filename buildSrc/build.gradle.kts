import org.gradle.kotlin.dsl.`kotlin-dsl`
plugins {
    `kotlin-dsl`
}

repositories{
    google()
    mavenCentral()

}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jacoco:org.jacoco.core:0.8.7")
    }
}
