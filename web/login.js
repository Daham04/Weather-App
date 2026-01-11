const form = document.getElementById("loginForm");
const error = document.getElementById("loginError");

form.addEventListener("submit", e => {
    e.preventDefault();

    const email = emailInput();
    const password = passwordInput();

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `email=${email}&password=${password}`
    }).then(res => {
        if (res.ok) {
            window.location.href = "/web/dashboard.html";
        } else {
            error.textContent = "Invalid credentials";
        }
    });
});

function emailInput() {
    return document.getElementById("email").value;
}

function passwordInput() {
    return document.getElementById("password").value;
}
