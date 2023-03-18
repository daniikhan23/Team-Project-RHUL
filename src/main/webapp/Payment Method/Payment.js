const form = document.querySelector('form');
const nameInput = document.querySelector('#name');
const cardInput = document.querySelector('#card');
const expiryInput = document.querySelector('#expiry');
const cvvInput = document.querySelector('#cvv');

form.addEventListener('submit', (event) => {
    event.preventDefault();
    if (nameInput.value === '' || cardInput.value === '' || expiryInput.value === '' || cvvInput.value === '') {
        const error = document.createElement('p');
        error.classList.add('error');
        error.textContent = 'Please fill in all fields';
        form.appendChild(error);
    } else {
        // code to process payment
        alert('Payment successful');
    }
});
