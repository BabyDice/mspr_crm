import {token} from "./token.js";

window.onload = () =>{
    if (token == null){
        window.location.href = "/view/auth"
    }
}

let clientBtn = document.querySelector("#clientBtn")
let commandeBtn = document.querySelector("#commandeBtn")
let produitBtn = document.querySelector("#produitBtn")
let statBtn = document.querySelector("#statBtn")

clientBtn.addEventListener("click", function(){
    window.location.href="/view/clients"
})
commandeBtn.addEventListener("click", function(){
    window.location.href="/view/commandes"
})
produitBtn.addEventListener("click", function(){
    window.location.href="/view/products"
})

statBtn.addEventListener("click", function(){
    window.location.href="/view/statistiques"
})