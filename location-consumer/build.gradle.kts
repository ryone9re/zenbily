import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("java")
    id("idea")
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version "0.8.18"
}

group = "dev.ryone"
version = "1.0-SNAPSHOT"

val protobufVersion = "4.31.1"
val kafkaVersion = "4.0.0"
val decatonVersion = "9.1.2"
val testcontainersVersion = "1.21.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.google.protobuf:protobuf-java:$protobufVersion")
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")

    implementation("com.linecorp.decaton:decaton-common:$decatonVersion")
    implementation("com.linecorp.decaton:decaton-client:$decatonVersion")
    implementation("com.linecorp.decaton:decaton-protobuf:$decatonVersion")
    implementation("com.linecorp.decaton:decaton-processor:$decatonVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(platform("org.testcontainers:testcontainers-bom:$testcontainersVersion"))
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.awaitility:awaitility")
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
}

idea {
    module {
        generatedSourceDirs.add(file("build/generated/source/proto/main/java"))
    }
}
