document.addEventListener("DOMContentLoaded", function() {
    const nomeInput = document.querySelector("#nome");

    nomeInput.addEventListener("blur", function() {
        const nomeCampeao = nomeInput.value.trim().toLoweCase();

        if(!nomeCampeao) return;

        fetch(`/splashArt/campeoes/${nomeCampeao}/habilidades`)
            .then(response => {
                if(!response.ok) throw new Error("Campeão não encontrado");
                return response.json();
                })
                .then(data => {

                    document.getElementById("nomePassive").textContent = data.passive.nome;
                    document.getElementById("efeitoPassive").textContent = data.passive.efeito;
                    document.getElementById("recursoPassive").textContent = data.passive.recurso;
                    document.getElementById("custoPassive").textContent = data.passive.custo;
                    document.getElementById("iconePassive").src =`/static/splashArt/Campeoes/${nomeCampeao}/passive.webp`;


                    document.getElementById("nomeQ").textContent = data.q.nome;
                    document.getElementById("efeitoQ").textContent = data.q.efeito;
                    document.getElementById("recursoQ").textContent = data.q.recurso;
                    document.getElementById("custoQ").textContent = data.q.custo;
                    document.getElementById("iconeQ").src = `/static/splashArt/Campeoes/${nomeCampeao}/q.webp`;


                    document.getElementById("nomeW").textContent = data.w.nome;
                    document.getElementById("efeitoW").textContent = data.w.efeito;
                    document.getElementById("recursoW").textContent = data.w.recurso;
                    document.getElementById("custoW").textContent = data.w.custo;
                    document.getElementById("iconeW").src = `/splashArt/Campeoes/${nomeCampeao}/w.webp`;


                    document.getElementById("nomeE").textContent = data.E.nome;
                    document.getElementById("efeitoE").textContent = data.E.efeito;
                    document.getElementById("recursoE").textContent = data.E.recurso;
                    document.getElementById("custoE").textContent = data.E.custo;
                    document.getElementById("iconeE").src = `/static/splashArt/Campeoes/${nomeCampeao}/e.webp`;


                    document.getElementById("nomeR").textContent = data.R.nome;
                    document.getElementById("efeitoR").textContent = data.R.efeito;
                    document.getElementById("recursoR").textContent = data.R.recurso;
                    document.getElementById("custoR").textContent = data.R.custo;
                    document.getElementById("iconeR").src = `/static/splashArt/Campeoes/${nomeCampeao}/r.webp`;

                    })
                    .catch(error => {
                        console.error("erro ao carregar habilidades:", error);
                        alert("Não foi possível carregar as habilidades. Verifique o nome do campeão.");
                        });
                    });
                });