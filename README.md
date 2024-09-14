# VG Trips Task
VG Trips is an Android app built entirely with Kotlin and Jetpack Compose. Following Android design and development best practices.

The app simply implements a use flow of booking and viewing trips as well as accompanying itineraries.
APK is available [here](https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/vg-trips.apk?alt=media&token=4723fc2d-6fd0-40a4-a3d4-b7105e1a4846)

## Features
Users can view their booked trips and book new trips. A destination can be selected from a list of cities; dates are chosen by picking start and end days on a calendar. The details of trip are shown as soon as booking is complete. Further itineraries can be added.

## Screenshots
![Screenshot_20240914-012045_VGTask](https://github.com/user-attachments/assets/f0d63e75-005b-468b-b560-8f6440ca85c7)

## Development Environment
The app uses the Gradle build system and can be imported directly into Android Studio.
No additional configuration required.

## Architecture
The app follows the [official architecture guidance](https://developer.android.com/topic/architecture). For simplicity , it is  implementd as a single-module app.
The app architecture has two layers: a data layer, and a UI layer.

#### Create a new trip - A simple flow
The creation of a new trip is done over three screens. First, `DestinationScreen.kt` screen to pick a destination; 2nd, `DateScreen.kt` to pick a date range, and finally a 3rd, `FinalizeScreen.kt` to enter details such as trip name, description and trip style.
These three screens rely on one ViewModel (one of three viewmodels in the app) `TripCreationViewModel`. The user is directed through the screens and the data is sent to the viewmodel from the Composables via call backs that the viewmodel exposes. Example is the `setDestination()` method which is used in `DestinationScreen.kt` to the store the destination selected by the user.
Upon completion of data collection, the data is sent by the viewmodel to the `UserRepo` in the data layer. The repo then makes a network request to update the user's trips. The repo does this with the remote service based on Retrofit on which it depends.

When this is complete, a navigation to the `TripScreen.kt` is triggered and id of the newly created trip is passed along. The trip screen then fetches the new trip with the id and displays it.

### Data layer
The data layer is the source of truth for all data in the app. Data from here is exposed to the UI layer via Repos; `UserRepo` and `CitiesRepo`. These repos expose methods for fetching and sending data.

The repositories depend on data sources for local and remote data.
- `UserRemoteService` and `CitiesRemoteService` and both based on Retrofit and are used by the repos for manipulating remote data.
- `VGTaskDataStore` is based on the Android Preference DataStore library. This stores data locally. It is used simply for storing the user's id.


### Domain layer
This app has no domain layer. Although two files viz; `GetFormattedDate.kt` and `GenerateCalendarItems.kt` which are used to get formatted dates and generate data for the Calendar respectively; are utility files that could be grouped in a directory to form the domain layer.

### UI Layer
The UI layer comprises:
- UI elements built using Jetpack Compose
- Android ViewModels

The viewmodels hold and provide data for the ui state. As well as perform some business logic.
Pieces of data needed by each screen are provided in immutable data classes. The composable functions transform this data class instances to UIstate visible to the user.

User interactions are passed in the opposite direction from Composable to Viewmodel via callback/lambdas provided/exposed by the viewmodel

## Screens
The app has 3 main screens and 3 sub screens
- Home Screen: The landing screen. Booked trips can be seen here. Users can go from here to trip creation screens.
- Trip Creation screen: This has the 3 sub screens
  - Destination Screen: To choose a trip destination
  - Date Screen: To pick a start and end date via a calendar display
  - Finalize Screen: To enter trip name, style and description and finalize the trip creation process
- Trip Screen: This screen shows details of a single trip. Users can also add additional itinerary to the trip here

## Dependencies
- Retrofit: For making network calls
- Preferences DataStore: For storing key-value pairs based data locally
- Coil: For loading images over the network seamlessly
- Hilt: For dependency injection


## API
The api used is the crud feature provided by https://beeceptor.com/ . A single endpoint `/users` subserves all operations. Trips are saved as an array in a user object. This `trips` array is one of only 2 fields in the user object. The other being the id.
Trip updates are made by fetching the user, adding or updating trips in the array, and then resending the whole object as a patch back to the api.

The other api calls outside this include:
- To retrieve a json of cities in an array. This was prepared for this project and stored in a cloud bucket.
  Ofcourse this could have been done with Beeceptor. But given the endpoints expire with the free tier and getting new endpoints would entail repopulating it with the cities, a bucket storage approach was preferred.
- Api calls are also made to download images diaplayed across various points in the app.

## Challenges and Possible Improvements
This project was done under very difficult circumstances due to some personal challenges (Thankfully, those have been resolved.) Many improvements could be done in the project if I had more time.
  These include:
- Testing: Despite using Hilt for dependency injection, the full benefits of Hilt were not used in the absence of any test.
- Better code quality: Especially in the UI with the Composables. While the final appearance of the UI on screen is good, the code can be better written and organized for maintainability.
- Screen size responsiveness: The app was made mainly for regular android phones in portrait orientation. With more time, the app can be made to look good in all screens accross the numerous offerings of the vast Android ecosystem. 
