plugins {
    kotlin("jvm") version "2.1.10"
}

group = "me.cdh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:3.5.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks{
    compileJava{
        dependsOn(compileKotlin)
        doFirst{
            options.compilerArgs = listOf(
                "--module-path", classpath.asPath
            )
        }
    }
    compileKotlin {
        destinationDirectory.set(compileJava.get().destinationDirectory)
    }
    jar{
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
task("copyDependencies", Copy::class) {
    configurations.compileClasspath.get()
        .filter { it.extension == "jar" }
        .forEach { from(it.absolutePath).into("${layout.buildDirectory.get()}/libs") }
}