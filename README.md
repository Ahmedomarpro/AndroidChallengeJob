# Android Challenge Job
A mobile app for displaying real time Premier League Using APIs.(football-data.org)

## Challenge description Job (Orcas)
Access to the top football competitions is and will be free forever as this was the initial purpose to setup the project.

## Additional Info About API Documentation
* [football](https://www.football-data.org/documentation/api)
 
 football-data.org provides football data and statistics (live scores, fixtures, tables, squads, lineups/subs, etc.) in a machine-readable way.

Our free (RESTful) API in JSON representation is used by thousands of developers to power websites and mobile apps with football data.

Access to the top football competitions is and will be free forever as this was the initial purpose to setup the project.
However if you need more competitions (or in-depth data), we have several paid plans available to serve your needs.

The exhaustive documentation, code samples and libraries will help you get up and running quickly. 

## Screenshot

 ![Screenshot_Acivity](https://user-images.githubusercontent.com/22521791/69773224-e8683280-119a-11ea-8265-84b25a39ff78.png)


## Specifications
- Caching for showing the last places offline.
- portrait and landscape.
- using MVC-MVVM
- Using Usecases (part of clean architecture)
- Partly include comments.
- Reactive code

## Languages, libraries and tools used

 * [androidX libraries](https://developer.android.com/jetpack/androidx)
 * [Android LifeCycle](https://developer.android.com/topic/libraries/architecture)
 * [Glide](https://github.com/bumptech/glide)
 * [Room](https://developer.android.com/jetpack/androidx/releases/room)
 * [Retrofit2](https://github.com/square/retrofit)
 * [json](https://github.com/nlohmann/json)

## Requirements
- min SDK 19

## Known Issues 
 - Sometimes images won't load as the quota on Football Api is very limited.

## Implementation

* In this project I'm using [MVC Pattern] [MVVM Pattern](https://developer.android.com/jetpack/docs/guide)
as an application architecture adopted with usage of UseCases with these design patterns in mind:-
- Repository Pattern
- Singleton
- Observables
- Adapters

 * Using Retrofit library to handle the APIs stuff.
* Using Room for caching places
* [Creating Custom ImagesLoader](https://github.com/Ahmed3Elshaer/GeoSquar/tree/master/app/src/main/java/com/ahmed3elshaer/geosquar/common/loader) with Glide to handle getting the Venues Photos from Football Api and the extract a url and then load it into ImageView with no pain of writting logic inside the RecyclerView Adapter 
* Separation of concerns : The most important principle used in this project to avoid many lifecycle-related problems.
<img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png"></a>
* Each component depends only on the component one level below it. For example, activities and fragments depend only on a view model. The repository is the only class that depends on multiple other classes; in this example, the repository depends on a persistent data model and a remote backend data source.
This design creates a consistent and pleasant user experience. Regardless of whether the user comes back to the app several minutes after they've last closed it or several days later, they instantly see a user's information that the app persists locally. If this data is stale, the app's repository module starts updating the data in the background.
* Using to best of managing ViewState with less complex tools , using Data Classes and LiveData we created a solid source that we can expose to view to show what the app can do to the user without worring about the side effects
 
## Immutability
Data immutability is embraced to help keeping the logic simple. Immutability means that we do not need to manage data being mutated in other methods, in other threads, etc; because we are sure the data cannot change. Data immutability is implemented with Java class

## ViewModel LifeCycle
The ViewModel should outlive the View on configuration changes. For instance, on rotation, the Activity gets destroyed and recreated but your ViewModel should not be affected by this. If the ViewModel was to be recreated as well, all the ongoing tasks and cached latest ViewState would be lost.
We use the Architecture Components library to instantiate our ViewModel in order to easily have its lifecycle correctly managed.


## License
MIT License
```
Copyright (c) 2019 Ahmed Omar
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE...

```

  



 




