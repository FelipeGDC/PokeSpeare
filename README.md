
# PokeSpeare SDK and Sample implementation:

- SDK developed to get the information of an specific Pokemon and the sprite when passing the name as a parameter. The SDK also has a custom view for easier display of the Pokemon information.

- The project also comes with a sample App implementing the SDK with a search bar and implementing the custom view.

- SDK Architecture approach inspired by DDD and CLEAN concepts.

## How to use

To initialize and use the SDK use `PokemonSDK` with context:

```kotlin  
// Initialization  
val pokemonSdk: PokemonSdk = PokemonSDK(context)  
  
```  

Then you can access the information through the public methods, both methods should be called from a coroutine scope:

```kotlin  
viewModelScope.launch {
    pokemonSdk.getPokemonDescription("Pokemon name") //Returns PokemonDescriptionSdk  
    pokemonSdk.getPokemonSprite("Pokemon name") //Return PokemonSpriteSdk  
}
```  

The SDK is currently working with two classes: `PokemonDescriptionSdk` and `PokemonSpriteSdk`, both of which come with a field `State` to know the result of the request:
```kotlin  
data class PokemonDescriptionSdk(  
 var state: State<Any>?,  
 var description: String? = "",  
 var generation: String? = "",  
 var genera: String? = "",  
 var name: String? = "",  
 var pokedexNumber: Int? = 0  
)  
  
data class PokemonSpriteSdk(  
 var state: State<Any>?,   
var url: String = "",var type: String = "")  
```  

The custom component is called `PokemonInfoView`:
```xml
    <com.fgdc.pokespearesdk.presentation.component.PokemonInfoView
        android:id="@+id/pokemonInfoView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
```

And the way we can set the values is in the fragment/activity we are using it, through access to it's identifiers:

```kotlin  
       pokemonInfoView.name.text = "Pokemon name"
       pokemonInfoView.description.text = "Pokemon description"
       pokemonInfoView.pokedexNumber.text = "Pokemon Pokedex number"
       pokemonInfoView.sprite = ImageView
```  

## Libraries 🛠️

- [Flow](https://developer.android.com/kotlin/flow)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Coil](https://coil-kt.github.io/coil/)
- [Android Jetpack](https://developer.android.com/jetpack)
    - [Navigator](https://developer.android.com/guide/navigation/navigation-getting-started)
    - [View Binding](https://developer.android.com/topic/libraries/view-binding)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [Lottie](https://github.com/airbnb/lottie-android/)


## Structure 🎨

Currently the project is separated into two modules, the app module with the sample project and pokespeare-sdk with the SDK.

### SDK :
- __Data__: Contains the Repositories Implementations and the Data Sources.
    - __Source__: In which we have the source of the data we are going to work with, let it be  
      the API implementation and abstraction, and/or the database. In this case, we have the API  
      calls. It's all separated by features.
    - __Repository__: Repositories are responsible to coordinate data from the different Data  
      Sources. A sort of abstraction for the data sources in order to avoid working directly with  
      them. We make calls to them and we can ignore whether the data comes from the network or a  
      local database.
    - __Mappers__: Classes to map the objects in the data layer to the domain layer.
- __DI__: The dependency injector package, where the modules and components are created.
- __Domain__: Collection of entity objects and related business logic that is designed to represent  
  the enterprise business model.
    - __Models__: An abstraction of the objects that represent the logic of the project.
    - __Repository__: The abstraction of the repositories to be implemented in the Data package.
    - __UseCases__: The interactors that define be the business logic of the application.
    - __Mappers__: Classes to map the objects in the domain layer to the presentation layer.
- __Presentation__: There's no fragments or viewmodels in this case, here lies the SDK abstraction that it's used by the users.
    - __SDK__: The SDK class with the methods, and the models used in this layer.
    - __Component__: The PokemonInfoView component lies here.
- __Utils__: A variety of classes, extensions, and helpers to help and use across the application,  
  that not necessarily have anything to do with the logic of the same.

### App:
- __UI__: With an MVVM pattern, everything is separated as features, the screens and logic behind  
  them are found here.
- __DI__: The dependency injector package, where the modules and components are created.
- __Utils__: A variety of classes, extensions, and helpers to help and use across the application,  
  that not necessarily have anything to do with the logic of the same.

## Testing 🧰

#### (There should totally be more tests, but because of the type of project, I haven't tested every part of the application. I decided to test the basic functionalities in the SDK, repositories and usecases, since I felt it was representative enough. At least for the time being.)

- JUnit 4
- [Mockk](https://mockk.io/)
- [Kotest](https://github.com/kotest/kotest)


## Gradle Setup 🐘

This template is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the [Dependencies.kt](buildSrc/src/main/java/Dependencies.kt) file in the `buildSrc` folder. This provides convenient auto-completion when writing your gradle files.

## Static Analysis 🔍

This project is using [**ktlint**](https://github.com/pinterest/ktlint) with the [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) plugin to format your code. To reformat all the source code as well as the buildscript you can run the `ktlintFormat` gradle task.

This project is also using [**detekt**](https://github.com/detekt/detekt) to analyze the source code, with the configuration that is stored in the [detekt.yml](config/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task).

## CI ⚙️

There are currently the following workflows available:
- [Lint](.github/workflows/android-lint.yml) - Runs Android lint
- [Detekt](.github/workflows/detekt.yml) - Runs detekt.
- [ktlintCheck](.github/workflows/run-ktlint.yml) - Runs ktlint.
- [Unit Test](.github/workflows/unit-test.yml) - Run unit tests.


# Credits 🤖

[Kotlin Android Template](https://github.com/cortinico/kotlin-android-template): "A simple Github template that lets you create an **Android/Kotlin** project and be up and running in a **few seconds**."  
[Kotlin Pokedex](https://github.com/mrcsxsiq/Kotlin-Pokedex): For inspiration regarding designs and idea of how to map type/color.