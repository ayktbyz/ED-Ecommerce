pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "enuygun"

include(":app")
include(":data")
include(":domain")
include(":presentation")

include(":core:network")
project(":core:network").projectDir = file("core/network")

include(":core:database")
project(":core:database").projectDir = file("core/database")
