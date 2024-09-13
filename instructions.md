TODO List Backend

Nesse trabalho deverá ser implementado o backend da nossa aplicação TODO List (Não é necessário frontend). O projeto deverá ser implementado seguindo as especificações abaixo. Ao concluir o projeto deverá ser postado o link do GitHub do projeto e o nome dos integrantes da equipe no Google Forms do trabalho (irei divulgar o link posteriormente). Devem ser formadas equipes de 4 alunos e em caso de sobra poderá ser feito uma equipe com 5 alunos. A data limite de entrega é até as 23:59 de 5/10.

Contexto
O nosso TODO App armazena a lista de tarefas do usuário. Nesse backend você deverá fornecer as funcionalidades básicas para que o App funcione.

Requisitos Funcionais
1 - Cadastro de uma tarefa

Será recebido via POST uma requisição para cadastro de uma tarefa seguindo a seguinte estrutura:
{
   "titulo": "Titulo da tarefa",
   "descricao": "Descrição da tarefa",
   "status": "O status da tarefa, poderá ser os valores PARA FAZER, FAZENDO e FEITO"
}

Deverão ser feitas as seguintes validações
O campo título é obrigatório e não deverá ter mais que 100 caracteres.
O campo descrição é obrigatório.
O campo status é obrigatório e só poderá ter os valores PARA FAZER, FAZENDO e FEITO. Qualquer valor diferente desses irá gerar um erro (dica: usar enum para limitar os valores possíveis)

Em caso de erro de validação deverá ser retornado a seguinte resposta junto de um erro 400 (bad request)

{
   "titulo": "Recurso não encontrado",
   "descricao": "Tarefa com id 1 não encotrada",
"campos": ["Campo nome não é válido"],
}

Ver exemplo de como retornar erro aqui: https://github.com/felipegustavo/ifba-saj-eng-soft-2/blob/main/demo-crud/src/main/java/edu/ifba/saj/demo_crud/exceptions/DefaultControllerAdvice.java no método public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex).

A tarefa deverá ser salva no banco de dados. Deverá ser retornada a tarefa cadastrada acrescida do id que será gerado automaticamente pela aplicação. Exemplo abaixo:

{
   "id": "1",
   "titulo": "Teste",
   "descricao": "Teste",
   "status": "PARA FAZER",
   "data_criacao": "2024-09-12" // A data de criação será gerada automaticamente pela aplicação com a data atual
}

2 - Atualização de uma tarefa

Será recebido via PUT uma requisição para atualização de uma tarefa seguindo a estrutura do cadastro. O id da tarefa deverá ser recebido pela url (por exemplo, PUT localhost:808/1).  A estrutura de requisição e resposta será a mesma do cadastro.

3 - Remoção de uma tarefa

Será recebido via DELETE uma requisição para remoção de uma tarefa. O id da tarefa deverá ser recebido pela url (por exemplo, DELETE localhost:808/1).  Deverá ser retornado um código 204 indicando que foi removida com sucesso. Caso o usuário tente remover uma tarefa que não existe deverá ser retornado um código 404 com uma resposta de erro seguindo a seguinte estrutura.

{
   "titulo": "Recurso não encontrado",
   "descricao": "Tarefa com id 1 não encotrada"
}


4 - Buscar todas as tarefas


Deverá ser retornado um array com todas as tarefas cadastradas no banco de dados com todos os campos, incluindo id. A aplicação irá receber o método GET para essa operação.


[{
   "id": "1",
   "titulo": "Teste",
   "descricao": "Teste",
   "status": "PARA FAZER",
   "data_criacao": "2024-09-12"
},
{
   "id": "2",
   "titulo": "Teste 2",
   "descricao": "Teste 2",
   "status": "FAZENDO",
   "data_criacao": "2024-09-12"
}]


5 - Busca tarefa por ID


Deverá ser retornada uma tarefa de acordo com o seu id. O id da tarefa deverá ser recebido pela url (por exemplo, GET localhost:808/1). Caso o usuário tente buscar uma tarefa que não existe também deverá ser retornado um erro de recurso não encontrado similar a remoção.


6 - Busca tarefa por status


Deverá ser retornado um array com todas as tarefas cadastradas no banco de dados com todos os campos, incluindo id, que tenham o status passado na url (PARA FAZER, FAZENDO ou FEITO). A aplicação irá receber o método GET para essa operação (por exemplo, GET localhost:808/status/FAZENDO).


Requisitos Não Funcionais

1 - Os dados deverão ser salvos no banco de dados H2
2 - Para as camadas de controller e service deverão ser feitos testes unitários usando mockito.
3 - Os testes unitários deverá cobrir tanto casos de sucesso quanto de erros, com validação por exemplo, na camada de controller.
4 - Faça uso de controle advice para tratar erros.

Será avaliada a corretude das operações e se foram atendidos os requisitos não funcionais.

Boa sorte!
