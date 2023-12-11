document.addEventListener("DOMContentLoaded", function () {
    const limit = 10; // ou tout autre nombre entier nécessaire

    // Ensuite, appelez la fonction fetchProduitsPlusVendus avec la variable limit
    fetchProduitsPlusVendus(limit);

    // Fetch des statistiques depuis le backend
    fetchStatistiques();

    // Ensuite, appelez la fonction fetchChiffreAffairesAnnuel avec l'année actuelle
    const currentYear = new Date().getFullYear();
    fetchChiffreAffairesAnnuel(currentYear);

    // Ensuite, appelez la fonction fetchChiffreAffairesMensuel avec le mois et l'année actuels
    const currentMonth = new Date().getMonth() + 1;
    fetchChiffreAffairesMensuel(currentMonth, currentYear);

    // ... (restez inchangé)
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

document.addEventListener("DOMContentLoaded", function () {
    const currentYear = new Date().getFullYear();
    const currentMonth = new Date().getMonth() + 1;

    // Ensuite, appelez la fonction fetchChiffreAffairesAnnuel avec l'année actuelle
    fetchChiffreAffairesAnnuel(currentYear);

    // Ensuite, appelez la fonction fetchChiffreAffairesMensuel avec le mois et l'année actuels
    fetchChiffreAffairesMensuel(currentMonth, currentYear);

    // ... (restez inchangé)
});

function fetchChiffreAffairesAnnuel() {
    // Récupérez la valeur de l'année à partir de la liste déroulante
    const selectedYear = document.getElementById('selectYearAnnuel').value;

    fetch(`/chiffreAffairesAnnuel?year=${selectedYear}`)
        .then(response => {
            if (!response.ok) {
                console.error(`Error: ${response.status}`);
                throw new Error(`Error: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            console.log("Chiffre d'affaires annuel (raw data):", data);

            // Convertissez la chaîne en nombre
            const chiffreAffairesAnnuel = Number(data.trim());

            if (!isNaN(chiffreAffairesAnnuel)) {
                // Mettez à jour le contenu HTML avec le chiffre d'affaires annuel
                const chiffreAffairesAnnuelElement = document.getElementById('chiffreAffairesAnnuel');
                if (chiffreAffairesAnnuelElement) {
                    chiffreAffairesAnnuelElement.textContent = chiffreAffairesAnnuel.toString();
                    console.log("Mise à jour réussie !");
                } else {
                    console.error("Erreur : Element HTML non trouvé.");
                }
            } else {
                console.error("Erreur : La valeur n'est pas un nombre valide.");
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}


function fetchChiffreAffairesMensuel() {
    const selectedMonth = document.getElementById('selectMonth').value;
    const selectedYear = document.getElementById('selectYear').value;

    fetch(`/chiffreAffairesMensuel?month=${selectedMonth}&year=${selectedYear}`)
        .then(response => {
            if (!response.ok) {
                console.error(`Error: ${response.status}`);
                throw new Error(`Error: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            console.log("Chiffre d'affaires mensuel (raw data):", data);

            // Convertissez la chaîne en nombre
            const chiffreAffairesMensuel = Number(data.trim());

            if (!isNaN(chiffreAffairesMensuel)) {
                // Mettez à jour le contenu HTML avec le chiffre d'affaires mensuel
                const chiffreAffairesMensuelElement = document.getElementById('chiffreAffairesMensuel');
                if (chiffreAffairesMensuelElement) {
                    chiffreAffairesMensuelElement.textContent = chiffreAffairesMensuel.toString();
                    console.log("Mise à jour réussie !");
                } else {
                    console.error("Erreur : Element HTML non trouvé.");
                }
            } else {
                console.error("Erreur : La valeur n'est pas un nombre valide.");
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}



