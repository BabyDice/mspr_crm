import {setToken} from "./token.js"

let loginBtn = document.querySelector("#loginBtn")
loginBtn.addEventListener("click", function (){
    login()
})

function login() {

    const pseudoInput = document.getElementById("inputPseudo").value;
    const passwordInput = document.getElementById("inputPassword").value;
    const errorMessage = document.getElementById("errorMessage")

    errorMessage.style.display = "none"

    let params = {
        "username": pseudoInput,
        "password": passwordInput,
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/user/login', true);
    xhr.setRequestHeader('Content-Type', 'application/json')
    xhr.onload = function () {
        if (xhr.status === 200) {
            const resp = JSON.parse(xhr.response)
            setToken(resp.token)
            window.location.href = "/view/home";
        } else if (xhr.status != 200) {
            errorMessage.style.display = "flex"
            errorMessage.textContent = xhr.response
        }
    }
    xhr.send(JSON.stringify(params))
}