let token = getToken()
function setToken(value) {
    window.sessionStorage.clear()
    window.sessionStorage.setItem("user-token", value)
}

function getToken(){
    return window.sessionStorage.getItem("user-token")
}

function disconnect(){
    window.sessionStorage.clear()
    window.location.href = "/view/auth"
}

export {token, setToken, disconnect};