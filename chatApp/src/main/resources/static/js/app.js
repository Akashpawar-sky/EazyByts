// Function to register a user
async function registerUser() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/auth/register", {  // Corrected path to match your backend
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
    });
    if (response.ok) {
        alert("Registration successful!");
        window.location.href = "/login";  // Update this path to the correct login page URL
    } else {
        alert("Registration failed.");
    }
}

// Function to login a user
async function loginUser() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/auth/login", {  // Corrected path to match your backend
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
    });
    if (response.ok) {
        const token = await response.text();
        localStorage.setItem("token", token);
        window.location.href = "/";  // Update this path to redirect to the home page or dashboard
    } else {
        alert("Login failed.");
    }
}
// Function to send a message
async function sendMessage() {
    const message = document.getElementById("message").value;
    const token = localStorage.getItem("token");

    if (!token) {
        alert("You need to log in to send messages.");
        return;
    }

    const response = await fetch("http://localhost:8080/chat/send", {  // Update endpoint to match backend
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
        },
        body: JSON.stringify({ content: message }),  // Send message as an object with 'content'
    });

    if (response.ok) {
        loadMessages();  // Reload messages after sending a new one
        document.getElementById("message").value = "";  // Clear the message input field
    } else {
        alert("Failed to send message.");
    }
}

// Function to load all messages
// Function to load all messages
// Function to load all messages
async function loadMessages() {
    try {
        const response = await fetch("http://localhost:8080/chat/messages");  // Ensure this endpoint matches your backend
        const messages = await response.json();

        if (!Array.isArray(messages)) {
            console.error("Expected an array of messages, but received:", messages);
            return;
        }

        const chatBox = document.getElementById("chat-box");
        chatBox.innerHTML = messages
            .map((msg) => {
                // Check if message structure is valid
                if (!msg.content || !msg.sender || !msg.sender.username) {
                    console.error("Invalid message structure:", msg);
                    return '';
                }

                const username = msg.sender.username;  // Use sender's username
                const content = msg.content;  // Content is just a string now
                return `<p><strong>${username}:</strong> ${content}</p>`;
            })
            .join("");
    } catch (error) {
        console.error("Error loading messages:", error);
        alert("Failed to load messages.");
    }
}

// Load messages when the page is loaded
document.addEventListener("DOMContentLoaded", loadMessages);

