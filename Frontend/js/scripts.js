let listas = [];
let listaAtual = null;

const formListas = document.querySelector(".lists");
const formTask = document.querySelector(".create-task");
const exTasks = document.querySelector(".tasks-todoList");

formListas.addEventListener("submit", function (event) {
  event.preventDefault();

  const nomeListaInput = document.querySelector(".nome-lista");
  const texto = nomeListaInput.value.trim();

  if (texto !== "") {
    const novaLista = {
      nome: texto,
      tarefas: [],
    };

    const lista = document.createElement("li");
    lista.textContent = texto;

    lista.addEventListener("click", () => {
      verlista(novaLista);
    });

    console.log(novaLista);
    listas.push(novaLista);
    nomeListaInput.value = "";

    const ul = document.querySelector(".todo-lists ul");
    ul.appendChild(lista);
  
} else {
    alert("Por favor, insira um nome para a lista.");
  }
});



function verlista(lista) {
  listaAtual = lista;

  const titulo = document.querySelector(".titulo");
  titulo.textContent = lista.nome;

  formListas.style.display = "none";
  formTask.style.display = "block";

}



// abrir formulario de nova tarefa
const btnTask = document.querySelector(".btn-newTask");
    btnTask.addEventListener("click", function() {
        const dialog = document.querySelector(".dialog");
        dialog.showModal();
    });

formTask.addEventListener("submit", function (event) {
    event.preventDefault();
    
    
});
    