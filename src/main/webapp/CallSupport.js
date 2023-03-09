// JavaScript to show/hide the modal
const help = document.getElementById("help");
function showHelp() {
    help.style.display = "block";
}
function hideHelp() {
    help.style.display = "none";
}

// Get the popup element
var popup = document.querySelector(".modal");

// Show the popup
function showPopup() {
    popup.style.display = "block";
}

// Confirm the action
function confirmAction() {
    // Do something (e.g. delete a record)
    console.log("Action confirmed");

    // Hide the popup
    hidePopup();
}