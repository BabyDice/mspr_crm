import {token} from "./token";

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
        headers:{
            "Content-Type":"application/json",
            "Authorization":"Bearer " + token
        }
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
    fetch('http://localhost:8080/clients', {
        headers:{
            "Content-Type":"application/json",
            "Authorization":"Bearer " + token
        }
    })
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