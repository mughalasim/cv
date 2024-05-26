# My Resume App

A Basic kotlin Android application that showcases the latest development methodologies. 
Download the sample app [here](https://github.com/mughalasim/cv/raw/master/app/release/My%20Resume.apk)

## Features
- Fetches and sets language resource files dynamically to support localisation without publication.
- Fetches dynamic CV over firebase realtime database.
- All view items are in Jetpack Compose fully state driven and update with flows over coroutines.
- Supports Dark and Light themes.
- Firebase analytics for screen and button tap tracking

## New Additions 18th May 2024
- Detekt, ktlint and Spotless for easier code formatting and readability
- Adding Retrofit2 to consume Firebase Database REST API
- Migrate from Groovy DSL to Kotlin DSL with version catalogue
- Added Kotlinx serialization with custom call adapters to process API response
- Using android gradle 8.4.0
- Using KoinViewModel injected into compose functions being lifecycle aware
- Updated lifecycle, compose, navigation and koin
- Added proguard rules for better obfuscation support
- Added bottom navigation and top bar with jetpack compose scaffold

## Release notes 
Get the release notes [here](https://github.com/mughalasim/cv/releases)


## Preview
<img 
    src="https://github.com/mughalasim/cv/blob/master/images/sc1.png" 
    width="180" height="400" alt="null"
/>

<img
    src="https://github.com/mughalasim/cv/blob/master/images/sc2.png"
    width="180" height="400" alt="null"
/>
