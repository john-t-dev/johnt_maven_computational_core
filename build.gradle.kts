plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    signing
}

group = "io.github.john-t-dev"
version = "1.0.2"

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("JohnT Computational Core")
                description.set("A legacy supply chain testing library.")
                url.set("https://github.com/john-t-dev/johnt_maven_computational_core")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        id.set("john-t-dev")
                        name.set("John Toshokan")
                        email.set("johntoshokan@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/john-t-dev/johnt_maven_computational_core.git")
                    developerConnection.set("scm:git:ssh://github.com/john-t-dev/johnt_maven_computational_core.git")
                    url.set("https://github.com/john-t-dev/johnt_maven_computational_core")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://central.sonatype.com/repository/maven-releases/")
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("OSSRH_GPG_SECRET_KEY")
    val signingPassword = System.getenv("OSSRH_GPG_SECRET_KEY_PASSWORD")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}
