select sales.product_ID, product_Name, sales.supplier_ID,supplier_Name, month, quarter, sum(total_sales) as product_sale
from Sales, time, product, supplier 
where sales.product_ID = product.product_ID and supplier.supplier_ID = sales.supplier_ID and time.time_ID = sales.time_ID
group by month, quarter , supplier_ID, product_ID
order by quarter asc, month asc, supplier_id asc, product_ID asc;

select sales.store_ID, store.store_Name, sales.product_ID, product.product_name, sum(total_Sales) as product_Sale from sales, product, store
where sales.product_ID =product.product_ID and store.store_ID = sales.store_ID
group by store_ID, product_ID
order by store_id asc, product_id asc;

select product_name,sales.product_ID, t_day ,sum(quantity) as units_sold
from sales, time, product
where sales.time_ID = time.time_ID and sales.product_ID = product.product_ID and (t_day = "Saturday" or t_day = "Sunday")
group by product_ID
order by units_sold desc limit 5;

select sales.product_ID, product.product_name,quarter, sum(total_sales)
from sales, product, time
where sales.time_ID = time.time_ID and product.product_id = sales.product_ID 
group by quarter, sales.product_ID
order by product_id asc, quarter asc;
create view first_half as
select sales.product_ID,sum(total_sales) as Mid_1 from time, product, sales where (quarter = 1 or quarter = 2) and sales.time_ID = time.time_ID and sales.product_ID = product.product_ID group by sales.product_ID;
create view second_half as
select sales.product_ID,sum(total_sales) as Mid_2 from time, product, sales where (quarter = 3 or quarter = 4) and sales.time_ID = time.time_ID and sales.product_ID = product.product_ID group by sales.product_ID;
create view yearly as 
select first_half.product_ID, Mid_1, Mid_2, Mid_1 + Mid_2 as total_yearly
from first_half, second_half
where first_half.product_ID = second_half.product_ID;
select product_name, yearly.product_ID, Mid_1, Mid_2, total_yearly from yearly, product
where product.product_ID = yearly.product_ID;
SELECT product_name, 
COUNT(product_name) as recurring
FROM product
GROUP BY product_name
HAVING COUNT(recurring) > 1;
create table STOREANALYSIS_MV(
Store_ID varchar(4) not null,
prod_ID varchar(6) not null,
store_total decimal(10,3) default (0.00) not null,
constraint productFK foreign key (prod_ID) references product(product_ID),
constraint storeFK foreign key (store_ID) references store(store_ID)
);

insert into STOREANALYSIS_MV
select store_id, product_ID, sum(total_sales)
from sales 
group by product_ID, store_ID
order by store_id asc, product_ID asc;
select * from STOREANALYSIS_MV;