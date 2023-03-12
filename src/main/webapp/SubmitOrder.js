// JavaScript to populate the order details and total
const orderDetails = [
];
const orderTotal = orderDetails.reduce(
    (total, item) => total + item.price,
    0
);


const orderDetailsList = document.getElementById("order-details");


orderDetails.forEach((item) => {
    const listItem = document.createElement("li");
    listItem.innerText = `${item.name} - $${item.price}`;
    orderDetailsList.appendChild(listItem);
});
// JavaScript to show/hide the modal
const modal = document.getElementById("modal");
function showModal() {
    modal.style.display = "block";
}
function hideModal() {
    modal.style.display = "none";
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
