plugins {
    id("java")
    id("org.springframework.boot") version "3.5.3"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}

group = "org.liahnu.bot"
version = "1.0-SNAPSHOT"


repositories {
    mavenLocal()
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.mikuac:shiro:2.4.3")
    compileOnly ("org.projectlombok:lombok:1.18.30") // 使用最新版本
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.6")
    implementation("com.mysql:mysql-connector-j:9.3.0")
    implementation("cn.hutool:hutool-all:5.8.24")
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}


tasks.test {
    useJUnitPlatform()
}

docker {
    springBootApplication {
        var version = System.getenv("VERSION") ?: project.findProperty("VERSION") as String?
        if (version == null || version == "") {
            version = "1.0.0"
        }

        baseImage.set("openjdk:17-jdk-alpine")
        ports.set(listOf(5000))
        images.set(listOf(
            "registry.cn-beijing.aliyuncs.com/1328411791/mahjong-bot:$version",
            "registry.cn-beijing.aliyuncs.com/1328411791/mahjong-bot:latest"))
        jvmArgs.set(listOf("-Dspring.profiles.active=prod", "-Xmx512m"))
    }
    registryCredentials {
        url = "https://registry.cn-beijing.aliyuncs.com"
        username.set(System.getenv("DOCKERHUB_USERNAME") ?: project.findProperty("DOCKERHUB_USERNAME") as String?)
        password.set(System.getenv("DOCKERHUB_PASSWORD") ?: project.findProperty("DOCKERHUB_PASSWORD") as String?)
    }
}
