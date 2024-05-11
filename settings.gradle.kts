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

rootProject.name = "VKPokemonApp"
include(":app")
include(":feature:list:api")
include(":core:network")
include(":feature:list:domain")
include(":feature:list:data")
include(":feature:list:presentation")
include(":feature:detail:domain")
include(":feature:detail:api")
include(":feature:detail:data")
include(":feature:detail:presentation")
include(":core:common")
include(":core:database")
include(":core:foundation")
