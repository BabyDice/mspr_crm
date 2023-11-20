// addProduct.js

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

// Autres scripts nécessaires
