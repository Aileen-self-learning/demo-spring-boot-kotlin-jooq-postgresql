import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*

plugins {
	id("org.springframework.boot") version "2.6.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("nu.studer.jooq") version "7.1.1"
}

group = "com.tw.cn.example.springboot"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

buildscript {
	repositories {
		mavenLocal()
		mavenCentral()
	}
	dependencies {
		classpath("org.jooq:jooq-codegen:3.14.16")
		classpath("org.postgresql:postgresql:42.2.0")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.jooq:jooq-meta")
	implementation("org.jooq:jooq")
	implementation("org.jooq:jooq-codegen")
	runtimeOnly("org.postgresql:postgresql")
	jooqGenerator("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.12.4")
}

tasks.register("JOOQ_Code_Generate") {
	doLast{
		val config: org.jooq.meta.jaxb.Configuration = Configuration()
			.withJdbc(Jdbc()
				.withDriver("org.postgresql.Driver")
				.withUrl("jdbc:postgresql://localhost:5433/app_db")
				.withUsername("root")
				.withPassword("password"))
			.withGenerator(Generator()
				.withGenerate(Generate()
					.withComments(true) // 注释 √
					.withCommentsOnCatalogs(true)
					.withRelations(true)
					.withImmutablePojos(false) // if true, cannot use 'into()' method
					.withInterfaces(true)
					.withDaos(true))
				.withDatabase(Database()
					.withName("org.jooq.meta.postgres.PostgresDatabase")
					.withIncludes(".*")
					.withExcludes("")
					.withInputSchema("public")
				)
				.withTarget(org.jooq.meta.jaxb.Target()
					.withPackageName("")
					.withDirectory("/Users/aileen/IdeaProjects/studying/demo-spring-boot-kotlin/src/main/java"))
			)
		GenerationTool.generate(config)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
