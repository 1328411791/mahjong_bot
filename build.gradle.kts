plugins {
    id("java")
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
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.6")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("cn.hutool:hutool-all:5.8.22")
}

tasks.test {
    useJUnitPlatform()
}