# General information of the project:

- **Language**: Kotlin
- **IDE**: Android Studio
- **Design Pattern**: MVVM (Model - View - ViewModel) with LiveData
- **DI**: Dagger Hilt
- **Concurrency**:  RxJava
- **Unit Test Framework**: Spec
- **UI Test Framework**: Espresso

The project follows a feature structure. There is a feature folder where all additional features would be placed, in this example, I provide the both screens under the same feature for simplicity.

Each feature manages the three layers of clean architecture (Data - Domain - UI).

As DI framework I used Dagger Hilt, its configuration is done in the subfolder DI

The management of the response is done with `RxJava`, using `Moshi` library to adapt the response.

I decided to create few models for the Network Response `TokenDto`, `TokenResponse` , `BalanceReponse`and `USDETHResponse` and the one used in the domain is `Token` and the primitieves`String` and  `BigDecimal`, the transformation is done in the mappers `BalanceMapper`, `TokenMapper` and  `USDETHMapper`.

The project uses the `UseCases` pattern in order to help identify the different use cases the app could have, in this case `GetBalanceUseCase`, `GetConversionUseCase`, `GetBalanceWithConversionUseCase` and `GetTokenListUseCase`

For retrieving the balance and handling the result of 2 endpoints at the same time I use the Rx method `Zip`, combining both observables and returning a String

I implemented each screen in a Fragment, In order to handle the navigation between the two fragments I decided to use the Android Navigation Component and adding an animation between the screens, you can find in `nav_graph.xml` the navigation graph between the fragments

I introduced a custom view to handle the states of loading, or error in both screens, this is displayed in case there are some error in the network calls.

The `Cache` is provided for the retrieve balance case in the repostory. I introduced a basic example of it where it is saved the network response based on the address provided, but a more complex implementation based on timestamps should be introduced (as a balance would be out of date after some time).

For the second screen I am using an AutocompleteTextView that loads all the addressed provided by the endpoint, you need to select one in order to display the balance of that address. (I reused the same usecase used for the previous screen).

For simplicity the adress provided is hardcoded in the ViewState, but other encrypted solution should be provided in a production environment.

For `UnitTest` I use the spec framework, as I think it improves the readability of the different use cases and as well the scalability for more complex input data structures.
I provided few exaples for each layer, full testing of all layers should be provided in a production environment
Please note for runing it is needed the Spec framework plugin to be installed.
The  `UnitTest` test the 3 different layers of the feature

I provide a `UITest` using espresso to test the displayed of the list in the fragment. This is done mocking the response of the view states. Please use an emulator with API 28 to run it.

Please note, I didn't add end to end test, that should be added to test the navigation between the different screens.

# Scrum:
In order to manage this project I followed the principles of _Scrum Methodology_ identifying the following tasks and estimating them as follows:

- [x] **TaskA**: Setup the project -> Story Points: **2**
- [x] **TaskB**: Establish the MVVM architecture with Data-Domain-UI -> Story Points: **5**
- [x]  **TaskC**: Implement Dagger-> Story Points: **5**
- [x] **TaskD**: Implement retrieving the data for balance screen and combine the response-> Story Points: **5**
- [x] **TaskE**: UI implementation of the Balance -> Story Points: **1**
- [x]  **TaskF**: Implement retrieving the data for Tokens-> Story Points: **3**
- [x] **TaskG**: UI implementation of the Tokens -> Story Points: **2**
- [x] **TaskH**: UI implementation of cache -> Story Points: **2**
- [x] **TaskI**: UITest of the  list view -> Story Points: **3**

Other tasks identified not implemented:
- [ ] **TaskJ**: Improve designs
- [ ] **TaskK**: Implement end to end tests
- [ ] **TaskL**: Implement UI test for ERC20 token screen
- [ ] **TasM**: Migrate gradle to KTS
- [ ] **TaskN**: Define more custom styles for the app

# Questions:
### How long did you spend on the exercise?
Around 6 to 8 hours.
###  What would you improve if you had more time?
I would provide more coverage for testing
I would improve the designs and the cache.
I would formmat diferently the balance for the 0.00 case in the second screen
###  What would you like to highlight in the code?
I have resumed in the intro of the Readme the main points of the code, but basically I wanted to show a clean code architecture where other features can be built on top.
###  If you had to store the private key associated with an ethereum account on an android device, how would you make that storage secure?
I would used `EncrypedSharedPreference` from the Security library
