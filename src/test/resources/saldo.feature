# language: pt

Funcionalidade: Fazer uma busca pelo nome do Cliente
  Cenário: Verificar os Dados de Cadastro e o Saldo do Cliente
    Dado Eu tenho o nome "João da Silva" do cliente definido
    Quando Eu pesquiso na API de Saldo
    Entao Eu obtenho os dados de Cadastro e Saldo do Cliente com status "200"

  Cenário: Verificar os Dados de Cadastro e o Saldo do Cliente
    Dado Eu tenho o nome "João da Silva Santos" do cliente definido
    Quando Eu pesquiso na API de Saldo
    Entao Eu obtenho os dados de Cadastro e Saldo do Cliente com status "200"

  Cenário: Verificar os Dados de Cadastro e o Saldo do Cliente
    Dado Eu tenho o nome "Menestrel Boa Vida" do cliente definido
    Quando Eu pesquiso na API de Saldo e não encontro o cliente
    Entao Eu obtenho a mensagem de erro "Ocorreu um erro ao tentar Consultar o Saldo do Cliente. Tente Novamente mais tarde!"