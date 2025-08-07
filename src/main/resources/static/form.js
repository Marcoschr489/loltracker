document.addEventListener("DOMContentLoaded", function () {
    const nomeInput = document.getElementById("nome");
    const habilidadesDiv = document.getElementById("habilidades");

    if(!nomeInput || !habilidadesDiv) return;

    const preencherHabilidades = (nome) => {
        fetch(`/api/campeoes/${nome}/habilidades`)
            .then(res => {
                if  (!res.ok) throw new Error("Erro ao buscar habilidades");
                return res.json();
                })
                .then(data => {
                    habilidadesDiv.innerHTML = "";
                    data.habilidades.forEach(hab => {
                        const container = document.createElement("div");
                        container.className = "habilidade-card";

                        container.innerHTML = `
                            <img src="/static/splashArt/${hab.tipo}.webp" alt="${hab.tipo}" width="50">
                            <p><strong>${hab.tipo}:</strong> ${hab.nome}</p>
                            <p><strong>Efeito:</strong> ${hab.efeito}</p>
                            <p><strong>Recurso:</strong> ${hab.recurso}</p>
                            <p><strong>Custo:</strong> ${hab.custo}</p>
                            `;
                            habilidadesDiv.appendChild(container);
                            });
                        })
                        .catch(error => {
                            habilidadesDiv.innerHTML = "<p>Erro ao carregar habilidades</p>";
                            console.error(error);
                        });
                    };

                    if(nomeInput.value) {
                        preencherHabilidades(nomeInput.value.toLowerCase());
                    }

                    nomeInput.addEventListener("blur", function() {
                        if(this.value) {
                            preencherHabilidades(this.value.toLowerCase());
                        }
                    });
                });