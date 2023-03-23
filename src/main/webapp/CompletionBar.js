const indicators = document.querySelectorAll('.indicator');

// set the first indicator as active by default
indicators[0].classList.add('active');

// loop through the indicators and add a click event listener to each one
indicators.forEach((indicator, index) => {
    indicator.addEventListener('click', () => {
        // remove the active class from all indicators
        indicators.forEach(indicator => {
            indicator.classList.remove('active');
        });

        // add the active class to the clicked indicator and all previous indicators
        for (let i = 0; i <= index; i++) {
            indicators[i].classList.add('active');
        }
    });
});
