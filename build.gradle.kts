plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.2.0"
}

group = "me.hql"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        // Chỉ cần tải mỗi cái khung IntelliJ này là đủ, bản 2.2.0 sẽ tự lo phần còn lại!
        intellijIdeaCommunity("2023.2.5")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "232"
            untilBuild = "242.*"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.named("instrumentCode") {
    enabled = false
}
sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/gen")
    }
}