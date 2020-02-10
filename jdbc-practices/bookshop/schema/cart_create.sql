-- insert into cart values(1, 5, 3, 3*(select price from book where no = 1));
-- insert into cart values(2, 5, 3, 3*(select price from book where no = 2));
-- insert into cart values(3, 6, 2, 2*(select price from book where no = 3));
-- insert into cart values(4, 6, 2, 2*(select price from book where no = 4));

select C.member_no, C.book_no, B.name, C.count, C.price from cart C join book B on (C.book_no = B.no) where C.member_no = 5;
select * from cart order by member_no asc;

-- insert into cart values(5, 5, 4, 4*(select price from book where no = 5));
-- update cart set count = 3, price = 3*(select price from book where no = 5) where member_no = 5 and book_no = 5;
-- delete from cart where member_no = 5 and book_no = 5;