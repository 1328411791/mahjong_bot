plugins {
    id("java")
    id("org.springframework.boot") version "3.5.0"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}

group = "org.liahnu.bot"
version = "1.0-SNAPSHOT"


repositories {
    mavenLocal()
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.mikuac:shiro:2.4.3")
    compileOnly ("org.projectlombok:lombok:1.18.30") // 使用最新版本
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.6")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("cn.hutool:hutool-all:5.8.22")
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

docker {
    springBootApplication {
        baseImage.set("openjdk:17-jdk-alpine")
        ports.set(listOf(5000))
        images.set(listOf("ghcr.io/1328411791/mahjong-bot:${version}", "ghcr.io/1328411791/mahjong-bot:latest"))
        jvmArgs.set(listOf("-Dspring.profiles.active=prod", "-Xmx512m","-spring.profiles.active=prod"))
    }
    registryCredentials {
        url = "https://ghcr.io"
        username.set(System.getenv("GITHUB_USERNAME") ?: project.findProperty("GITHUB_USERNAME") as String?)
        password.set(System.getenv("GITHUB_TOKEN") ?: project.findProperty("GITHUB_TOKEN") as String?)
    }
}
