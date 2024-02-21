# language: pt

Funcionalidade: Fazer uma transferencia para outra conta
  Cenário: Verificar os Dados de Cadastro e o Saldo do Cliente para fazer uma transferencia
    Dado Eu sou o cliente "João da Silva"
    Quando Quero fazer uma transferência para o banco "001" agencia "0001" e conta "00001" do tipo "CORRENTE" no valor de "900"
    Entao Eu faço a transferência e recebo confirmação com status "200"

  Cenário: Verificar os Dados de Cadastro e o Saldo do Cliente para fazer uma transferencia
    Dado Eu sou o cliente "João da Silva Santos"
    Quando Quero fazer uma transferência para o banco "001" agencia "0001" e conta "00001" do tipo "CORRENTE" no valor de "900"
    Entao Eu não faco a transferencia porque a conta deste cliente está "INATIVA" e recebi a mensagem "O Status da Conta deve ser ativo, para realizar alguma transação"