insert into account(id,account_type,account_number)
values
(1,'Savings', '98009090'),
(2,'Current', '98009092');

insert into statement(id,date_field,amount,account_id)
values
(1,'2020-08-01','5000',1),
(2,'2020-08-02','2000',1),
(3,'2020-08-03','9000',1),
(4,'2020-08-01','1000',1),
(5,'2020-08-01','2000',2),
(6,'2020-08-02','1000',2),
(7,'2020-08-03','9000',2),
(8,'2020-08-01','8000',2);