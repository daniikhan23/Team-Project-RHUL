// Get references to form elements
const form = document.querySelector("form");
const usernameInput = document.querySelector("#username");
const emailInput = document.querySelector("#email");
const passwordInput = document.querySelector("#password");
const confirmPasswordInput = document.querySelector("#confirm-password");

// Submit form handler
form.addEventListener("submit", function (event) {
    event.preventDefault();

    // Get input values
    const username = usernameInput.value;
    const email = emailInput.value;
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // Validate input values
    if (!username || !email || !password || !confirmPassword) {
        alert("Please fill out all fields");
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match. Please try again.");
        return;
    }

    // Submitting the form (in a real scenario, this would send data to a server)
    alert('Form submitted successfully!\nUsername: ${ username }\nEmail: ${ email }\nPassword: ${ password }');

    // Reset form fields
    form.reset();
});
