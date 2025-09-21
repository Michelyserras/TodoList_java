let listas = [];
let listaAtual = null;

const formListas = document.querySelector(".lists");
const formTask = document.querySelector(".create-task");
const exLists = document.querySelector(".ex-lists");
const exTasks = document.querySelector(".tasks-todoList");

// criando nova lista

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

    const ul = document.querySelector(".ex-lists");
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
  exLists.style.display = "none";
  formTask.style.display = "block";
}

// abrir formulario de nova tarefa
const btnTask = document.querySelector(".btn-newTask");
btnTask.addEventListener("click", function () {
  const dialog = document.querySelector(".dialog");
  dialog.showModal();
});

// fechar formulario de nova tarefa
const btnLeaveTask = document.querySelector(".btn-leaveTask");
btnLeaveTask.addEventListener("click", function () {
  const dialog = document.querySelector(".dialog");
  dialog.close();
});

// adicionar nova tarefa
const dialog = document.querySelector(".dialog");

const btnAddTask = document.querySelector(".btn-addTask");
btnAddTask.addEventListener("click", (e) => {
  e.preventDefault();

  const nome = document.querySelector(".nome-task").value.trim();
  const descricao = document.querySelector(".descricao-task").value.trim();
  const dataTermino = document.querySelector(".dataTermino").value;
  const categoria = document.querySelector(".categoria-task").value.trim();
  const prioridade = document.querySelector(".prioridade").value;
  const status = document.querySelector(".status-task").value;

  if (!nome || !descricao || !dataTermino || !categoria) {
    alert("Por favor, preencha todos os campos obrigatórios.");
    return;
  }

  if (listaAtual) {
    listaAtual.tarefas.push({
      nome,
      descricao,
      dataTermino,
      categoria,
      prioridade,
      status,
    });
  }

  // cria o item da lista
  const li = document.createElement("li");
  li.classList.add("task-item");
  var textoHTML = `
    <div class="task-header">
    <span class="status ${status.toLowerCase()}">${status}</span>
    <strong>${nome}</strong>
    </div>
    <p>${descricao}</p>
    <small class="task-info">${formatDate(
      dataTermino
    )} | ${categoria} | Prioridade: ${prioridade}</small>
    <div class="task-actions">
      <button class="btn-edit" type="button">Editar</button>
      <button class="btn-delete" type="button">Excluir</button>
    </div>

  `;

  li.innerHTML = textoHTML;
  exTasks.appendChild(li);

  // limpa os campos
  document.querySelector(".nome-task").value = "";
  document.querySelector(".descricao-task").value = "";
  document.querySelector(".dataTermino").value = "";
  document.querySelector(".categoria-task").value = "";
  document.querySelector(".prioridade").value = "1";
  document.querySelector(".status-task").value = "TODO";

  // fecha o formularop
  dialog.close();
});

function formatDate(dateStr) {
  const [year, month, day] = dateStr.split("-");
  return `${day}/${month}/${year}`;
}

// voltar para listas
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
  formListas.style.display = "block";
  exLists.style.display = "block";
  formTask.style.display = "none";
  exTasks.innerHTML = "";
  const titulo = document.querySelector(".titulo");
  titulo.textContent = "Minhas listas";
  listaAtual = null;
});

// editar tarefa
exTasks.addEventListener("click", function (e) {
  if (e.target.classList.contains("btn-edit")) {
    const taskItem = e.target.closest(".task-item");
    const taskName = taskItem.querySelector("strong").textContent;
    const taskDescription = taskItem.querySelector("p").textContent;
    const taskInfo = taskItem.querySelector(".task-info").textContent;
    const status = taskItem.querySelector(".status").textContent;

    const [datePart, categoryPart, priorityPart] = taskInfo
      .split("|")
      .map((part) => part.trim());
    const [day, month, year] = datePart.split("/");
    const dataTermino = `${year}-${month}-${day}`;
    const categoria = categoryPart;
    const prioridade = priorityPart.split(":")[1].trim();
    document.querySelector(".nome-task").value = taskName;
    document.querySelector(".descricao-task").value = taskDescription;
    document.querySelector(".dataTermino").value = dataTermino;
    document.querySelector(".categoria-task").value = categoria;
    document.querySelector(".prioridade").value = prioridade;
    document.querySelector(".status-task").value = status;
    const dialog = document.querySelector(".dialog");
    dialog.showModal();

    taskItem.remove();
  }

  // excluir tarefa
  if (e.target.classList.contains("btn-delete")) {
    const taskItem = e.target.closest(".task-item");
    taskItem.remove();
  }
});
