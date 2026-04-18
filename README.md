# Mindbox - Intelligent Email App

A modern Android application for smart email management powered by AI personalization. Built with **Kotlin**, currently in development with mocked AI features.

##  Overview

Mindbox is an intelligent email client that leverages AI to personalize email experiences, optimize reading, and enhance productivity. The app is currently in the prototyping phase with AI features mocked for testing and UI/UX refinement.

##  Tech Stack

- **Kotlin** (100%) - Modern Android development language
- **Android SDK** - Official Android framework
- **Architecture Components** - ViewModel, LiveData, Room
- **Material Design 3** - Modern UI components
- **Jetpack** - Android development libraries
- **AI Features** (Mocked) - Ready for real AI integration

##  Getting Started

### Prerequisites
- Android Studio 2022.1+
- Kotlin 1.8+
- Android API 24+ (minimum)
- JDK 11+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Daniel25778/mindbox-mobile.git
cd mindbox-mobile
```

2. Open the project in Android Studio

3. Sync Gradle files:
```bash
./gradlew sync
```

### Running the App

1. Connect an Android device or start an emulator
2. Run the app:
```bash
./gradlew installDebug
```

Or click the **Run** button in Android Studio

### Building for Release

```bash
./gradlew assembleRelease
```

### Running Tests

```bash
./gradlew test
# UI Tests
./gradlew connectedAndroidTest
```
