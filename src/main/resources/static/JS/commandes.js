import {token} from "./token.js";

const addProductBtn = document.getElementById("addCommandBtn")
addProductBtn.addEventListener("click", function (event){
    event.preventDefault()
    addCommande()
})
function fillSelectOptions(data, selectId) {
    const select = document.getElementById(selectId);

    console.log("Filling options for select with ID:", selectId);

    data.forEach(item => {
        console.log("Adding option:", item);
        const option = document.createElement("option");
        option.value = item.id;
        option.text = item.name;
        select.add(option);
    });
}


function addCommande() {
    // Récupérez les valeurs sélectionnées dans les sélecteurs
    const clientId = document.getElementById("clientSelect").value;
    const productId = document.getElementById("productSelect").value;
    const dateCommande = document.getElementById("dateCommandeInput").value;

    // Construisez l'objet avec les données
    const commandeData = {
        client: { id: parseInt(clientId) },
        product: { id: parseInt(productId) },
        dateCommande: dateCommande,
        // Ajoutez d'autres propriétés selon vos besoins
    };

    // Effectuez une requête vers votre backend pour ajouter la commande
    fetch("/add", {
        method: "POST",
        headers: {
            'Authorization' : 'Bearer ' + token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(commandeData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            // Réponse du backend après l'ajout réussi
            console.log("Réponse du serveur :", data);
            // Mise à jour de la table des commandes
            displayCommandes(data);
            // Effacez les valeurs dans les champs après l'ajout
            document.getElementById("clientSelect").value = "";
            document.getElementById("productSelect").value = "";
            document.getElementById("dateCommandeInput").value = "";
        })
        .catch(error => {
            console.error("Erreur lors de la requête POST:", error);
            alert("Erreur lors de l'ajout de la commande. Veuillez vérifier la console pour plus de détails.");
        });
}


// Récupérez les clients et les produits depuis votre backend
// ...

// Récupérez les clients depuis votre backend
fetch("/clients",
    {
    headers: {
        'Authorization' : 'Bearer ' + token,
        'Content-Type': 'application/json',
    }
})
    .then(response => response.json())
    .then(data => {
        console.log("Clients data:", data);
        fillSelectOptions(data, "clientSelect");
    })
    .catch(error => {
        console.error("Erreur lors de la récupération des clients:", error);
        alert("Erreur lors de la récupération des clients. Veuillez vérifier la console pour plus de détails.");
    });

// Récupérez les produits depuis votre backend
fetch("/products",     {
    headers: {
        'Authorization' : 'Bearer ' + token,
        'Content-Type': 'application/json',
    }
})
    .then(response => response.json())
    .then(data => {
        console.log("Products data:", data);
        fillSelectOptions(data, "productSelect");
    })
    .catch(error => {
        console.error("Erreur lors de la récupération des produits:", error);
        alert("Erreur lors de la récupération des produits. Veuillez vérifier la console pour plus de détails.");
    });
fetch("/getAllCommandes",     {
    headers: {
        'Authorization' : 'Bearer ' + token,
        'Content-Type': 'application/json',
    }
})
    .then(response => response.json())
    .then(data => {
        console.log("Commandes data:", data);
        // Logique pour afficher les commandes dans votre table
        displayCommandes(data);
    })
    .catch(error => {
        console.error("Erreur lors de la récupération des commandes:", error);
        alert("Erreur lors de la récupération des commandes. Veuillez vérifier la console pour plus de détails.");
    });


// Fonction pour afficher les commandes dans la table
function displayCommandes(commandes) {
    // Supprimez le contenu actuel de la table
    const commandesTable = document.getElementById("commandesTable");
    while (commandesTable.rows.length > 1) {
        commandesTable.deleteRow(1);
    }

    // Parcourez les nouvelles commandes et ajoutez-les à la table
    commandes.forEach(commande => {
        const newRow = commandesTable.insertRow(-1);

        // Dans votre fonction displayCommandes après avoir défini dateCommandeCell.textContent
        // Ajoutez les cellules avec les données de la commande
        const clientCell = newRow.insertCell(0);
        clientCell.textContent = commande.client ? commande.client.name : "N/A";

        const productCell = newRow.insertCell(1);
        productCell.textContent = commande.product ? commande.product.name : "N/A";

        const dateCommandeCell = newRow.insertCell(2);
        dateCommandeCell.textContent = commande.dateCommande;
        const formattedDate = new Date(commande.dateCommande).toLocaleDateString();
        dateCommandeCell.textContent = formattedDate;

    });
}
