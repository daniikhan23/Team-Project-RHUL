const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;


    var Symbol = new RegExp(/[~`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/);

    if (Symbol.test(username) || Symbol.test(password)){
        alert("You cannot enter Special Symbols.");
    }
    else{
        alert("no");
    }
    
    if () {
        alert("You have successfully logged in.");
    } else {
        loginErrorMsg.style.opacity = 1;
    }
})

