delete from order_book;
delete from `order`;

insert into `order` values(1, concat( date_format(now(), '%Y%m%d-'), lpad(cast(1 as char), 5, '0') ), 0, 'Ohio, USA', 5);
insert into `order` values(2, concat( date_format(now(), '%Y%m%d-'), lpad(cast(2 as char), 5, '0') ), 0, 'Ohio, USA', 5);
insert into `order` values(3, concat( date_format(now(), '%Y%m%d-'), lpad(cast(3 as char), 5, '0') ), 0, 'Ohio, USA', 6);
insert into `order` values(4, concat( date_format(now(), '%Y%m%d-'), lpad(cast(4 as char), 5, '0') ), 0, 'Hong Kong, China', 6);

insert into order_book values(1,1,5);
insert into order_book values(1,2,4);
insert into order_book values(2,3,6);

update `order` set price = 50000, address = 'Shanghai, China' where no_full = '20200201-00004';
delete from `order` where no_full = '20200201-00004';

select * from `order`;
select * from order_book;
select count(no) from `order` where no_full like concat(date_format(now(), '%Y%m%d-'), '%');
select max(no)+1 from `order`;

select O.no, O.no_full, M.name, M.email, O.price, O.address from `order` O join member M on(O.member_no = M.no) order by O.no asc;