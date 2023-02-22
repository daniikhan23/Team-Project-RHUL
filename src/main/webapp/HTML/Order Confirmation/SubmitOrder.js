// JavaScript to populate the order details and total
const orderDetails = [
    { name: "Item 1", price: 10 },
    { name: "Item 2", price: 15 },
    { name: "Item 3", price: 20 },
];
const orderTotal = orderDetails.reduce(
    (total, item) => total + item.price,
    0
);
const orderDetailsList = document.getElementById("order-details");
const orderTotalElement = document.getElementById("order-total");
orderDetails.forEach((item) => {
    const listItem = document.createElement("li");
    listItem.innerText = `${item.name} - $${item.price}`;
    orderDetailsList.appendChild(listItem);
});
orderTotalElement.innerText = `$${orderTotal}`;

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

// Hide the popup
function hidePopup() {
    popup.style.display = "none";
}

// Confirm the action
function confirmAction() {
    // Do something (e.g. delete a record)
    console.log("Action confirmed");

    // Hide the popup
    hidePopup();
}
