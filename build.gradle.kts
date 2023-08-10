plugins {
    java
    id("io.izzel.taboolib") version "1.56"
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
}

taboolib {
    install("common")
    install("common-5")
    install("module-chat")
    install("module-configuration")
    install("module-lang")
    install("module-metrics")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("module-nms")
    install("module-nms-util")
    classifier = null
    version = "6.0.11-31"

    description {
        contributors {
            name("Yunleah763")
        }

    }
}

repositories {
    mavenCentral()
}

configurations{
    maybeCreate("packShadow")
    get("compileOnly").extendsFrom(get("packShadow"))
    get("packShadow").extendsFrom(get("taboo"))
}

dependencies {
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly(fileTree("libs"))
    "packShadow"(kotlin("stdlib"))
    "packShadow"("org.openjdk.nashorn:nashorn-core:15.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
