document.addEventListener("DOMContentLoaded", function () {
    const limit = 10; // ou tout autre nombre entier nécessaire

    // Ensuite, appelez la fonction fetchProduitsPlusVendus avec la variable limit
    fetchProduitsPlusVendus(limit);

    // Fetch des statistiques depuis le backend
    fetchStatistiques();

    // Ajoutez d'autres appels de fonction pour les autres statistiques
});

function fetchStatistiques() {
    fetch('http://localhost:8080/stats')
        .then(handleResponse)
        .then(data => {
            console.log("Data received:", data);

            // Mettez à jour le contenu HTML avec les statistiques
            const totalCommandesElement = document.getElementById('totalCommandes');
            if (totalCommandesElement) {
                totalCommandesElement.textContent = data.totalVentes;
            }

            document.getElementById('totalClients').textContent = data.totalClients;
            document.getElementById('totalProduits').textContent = data.totalProduits;

            // Afficher le produit le plus vendu
            const produitPlusVendu = data[0];
            if (produitPlusVendu) {
                document.getElementById('topSellingProduct').textContent = `Produit le plus vendu : ${produitPlusVendu.nomProduit} (Ventes: ${produitPlusVendu.totalVentes})`;
                document.getElementById('nomProduitTop').textContent = produitPlusVendu.nomProduit;
                document.getElementById('ventesTop').textContent = produitPlusVendu.totalVentes;
            }

            // Afficher le produit le moins vendu
            const produitMoinsVendu = data[1];
            if (produitMoinsVendu) {
                document.getElementById('leastSellingProduct').textContent = `Produit le moins vendu : ${produitMoinsVendu.nomProduit} (Ventes: ${produitMoinsVendu.totalVentes})`;
                document.getElementById('nomProduitLeast').textContent = produitMoinsVendu.nomProduit;
                document.getElementById('ventesLeast').textContent = produitMoinsVendu.totalVentes;
            }
        })
        .catch(handleResponse);
}

// ... (restez inchangé)




function fetchProduitsPlusVendus(limit) {
    fetch("/produitsPlusVendus?limit=" + limit)
        .then(response => response.json())
        .then(data => {
            console.log("Top-selling products:", data);

            // Votre logique de traitement ici
            const produitPlusVenduElement = document.getElementById('topSellingProduct');
            const produitMoinsVenduElement = document.getElementById('leastSellingProduct');

            // Triez le tableau pour avoir le produit le plus vendu en premier
            data.sort((a, b) => b.totalVentes - a.totalVentes);

            // Afficher le produit le plus vendu
            const produitPlusVendu = data[0];
            if (produitPlusVendu) {
                produitPlusVenduElement.textContent = `Produit le plus vendu : ${produitPlusVendu.nomProduit} (Ventes: ${produitPlusVendu.totalVentes})`;
            }

            // Afficher le produit le moins vendu
            const produitMoinsVendu = data[data.length - 1];
            if (produitMoinsVendu) {
                produitMoinsVenduElement.textContent = `Produit le moins vendu : ${produitMoinsVendu.nomProduit} (Ventes: ${produitMoinsVendu.totalVentes})`;
            }
        })
        .catch(error => {
            console.error("Error fetching top-selling products:", error);
        });
}

// ... (restez inchangé)



function handleResponse(response) {
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.json();
}
