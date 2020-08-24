# Projeto Teste Guia Bolso 

### Objetivos 
Esse Projeto tem como Objetivo criar uma api que realiza alguns mocks de transações.
Para testar basta realizar uma chamada http get para https://transacao-mock.herokuapp.com/api/usuario/{id}/transacoes/{ano}/{mes}
no qual:
 * o id aceita números de 1.000 a 100.000.000; 
 * o ano aceita numeros acima de zero;
 * o mês aceita de um a doze;
 Caso não for enviado nesses parametros será retornado um http Bad request(400) da API;
 ### Tecnologias 
 * Java 11;
 * Banco H2;
 * Spring Boot;
 * Spring Data;
 
 ###Padrões de Projeto
 * DTO;
 * Builder;
 
 Foi Desenvolvido utilizando o TDD, do qual foi criado um teste de controller que possuia o contrato da forma que os dados deveriam ser retornados.
  
  Para os testes foi usado em sua maioria a biblioteca Assertj.
 
