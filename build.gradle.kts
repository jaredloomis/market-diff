import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  kotlin("jvm") version "1.3.41"
  application
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_1_8
}

application {
  mainClassName = "com.jaredloomis.analmark.Main"
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
  implementation("org.jsoup:jsoup:1.12.1")
  implementation("org.apache.opennlp:opennlp-distr:1.9.1")
  implementation("edu.stanford.nlp:stanford-corenlp:3.9.2")
  implementation("edu.stanford.nlp:stanford-corenlp:3.9.2:models")
  implementation("org.postgresql:postgresql:42.2.6")

  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}