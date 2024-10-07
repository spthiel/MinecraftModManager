plugins {
    id("com.gradleup.shadow") version "8.3.3"
    id("java")
}

group = "xyz.elspeth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("com.vdurmont:semver4j:3.1.0")
    implementation("com.diogonunes:JColor:5.5.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "xyz.elspeth.Main"
    }
}