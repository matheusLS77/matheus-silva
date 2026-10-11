document.querySelectorAll('.excluirMaterial').forEach(function(button) {

    button.addEventListener('click', function() {

        if (!confirm('Confirma a exclusão?')) {
            return;
        }

        const linha = this.closest('tr');
        const id = this.dataset.id;

        fetch(`/materialexcluir/${id}`, {
            method: 'DELETE'
        })
            .then(async response => {

                const mensagem = await response.text();

                if (!response.ok) {
                    throw new Error(mensagem);
                }

                linha.remove();
                alert(mensagem);
            })
            .catch(error => {
                alert(error.message);
            });
    });
});