// statistiques.js

document.addEventListener("DOMContentLoaded", function () {
    // Fetch des statistiques depuis le backend
    fetchStatistiques();
});

function fetchStatistiques() {
    fetch('http://localhost:8080/stats') // Assurez-vous d'avoir un endpoint approprié dans votre backend
        .then(response => response.json())
        .then(data => {
            // Mettez à jour le contenu HTML avec les statistiques
            document.getElementById('totalCommandes').textContent = data.totalCommandes;
            document.getElementById('totalClients').textContent = data.totalClients;
            document.getElementById('totalProduits').textContent = data.totalProduits;
        })
        .catch(error => {
            console.error('Erreur lors de la récupération des statistiques :', error);
        });
}
