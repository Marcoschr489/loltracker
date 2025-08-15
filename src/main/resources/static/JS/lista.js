// Tooltip de habilidades
document.querySelectorAll('.habilidade-icone').forEach(img => {
    img.addEventListener('mouseenter', async () => {
        const campeao = img.dataset.campeao;
        const habilidade = img.dataset.habilidade.toUpperCase();
        const url = `/splashArt/Campeoes/${campeao}/${habilidade}tooltip.txt`;

        try {
            const response = await fetch(url);
            if (!response.ok) throw new Error('Arquivo não encontrado');
            const text = await response.text();
            const linhas = text.split('\n');
            const descricao = linhas[2] || 'Sem descrição';
            img.title = descricao.replace(/"/g, '');
        } catch (e) {
            img.title = 'Descrição não encontrada';
        }
    });
});

// Fechar modal ao clicar no "x"
document.querySelectorAll('.close').forEach(btn => {
    btn.addEventListener('click', function() {
        const modal = this.closest('.modal');
        if (modal) modal.style.display = 'none';
    });
});

// Fechar modal ao clicar fora
window.addEventListener('click', function(event) {
    document.querySelectorAll('.modal').forEach(modal => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});

// Botão "Ver Detalhes"
document.querySelectorAll('.btnDetalhes').forEach(btn => {
    btn.addEventListener('click', function() {
        const nome = this.dataset.nome;

        if (!nome) {
            console.error("Nome do campeão está indefinido");
            return;
        }

        fetch(`/campeoes/campeao/${encodeURIComponent(nome)}`)
            .then(async res => {
                if (!res.ok) throw new Error("Campeão não encontrado");
                const text = await res.text();
                if(!text) throw new Error("Respota vazia do servidor");
                return JSON.parse(text);
            })
            .then(data => console.log("Dados recebidos:", data))
            .catch(err => console.error(err));
    });
});

// Botão fechar modal principal
document.addEventListener('DOMContentLoaded', () => {
    const fecharBtn = document.getElementById('fecharModal');
    if (fecharBtn) {
        fecharBtn.addEventListener('click', function () {
            const modal = document.getElementById('modalDetalhes');
            if (modal) modal.style.display = 'none';
        });
    }
});
