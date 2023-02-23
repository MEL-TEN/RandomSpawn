import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    `java-library`
    kotlin("jvm") version "1.8.10"

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }

    mavenCentral()
    mavenLocal()
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT") {
        exclude("com.google.code.gson:gson")
        exclude("org.yaml:snakeyaml")
    }

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    compileOnly("com.google.code.gson:gson:2.10.1")

    // https://mvnrepository.com/artifact/org.yaml/snakeyaml
    compileOnly("org.yaml:snakeyaml:1.33")

}

group = "${project.group}"
version = "${project.version}"

description = "RandomSpawn"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.withType<ProcessResources> {
   filter(ReplaceTokens::class, "tokens" to
       mapOf(
            "version" to project.version,
            "api-version" to "1.18"
        )
   )

    filteringCharset = "UTF-8"
}

tasks.withType<ShadowJar> {
    dependsOn("jar")
}