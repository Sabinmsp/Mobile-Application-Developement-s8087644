# Assessment Application

An Android application for student authentication and entity management, built with Kotlin and modern Android architecture components.

## 📱 Overview

This is a native Android application that provides a student login system with dashboard functionality. Students can authenticate using their credentials and view assigned entities/data in a modern, user-friendly interface.

## ✨ Features

- **User Authentication**: Secure login with first name and student ID
- **Dashboard**: View personalized entity list with RecyclerView
- **Detail View**: Tap any entity to see detailed information
- **Modern UI**: Material Design components with CardViews and clean layouts
- **Error Handling**: Comprehensive network error handling and user feedback
- **API Integration**: RESTful API communication using Retrofit

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: XML layouts with Material Design components
- **Architecture**: MVVM pattern
- **Networking**: Retrofit + OkHttp
- **Data Parsing**: Gson
- **UI Components**: RecyclerView, CardView, Material Design
- **Minimum SDK**: 28 (Android 9.0)
- **Target SDK**: 34 (Android 14)

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio**: Electric Eel (2022.1.1) or later
- **JDK**: Version 8 or higher
- **Android SDK**: API level 28 or higher
- **Git**: For cloning the repository
- **Internet Connection**: Required for API calls

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd MyAssssmentApplication
```

### 2. Open in Android Studio
1. Open Android Studio
2. Select "Open an existing project"
3. Navigate to the cloned directory and select it
4. Wait for Gradle sync to complete

### 3. Sync Project
```bash
# Android Studio will automatically sync, but you can manually trigger:
./gradlew clean build
```

## 🔧 Building the Application

### Using Android Studio (Recommended)
1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Click **Build > Make Project** or press `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)
4. Ensure no build errors occur

### Using Command Line
```bash
# Clean and build the project
./gradlew clean build

# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing configuration)
./gradlew assembleRelease
```

## 📱 Running the Application

### On Physical Device
1. Enable **Developer Options** on your Android device
2. Enable **USB Debugging**
3. Connect device via USB
4. In Android Studio, select your device from the device dropdown
5. Click **Run** button or press `Shift+F10`

### On Emulator
1. Open **AVD Manager** in Android Studio
2. Create a new Virtual Device (API 28 or higher recommended)
3. Start the emulator
4. Select the emulator from device dropdown
5. Click **Run** button

### Command Line Installation
```bash
# Install debug APK on connected device
./gradlew installDebug

# Install and run
./gradlew installDebug && adb shell am start -n com.example.myassssmentapplication/.MainActivity
```

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/example/myassssmentapplication/
│   │   ├── MainActivity.kt              # Login screen
│   │   ├── DashboardActivity.kt         # Main dashboard with entity list
│   │   ├── DetailsActivity.kt           # Entity detail view
│   │   ├── EntityRecyclerAdapter.kt     # RecyclerView adapter
│   │   ├── ApiService.kt                # API endpoint definitions
│   │   ├── RetrofitClient.kt            # Retrofit configuration
│   │   ├── Entity.kt                    # Entity data model
│   │   ├── LoginRequest.kt              # Login request model
│   │   ├── LoginResponse.kt             # Login response model
│   │   └── DashboardResponse.kt         # Dashboard response model
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        # Login screen layout
│   │   │   ├── activity_dashboard.xml   # Dashboard layout
│   │   │   ├── activity_details.xml     # Details screen layout
│   │   │   └── item_entity.xml          # RecyclerView item layout
│   │   ├── values/
│   │   │   ├── colors.xml               # App color definitions
│   │   │   ├── strings.xml              # String resources
│   │   │   └── themes.xml               # App themes
│   │   └── drawable/                    # Button backgrounds and drawables
│   └── AndroidManifest.xml              # App configuration
```

## 🌐 API Configuration

The app connects to: `https://nit3213api.onrender.com/`

### Endpoints Used:
- **POST /sydney/auth** - User authentication
- **GET /dashboard/{keypass}** - Fetch user dashboard data

### Sample Login Credentials:
- **First Name**: Your actual first name
- **Student ID**: Format `s12345678` (s + your student number)

## 🎮 How to Use

1. **Launch the App**: Open the application on your device
2. **Login**: 
   - Enter your first name
   - Enter your student ID in format `s12345678`
   - Tap "LOGIN"
3. **Dashboard**: View your assigned entities in a scrollable list
4. **Details**: Tap any entity card to view detailed information
5. **Logout**: Use the logout button to return to login screen

## 🔍 Troubleshooting

### Common Issues

**Build Fails:**
```bash
# Clean and rebuild
./gradlew clean build
```

**Gradle Sync Issues:**
- Ensure stable internet connection
- Click "Sync Now" in Android Studio
- Check `gradle/wrapper/gradle-wrapper.properties` for correct Gradle version

**API Connection Issues:**
- Verify internet connection
- Check if API server is accessible: `https://nit3213api.onrender.com/`
- Ensure correct login credentials format

**App Crashes:**
- Check logcat in Android Studio for error details
- Verify minimum SDK version (API 28+)
- Ensure all dependencies are properly synced

### Debug Mode
```bash
# Run with debug logging
adb logcat | grep "MyAssssmentApplication"
```

## 🧪 Testing

### Running Unit Tests
```bash
./gradlew test
```

### Running Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## 📄 Dependencies

Key dependencies used in this project:

- **Retrofit 2.9.0** - HTTP client for API calls
- **Gson** - JSON parsing
- **OkHttp 4.11.0** - HTTP logging interceptor
- **Material Components** - UI components
- **RecyclerView 1.3.2** - Efficient list display
- **CardView** - Material design cards

## 📝 Version Information

- **Version**: 1.0
- **Compile SDK**: 34
- **Min SDK**: 28
- **Target SDK**: 34
- **Kotlin**: 1.9.24
- **Gradle**: 8.7.0

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

If you encounter any issues:
1. Check the troubleshooting section above
2. Review Android Studio's logcat for error details
3. Ensure all prerequisites are met
4. Verify API connectivity

---

**Note**: This application requires an active internet connection to authenticate users and fetch dashboard data from the remote API. 