insert into usuario(ID) values(10000);
insert into TRANSACAO (ID, valor, data, fk_usuario, DESCRICAO)
values (1, 50, to_timestamp('27/04/2020 12:00:00', 'dd/mm/yyyy hh24:mi:ss'), 10000, 'PROSPECT'),
       (2, -5012, TO_TIMESTAMP('2/04/2020 12:00:00', 'dd/mm/yyyy hh24:mi:ss'), 10000, 'BASE NET'),
       (3, 523123, TO_TIMESTAMP('15/04/2020 12:00:00', 'dd/mm/yyyy hh24:mi:ss'), 10000, 'BASE CLARO');
