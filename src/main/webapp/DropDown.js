// Get the dropdown list element
var dropdown = document.getElementById("myDropdown");

// Add an event listener to the dropdown list
dropdown.addEventListener("change", function () {
    // Get the selected value
    var selectedValue = dropdown.value;

    // Do something with the selected value (e.g. display it on the page)
    console.log("Selected value: " + selectedValue);
});
