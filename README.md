# NewsData App

This is a mobile application that fetches and displays news articles from the [NewsData.io API](https://newsdata.io/documentation/#about-newdata-api). Built using **Kotlin** and **Jetpack Compose**, it demonstrates a simple architecture with simulated authentication, pagination, and data persistence. The project uses several modern libraries, making it a great example of how to build Android applications with clean, modular code.

## Features

- **Simulated Authentication**: 
  - Uses hardcoded credentials (`username: elonga@elonga.com`, `password: ElongaTheBest`) to simulate authentication.
  - A hardcoded token for NewsData.io API is used for API requests.
  - Once logged in, the token is stored locally so that the user remains logged in until they log out.

- **News Pagination**:
  - Loads 10 news articles initially and fetches an additional 10 when the user scrolls to the end of the list.

- **News Details**:
  - Displays detailed information, including the image (if available), title, author, language, link, and description.
  - Users can open the article link in their default browser.
  - The article URL can also be shared via the Android share function.

## Technologies and Libraries

This project is built using **Kotlin** and **Jetpack Compose**. The following libraries are used:

- **Navigation Compose**: For managing screen navigation.
- **Koin**: For dependency injection.
- **Retrofit**: For making network requests to NewsData.io API.
- **Datastore Preferences**: For persisting the authentication token locally.
- **Coil Compose**: For loading images from URLs.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git

2.	Open the project in Android Studio.
3.	Build and run the project on an Android device or emulator with an internet connection.

## Usage

	1.	Launch the app.
	2.	Log in using the credentials:
	    •	Username: elonga@elonga.com
    	•	Password: ElongaTheBest
	3.	Once logged in, you’ll see the home screen displaying the latest news. Scroll to the end of the list to load more news.
	4.	Click on any news item to view details, where you can:
    	•	Open the article in your default browser.
	    •	Share the article link using Android’s share functionality.

## Project Structure

•	Authentication: Simulates login using hardcoded credentials and token.   
•	Networking: Retrofit is used to fetch data from the NewsData.io API.   
•	Data Persistence: Datastore Preferences is used to store the token for automatic re-login.   
•	Pagination: Loads news articles in batches of 10 as the user scrolls.   
•	Image Loading: Coil is used for loading and displaying images from URLs.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for details.