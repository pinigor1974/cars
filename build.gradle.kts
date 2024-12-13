plugins {
    id("java")
    id("nebula.release") version "19.0.10"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "pro.nevercute"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val artifactoryReleaseRepo = System.getenv("RELEASE_REPO")
    ?: "https://cr.nevercute.pro:6443/repository/maven-releases/"
val artifactorySnapshotsRepo = System.getenv("SNAPSHOTS_REPO")
    ?: "https://cr.nevercute.pro:6443/repository/maven-snapshots/"
val artifactoryPublicRepo = System.getenv("PUBLIC_REPO")
    ?: "https://cr.nevercute.pro:6443/repository/maven-central/"
val artifactoryDevelopmentRepo = System.getenv("DEVELOPMENT_REPO")
    ?: "https://cr.nevercute.pro:6443/repository/maven-develop/"

val springBootVersion = "3.4.0"
val springCloudDependenciesVersion = "2024.0.0"
val dependencyManagement = "1.1.6"
val lombokVersion = "1.18.36"
val lombokBindingVersion = "0.2.0"
val mapstructVersion = "1.6.3"
val httpClientVersion = "4.5.13"
val wireMockVersion = "2.35.1"

allprojects {
    repositories {
        mavenLocal()
        maven(artifactoryPublicRepo)
        maven("https://cr.nevercute.pro:6443/repository/gradle-libs/")
        maven { url = uri("https://repo.spring.io/milestone") }
    }
    apply(plugin = "nebula.release")
    apply(plugin = "maven-publish")
}

subprojects {
    extensions.configure<PublishingExtension>() {
        publications {
            repositories {
                maven {
                    url = uri(resolveRepository())
                    credentials {
                        username = (System.getenv("REPO_MAVEN_USER")
                            ?: project.properties["repo_maven_user"] as? String)
                        password = (System.getenv("REPO_MAVEN_PASSWORD")
                            ?: project.properties["repo_maven_password"] as? String)
                    }
                }
            }
        }
    }

    extensions.findByType<JavaPluginExtension>()?.let {
        it.targetCompatibility = JavaVersion.VERSION_21
        it.sourceCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<GenerateModuleMetadata> {
        enabled = false
    }
}



java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    test {
        testLogging {
            testLogging.showStandardStreams = true // get verbose log
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
        jvmArgs = listOf(
            "-Duser.timezone=Europe/Moscow",
        )
    }
}

fun resolveRepository(): String = when {
    project.version.toString().endsWith("SNAPSHOT") -> artifactorySnapshotsRepo
    project.version.toString().contains("-dev.") -> artifactoryDevelopmentRepo
    else -> artifactoryReleaseRepo
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springBootVersion}")
    }
}

dependencies {

    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${lombokBindingVersion}")

    implementation("org.projectlombok:lombok:${lombokVersion}")
    implementation("org.projectlombok:lombok-mapstruct-binding:${lombokBindingVersion}")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:${springBootVersion}"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudDependenciesVersion}"))

    // Spring Starters
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Other
    implementation("org.apache.httpcomponents:httpclient:${httpClientVersion}")
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.github.tomakehurst:wiremock-jre8-standalone:${wireMockVersion}")
}
