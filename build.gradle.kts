plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "ru.otus"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
    mavenLocal()
    mavenCentral()
}

val jjwtApi: String by project
val jjwtImpl: String by project
val jjwtJackson: String by project
val mapstruct: String by project
val mapstructProcessor: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.flywaydb:flyway-core")
    implementation("io.jsonwebtoken:jjwt-api:$jjwtApi")
    implementation("io.jsonwebtoken:jjwt-impl:$jjwtImpl")
    implementation("io.jsonwebtoken:jjwt-jackson:$jjwtJackson")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.mapstruct:mapstruct:$mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructProcessor")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

