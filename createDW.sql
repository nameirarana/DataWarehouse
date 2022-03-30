drop database if exists Retail_DW;

Create database Retail_DW;
use Retail_DW;

Create table product
(
product_ID varchar(6)not null,
Product_Name varchar(30)not null,
price decimal(5,2) default (0.0) not null,
CONSTRAINT product_PK PRIMARY KEY (product_ID)
);

Create table supplier
(
supplier_ID varchar(5)not null,
supplier_Name varchar(30)not null,
CONSTRAINT supplier_PK PRIMARY KEY (supplier_ID)
);

Create table customer
(
customer_ID varchar(4)not null,
customer_Name varchar(30)not null,
CONSTRAINT customer_PK PRIMARY KEY (customer_ID)
);
Create table store
(
store_ID varchar(4)not null primary key,
store_Name varchar(20)not null
);

Create table time
(
time_ID int(10) not null Auto_Increment,
t_date Date not null,
t_day varchar(10) not null,
month varchar(20)not null,
quarter varchar(20)not null,
year varchar(20)not null,
CONSTRAINT time_PK PRIMARY KEY (time_ID)
);
Alter Table time Auto_Increment = 10;

Create table sales
(
product_ID varchar(6)not null,
customer_ID varchar(4)not null,
supplier_ID varchar(5)not null,
store_ID varchar(4)not null,
time_ID int(10)not null,
quantity smallINT not null,
total_Sales decimal(10,3) default (0.00) not null,
constraint product_FK foreign key (product_ID) references product(product_ID),
constraint customer_FK foreign key (customer_ID) references customer(customer_ID),
constraint supplier_FK foreign key (supplier_ID) references supplier(supplier_ID),
constraint store_FK foreign key (store_ID) references store(store_ID),
constraint time_FK foreign key (time_ID) references time(time_ID)
);


