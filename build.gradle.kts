plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.2.0"
    id("org.jetbrains.grammarkit") version "2022.3.2.2" // Thêm plugin Grammar-Kit
}

group = "me.hql"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2023.2.5")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "232"
            untilBuild = provider { null }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/gen")
    }
}

tasks {
    named("instrumentCode") {
        enabled = false
    }

    // 1. Cấu hình gen Parser từ LinhScript.bnf
    generateParser {
        sourceFile.set(file("src/main/grammars/LinhScript.bnf"))
        targetRootOutputDir.set(file("src/main/gen"))
        pathToParser.set("/me/hql/linhscript/parser/LinhScriptParser.java")
        pathToPsiRoot.set("/me/hql/linhscript/psi")
        purgeOldFiles.set(true)
    }

    // 2. Cấu hình gen Lexer từ LinhScript.flex
    generateLexer {
        sourceFile.set(file("src/main/grammars/LinhScript.flex"))
        // Khớp với vị trí LinhScriptLexer trong ảnh của bạn (nằm thẳng trong package me.hql.linhscript)
        targetOutputDir.set(file("src/main/gen/me/hql/linhscript"))
        purgeOldFiles.set(true)
    }

    // Đảm bảo luôn gen code xong mới biên dịch Java
    compileJava {
        dependsOn(generateParser, generateLexer)
    }
}