🎬 TheMovieApp

TheMovieApp is an Android application that displays movie information such as Now Playing movies, movie details, and movies by genre.
This project was built as a technical test and demonstrates clean architecture, modern Android development, and scalable project structure.

📱 Features

- Now Playing Movies
- Movie Detail Screen
- Browse Movies by Genre
- Clean UI with Jetpack Compose
- Smooth navigation using Navigation Component
- Pagination support using Paging 3

🧱 Architecture

This project follows MVVM (Model–View–ViewModel) architecture combined with modularization to improve scalability, maintainability, and testability.

Why MVVM?

- Clear separation of concerns
- Lifecycle-aware state handling
- Easier to test and maintain

Why Modularization?

- Faster build time
- Better code ownership
- Easier feature scalability

Module Structure
core
│── network
│── ui
│── helper
│
home
│── data
│── domain
│── presentation
│
detail
│── presentation
│
genre
│── presentation
│
app
│── navigation
│── main ui

🛠 Tech Stack & Libraries
Core

Kotlin
- Jetpack Compose – Modern declarative UI
- MVVM Architecture
- Clean Architecture principles

Jetpack
- ViewModel
- Navigation Component (Compose)
- Paging 3
- Lifecycle

Dependency Injection
- Dagger Hilt

Networking
- Retrofit
- OkHttp
- Gson Converter

Async & State
- Kotlin Coroutines
- StateFlow / Flow
