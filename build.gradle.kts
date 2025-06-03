plugins {
    id("java")
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.liahnu.bot"
version = "1.0-SNAPSHOT"

repositories {
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
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.test {
    useJUnitPlatform()
}