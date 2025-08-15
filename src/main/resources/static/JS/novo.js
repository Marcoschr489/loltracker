function atualizar(nome) {
    nome = nome.toLowerCase();

    const splashImg = document.getElementById("splashArt");
    splashImg.src = `/splashArt/Campeoes/${nome}/splashart.png`;
    splashImg.style.display = "block";

    const habilidades = ['passive', 'q', 'w', 'e', 'r'];
    habilidades.forEach(tipo => {
        const img = document.getElementById(tipo);
        if (img) {
            img.src = `/splashArt/Campeoes/${nome}/${tipo}.webp`;
            img.style.display = "inline";
        }
    })
}

function preencherCards(nome, data) {
    ["Passive", "Q", "W", "E", "R"].forEach(tipo => {
        const habilidade = data[tipo];
        document.getElementById(`card-${tipo}`).innerHTML = `
            <img src="/splashart/Campeoes/${nome}/${tipo}.webp" alt="${tipo}" />
            <h4>${habilidade.nome}</h4>
            <p>${habilidade.efeito}</p>
            <p>Recurso: ${habilidade.recurso} | Custo: ${habilidade.custo}</p>
        `;
    });
}

document.addEventListener("DOMContentLoaded", function () {
    const nomeInput = document.getElementById("nome");

    nomeInput.addEventListener("input", function() {
        const nome = this.value.trim().toLowerCase();
        if (!nome) return;

        atualizar(nome);

        fetch(`/campeoes/descricao/habilidades/${nome}`)
            .then(response => {
                if (!response.ok) throw new Error("Erro ao buscar habilidades");
                return response.json();
            })
            .then(data => preencherCards(nome, data))
            .catch(err => console.error("Erro ao carregar habilidades:", err));
    });
});