import org.gradle.kotlin.dsl.`kotlin-dsl`
plugins {
    `kotlin-dsl`
}

repositories{
    jcenter()
    google()
    mavenCentral()

}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jacoco:org.jacoco.core:0.8.6")
    }
}
