import {token} from "./token.js";

const addProductBtn = document.getElementById("addProductBtn")
addProductBtn.addEventListener("click", function (){
    addProduct()
})

function addProduct() {
    // Récupérez les données du formulaire
    const productName = document.getElementById('productName').value;
    const productPrice = document.getElementById('productPrice').value;
    const productDescription = document.getElementById('productDescription').value;


    // Créez un objet avec les données
    const productData = {
        name: productName,
        description: productDescription,
        price: productPrice,
        // Ajoutez d'autres propriétés selon vos besoins
    };

    // Envoyez les données au backend (utilisez fetch ou axios par exemple)
    fetch('http://localhost:8080/products/add', {
        method: 'POST',
        headers: {
            'Authorization' : 'Bearer ' + token,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
    })
        .then(response => response.json())
        .then(data => {
            // Traitez la réponse du backend (peut inclure un message de réussite, etc.)
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
// script.js

// ...

// Function to delete a product
// script.js

// Function to delete a product
function deleteProduct(productId) {
    fetch(`http://localhost:8080/products/delete/${productId}`, {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }
    })
        .then(response => {
            if (response.ok) {
                console.log('Produit supprimé avec succès.');
                // Refresh the product list or perform other necessary actions
                loadProducts(); // Assuming you have a function to load products
            } else {
                console.error('Erreur lors de la suppression du produit.');
            }
        })
        .catch(error => console.error('Erreur lors de la suppression du produit:', error));
}

// Function to create a table row for a product
function createProductRow(product) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
        <td>
            <button type="button" onclick="deleteProduct(${product.id})">Delete</button>
        </td>
    `;
    return row;
}

// Reste du code...

// Function to load products and update the table
function loadProducts() {
    const productTableBody = document.getElementById('productTableBody');
    productTableBody.innerHTML = '';

    // Fetch products from the API
    fetch('http://localhost:8080/products',
        {
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + token
            }
        }
        )
        .then(response => response.json())
        .then(products => {
            // Create a table row for each product and append it to the table
            products.forEach(product => {
                const row = createProductRow(product);
                productTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}

// commandes.js

// Function to create a table row for a commande
function createCommandeRow(commande) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${commande.id}</td>
        <td>${commande.dateCommande}</td>
        <td>${commande.client.nom}</td>
        <td>${commande.product.name}</td>
    `;
    return row;
}

// Function to load commandes and update the table
function loadCommandes() {
    const commandeTableBody = document.getElementById('commandeTableBody');

    // Vérifiez si l'élément existe avant de continuer
    if (!commandeTableBody) {
        console.error('Element with id "commandeTableBody" not found.');
        return;
    }

    // Effacez le contenu de la table avant de la mettre à jour
    commandeTableBody.innerHTML = '';

    // Fetch commandes from the API
    fetch('http://localhost:8080/commandes', {
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }
    })
        .then(response => response.json())
        .then(commandes => {
            // Create a table row for each commande and append it to the table
            commandes.forEach(commande => {
                const row = createCommandeRow(commande);
                commandeTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching commandes:', error));
}

// Load commandes when the page is loaded
document.addEventListener('DOMContentLoaded', function () {
    loadCommandes();
    loadProducts();
});
document.addEventListener('click', function (event) {
    const target = event.target;

    // Vérifiez si le clic provient d'un bouton de suppression de produit
    if (target.tagName === 'BUTTON' && target.classList.contains('deleteProductBtn')) {
        const productId = target.getAttribute('data-product-id');

        // Vérifiez si l'ID du produit est défini
        if (productId) {
            deleteProduct(productId);
        }
    }
});


// Autres scripts nécessaires