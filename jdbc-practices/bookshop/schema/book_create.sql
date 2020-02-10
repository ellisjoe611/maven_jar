-- insert into book values(null, '지구 밖으로 행진하라', 15000, 2);
-- insert into book values(null, '협업의 기술', 20000, 3);
-- insert into book values(null, '목민심서', 18000, 4);
-- insert into book values(null, '이것이 경제학이다', 30000, 5);

select * from book;
select B.no, B.name, B.price, C.name from book B join category C on(B.category_no = C.no) order by B.no asc;
