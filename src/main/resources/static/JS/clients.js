// addProduct.js

function addClient() {
    // Récupérez les données du formulaire
    const clientName = document.getElementById('clientName').value;
    const clientLastName = document.getElementById('clientLastName').value;
    const clientCoordonnees = document.getElementById('clientCoordonnees').value;
    const clientEmail = document.getElementById('clientEmail').value;



    // Créez un objet avec les données
    const productData = {
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

// Autres scripts nécessaires
