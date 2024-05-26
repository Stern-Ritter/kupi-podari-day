rootProject.name = "kupi-podari-day"

pluginManagement {
    val springframeworkBoot: String by settings
    val dependencyManagement: String by settings

    plugins {
        id("org.springframework.boot") version springframeworkBoot
        id("io.spring.dependency-management") version dependencyManagement
    }
}