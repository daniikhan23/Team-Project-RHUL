const form = document.getElementById("login-form");

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Username & Password
    if (username === "admin" && password === "password") {
        alert("Login successful!");
    } else {
        alert("Incorrect username or password. Please try again.");
    }
});