# Android MVVM Project

This project is a modular Android application developed using the MVVM architecture. It provides a sample shopping experience with a clean separation of concerns and modern Android development practices.

## Architecture

The application is structured into the following modules:

- **app**: Entry point and UI layer.
- **data**: Handles data sources (local/remote), repository implementations, and data models.
- **domain**: Contains business logic, use-cases, and interfaces.
- **presentation**: Includes screens, ViewModels, and connects UI to domain logic.

### MVVM Pattern

- **Model**: Data layer, including repositories and data sources.
- **View**: UI components built with Jetpack Compose.
- **ViewModel**: Manages UI-related data, exposes state via Kotlin Flow, and handles user interactions.

### Reactive Data Flow

- **Kotlin Coroutines**: Used for asynchronous programming throughout the app, ensuring smooth UI and efficient background processing.
- **Kotlin Flow**: ViewModels expose UI state and events using Flow, allowing screens to reactively observe data changes.
- **State Management**: UI state is managed using Compose’s `State` and ViewModel’s `StateFlow` or `SharedFlow`.

### Dependency Injection

- **Hilt**: Used for dependency injection, providing ViewModels, repositories, and use-cases with required dependencies.

## Technologies Used

- **Kotlin**
- **Jetpack Compose**
- **Hilt**
- **Retrofit & OkHttp**
- **Room** (if local database is used)
- **AndroidX** (Lifecycle, Navigation, Core KTX, Material3)
- **Kotlin Coroutines & Flow**
- **JUnit, MockK, Turbine** (for unit and flow testing)

## Screens

- **Home**: Displays a list of products fetched from the repository.
- **Categories**: Shows product categories.
- **Favorites**: Displays user’s favorite products.
- **Cart**: Shows cart items and total price.
- **Product Detail**: Displays detailed information about a selected product.

Navigation is managed via [ENNavigationHost](app/src/main/java/com/aytbyz/enuygun/ui/navbar/ENNavigationHost.kt), using Jetpack Navigation Compose.

## Data Flow Example

1. **Repository** fetches data from remote API using Retrofit (and optionally caches with Room).
2. **UseCase** in the domain module processes business logic.
3. **ViewModel** calls UseCase, exposes results via `StateFlow` or `SharedFlow`.
4. **Composable Screen** observes ViewModel’s state and updates UI reactively.

## Unit Testing

- **JUnit**: Used for writing unit tests.
- **MockK**: Mocks dependencies such as repositories and data sources.
- **Turbine**: Tests Kotlin Flow emissions.
- **Coroutine Test**: Uses `runBlockingTest` or `TestDispatcher` for coroutine-based code.

Example test files:

- [GetProductsUseCaseTest](domain/src/test/java/com/aytbyz/enuygun/domain/GetProductsUseCaseTest.kt)
- [AddToCartUseCaseTest](domain/src/test/java/com/aytbyz/enuygun/domain/AddToCartUseCaseTest.kt)
- [UpdateCartItemQuantityUseCaseTest](domain/src/test/java/com/aytbyz/enuygun/domain/UpdateCartItemQuantityUseCaseTest.kt)
- [ExampleUnitTest](app/src/test/java/com/aytbyz/enuygun/ExampleUnitTest.kt)

Run all tests with:
```
./gradlew test
```

## Additional Notes

- The project is fully modular; new features can be added in the relevant module.
- Proguard rules are defined per module.
- Minimum SDK: 24, Target SDK: 35.
- For dependencies and configurations, check [build.gradle.kts](build.gradle.kts) and module-specific gradle files.

## Setup

1. Clone the project:  
   `git clone <repo-url>`
2. Open with Android Studio.
3. Download required SDKs and dependencies.
4. Run the app using the `Run` button or via terminal:  
   `./gradlew assembleDebug`

---

For more details, please review the code and
