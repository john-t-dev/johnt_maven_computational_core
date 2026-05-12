import java.util.Base64

plugins {
    kotlin("jvm") version "2.0.0"
    id("com.vanniktech.maven.publish") version "0.28.0" // The API Bridge Plugin
    signing
}

repositories {
    mavenCentral()
}

group = "io.github.john-t-dev"
version = "1.0.8"

mavenPublishing {
    publishToMavenCentral("CENTRAL_PORTAL") // Explicitly targets the new API
    
    // Satisfies Maven's pedantic XML Manifest rules automatically
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

// Bypasses the fragile string parser
signing {
    val signingKeyBase64 = System.getenv("ORG_GRADLE_PROJECT_signingKey")
    val signingPassword = System.getenv("ORG_GRADLE_PROJECT_signingPassword")
    if (signingKeyBase64 != null) {
        val signingKey = String(Base64.getDecoder().decode(signingKeyBase64))
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}
