import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    `java-library`
}

repositories {
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT@jar")
}

group = "${project.group}"
version = "${project.version}"
description = "RandomSpawn"

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.withType<ProcessResources>() {
    expand("version" to project.version)
}