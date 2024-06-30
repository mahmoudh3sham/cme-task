# Albums App Task @CME
An android app that displays a list of albums and details about them.

## Demo Video
[![Watch the Demo](https://img.youtube.com/vi/aj27jTVTR2A/0.jpg)](https://www.youtube.com/watch?v=aj27jTVTR2A)

## Modularization
Project follows the (modularization by layer) approach, it contains three main modules:
* App Module (presentation).
* Data Module.
* Domain Module.

## Architecture
* Project is built using **Clean Architecture**. It's divided into three main layers/modules (Presentation, Domain, Data).
and it uses **MVVM design architecture pattern** to handle the Presentation layer.

## Android lifecycle Components
Project uses the following Android Architecture Components:
* ViewModel
* StateFlow

## Network
Project uses the following tools to handle remote fetching
* Retrofit (with logging-interceptor to log network response).
* Coroutines with Flow to handle data fetching asynchronously.

## Local Database
Project uses the following tool to handle local database
* Realm

## Dependency Injection
project uses **DaggerHilt** for dependency injection.

## Design Tools
* project uses **Jetpack Compose** for screens design along with Material3.
* project uses **Coil & Glide (Experimental)** for asynchronous and lazy image fetching.

## Navigation
* project uses **Navigation Compose** for handling navigation between screens. 

## Testing
* project uses **JUnit** for unit testing.

## Using the App
Launching the app for the first time without internet connection will lead to raise a connection error along with a **retry** button to retry if the connection is back,
If user already has an internet connection, app will fetch 100 albums from Apple RSS API (https://rss.applemarketingtools.com), data is stored in offline database using
Realm database so that if user opens the app without internet connection, app will retrieve albums from that offline database otherwise it will retrieve the 
data from the remote api feed, data will be updated when user swipe to refresh or opens the app again with internet connection.

User can browse albums list in the first screen, when clicking on any album app will navigate to the clicked album's details screen offering the user more data about 
that album in a friendly experience.
