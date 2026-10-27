plugins {
    java
    id("net.serenity-bdd.serenity-gradle-plugin") version "5.4.1"
}

group = "co.com.pichincha"
version = "1.0-SNAPSHOT"

val serenityVersion = "5.4.1"
val cucumberVersion = "8.0.4"
val seleniumVersion = "4.51.0"
val junitVersion = "6.1.3"
val assertjVersion = "3.27.7"
val slf4jVersion = "2.0.17"

repositories {
    mavenCentral()
}

dependencies {
    // Serenity BDD + Screenplay (src/main: tasks, interactions, questions, userinterfaces)
    implementation("net.serenity-bdd:serenity-core:$serenityVersion")
    implementation("net.serenity-bdd:serenity-screenplay:$serenityVersion")
    implementation("net.serenity-bdd:serenity-screenplay-webdriver:$serenityVersion")
    implementation("net.serenity-bdd:serenity-ensure:$serenityVersion")

    // Selenium WebDriver (Selenium Manager descarga el driver automáticamente)
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

    // Aserciones y logging
    implementation("org.assertj:assertj-core:$assertjVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    // Cucumber + Serenity
    testImplementation("net.serenity-bdd:serenity-cucumber:$serenityVersion")
    testImplementation(platform("io.cucumber:cucumber-bom:$cucumberVersion"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")

    // JUnit 5 (Platform Suite para los runners de Cucumber)
    testImplementation("net.serenity-bdd:serenity-junit5:$serenityVersion")
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = 21
}

tasks.test {
    useJUnitPlatform()

    // Propaga las propiedades de ejecución, ej: gradlew test -Denvironment=qa -Dcucumber.filter.tags="@Login"
    listOf(
        "environment",
        "webdriver.driver",
        "headless.mode",
        "cucumber.filter.tags",
        "serenity.take.screenshots"
    ).forEach { key ->
        System.getProperty(key)?.let { systemProperty(key, it) }
    }

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = false
    }

    // Genera el reporte de Serenity aunque fallen pruebas
    finalizedBy(tasks.aggregate)
}

gradle.startParameter.isContinueOnFailure = true
