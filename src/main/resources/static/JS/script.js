// addProduct.js
function addClient() {
    // Récupérez les données du formulaire
    const clientName = document.getElementById('clientName').value;
    const clientLastName = document.getElementById('clientLastName').value;
    const clientCoordonnees = document.getElementById('clientCoordonnees').value;
    const clientEmail = document.getElementById('clientEmail').value;

    // Créez un objet avec les données
    const clientData = {
        name: clientName,
        lastName: clientLastName,
        coordonnees: clientCoordonnees,
        email: clientEmail,
        // Ajoutez d'autres propriétés selon vos besoins
    };

    // Envoyez les données au backend (utilisez fetch ou axios par exemple)
    fetch('http://localhost:8080/clients/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(clientData),
    })
        .then(response => response.json())
        .then(data => {
            // Traitez la réponse du backend (peut inclure un message de réussite, etc.)
            console.log('Success:', data);
            // Rafraîchissez la liste des clients ou effectuez d'autres actions nécessaires
            loadClients(); // Supposons que vous ayez une fonction pour charger les clients
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
function deleteClient(clientId) {
    fetch(`http://localhost:8080/clients/delete/${clientId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log('Client supprimé avec succès.');
                // Refresh the product list or perform other necessary actions
                loadClients(); // Assuming you have a function to load products
            } else {
                console.error('Erreur lors de la suppression du client.');
            }
        })
        .catch(error => console.error('Erreur lors de la suppression du client:', error));
}

// Function to create a table row for a product
// Function to create a table row for a client
function createClientRow(client) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${client.id}</td>
        <td>${client.name}</td>
        <td>${client.lastName}</td>
        <td>${client.coordonnees}</td>
        <td>${client.email}</td>
        <td>
            <button type="button" onclick="deleteClient(${client.id})">Delete</button>
        </td>
    `;
    return row;
}


// Function to load products and update the table
function loadClients() {
    const clientTableBody = document.getElementById('clientTableBody');
    clientTableBody.innerHTML = '';

    // Fetch products from the API
    fetch('http://localhost:8080/clients')
        .then(response => response.json())
        .then(clients => {
            // Create a table row for each product and append it to the table
            clients.forEach(clients => {
                const row = createClientRow(clients);
                clientTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching clients:', error));
}
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
function deleteProduct(productId) {
    fetch(`http://localhost:8080/products/delete/${productId}`, {
        method: 'DELETE',
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

// Function to load products and update the table
function loadProducts() {
    const productTableBody = document.getElementById('productTableBody');
    productTableBody.innerHTML = '';

    // Fetch products from the API
    fetch('http://localhost:8080/products')
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

// Function to add a new commande
function addCommande() {
    // Retrieve data from the form
    const dateCommande = document.getElementById('dateCommande').value;

    // Create an object with the data
    const commandeData = {
        dateCommande: dateCommande,
        // Add other properties as needed
    };

    // Send data to the backend (use fetch or axios, for example)
    fetch('http://localhost:8080/commandes/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(commandeData),
    })
        .then(response => response.json())
        .then(data => {
            // Process the backend response (may include a success message, etc.)
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

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
    commandeTableBody.innerHTML = '';

    // Fetch commandes from the API
    fetch('http://localhost:8080/commandes')
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
document.addEventListener('DOMContentLoaded', loadCommandes);

// Load products when the page is loaded
document.addEventListener('DOMContentLoaded', loadProducts);
document.addEventListener('DOMContentLoaded', loadClients);


// ...

// Autres scripts nécessaires
