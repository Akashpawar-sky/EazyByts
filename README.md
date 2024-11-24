

#Real-Time Chat Application
📚 Overview
This is a Real-Time Chat Application built as part of the #EazyBytsInternship program. The application allows users to communicate in real-time through a clean, responsive chat interface. It implements user authentication and stores chat history in a PostgreSQL database.

✨ Features
User Authentication: Users must log in to send messages. Without logging in, they can only view the chat history.
Real-Time Messaging: Users can send messages and see them updated instantly in the chat box.
Message History: All messages, along with the sender’s name, date, and time, are stored in a PostgreSQL database for persistence.
Secure API: All endpoints are protected using secure authentication.
Responsive UI: The chat interface is optimized for both desktop and mobile views.
🛠️ Tech Stack
Frontend:
HTML, CSS, JavaScript: For creating a clean, responsive user interface.
Backend:
Spring Boot: For building REST APIs and managing business logic.
Database:
PostgreSQL: For storing user data and chat history efficiently.
🔑 Prerequisites
Ensure you have the following installed on your system:

Java 17+
PostgreSQL 13+
Maven 3.8+

⚙️ Installation and Setup
Follow these steps to set up the application on your local machine:

Backend Setup:
Clone the repository:

git clone https://github.com/Akashpawar-sky/chat-app.git

Configure PostgreSQL:
Create a PostgreSQL database named chat_app.
Update the database credentials in the application.properties file:
#properties

spring.datasource.url=jdbc:postgresql://localhost:5432/chat_app
spring.datasource.username=your_username
spring.datasource.password=your_password
Build and run the backend:

mvn clean install
mvn spring-boot:run
Frontend Setup:
Navigate to the frontend directory (if applicable) or host the static files.
Make sure the app.js file contains the correct API URLs for the backend:

const BASE_URL = "http://localhost:8080";
Access the Application:
Open your browser and navigate to http://localhost:8080 for the chat interface.
📂 Project Structure

chat-app/
├── src/
│   ├── main/
│   │   ├── java/ (Spring Boot backend code)
│   │   ├── resources/
│   │   │   ├── static/ (Frontend files)
│   │   │   ├── templates/ (HTML templates)
│   │   │   └── application.properties (Database configuration)
│   ├── test/
├── pom.xml (Maven dependencies)
├── README.md
🚀 Features Walkthrough
User Authentication
Login: Users must log in using their credentials to access full functionality.
Register: New users can create an account.
Secure Messaging: Only authenticated users can send messages.
Chat Functionality
View Message History:
Messages are displayed with timestamps.
Send Messages:
Authenticated users can send messages, which are instantly displayed in the chat box.
Database Storage:
Each message is stored in the messages table with the following schema:

CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    sender VARCHAR(255),
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
📈 Lessons Learned
Building REST APIs: Gained hands-on experience with Spring Boot.
Database Management: Learned to design and query PostgreSQL tables for chat history.
Asynchronous Programming: Used JavaScript's async/await to handle real-time API calls.
Responsive Design: Built a UI that works seamlessly across devices.
🛡️ Security Measures
Authentication: Token-based authentication for secure communication.
Data Validation: Validated inputs on both frontend and backend to prevent malicious data.
📽️ Video Demo
Check out the video demonstration to see the application in action!
https://www.linkedin.com/posts/akash-pawar-sky_eazybyts-eazybytsteam-eazybytsinternship-activity-7262359011998466048-Ne8E?utm_source=share&utm_medium=member_desktop

💡 Credits
This project was completed as part of the EazyByts Internship Program. A huge thanks to the EazyByts team for the support and guidance!
