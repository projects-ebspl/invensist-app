create table Users (
	id int not null auto_increment,
	firstname varchar(64),
	lastname varchar(64),
	email varchar(128),
	phone varchar(32),
	address text,
	password text,
	roleAdmin tinyint,
	rolePlanner tinyint,
	roleUser tinyint,
	primary key (id),
	constraint uk_user_email unique (email)
);
insert into Users values (1,"Admin","admin","admin@einsicht.com","1234","----","$2a$10$BlLSI1onYXWqMrscDEBuCOpBQ0TGpUes6U43ft8i5qgjBiHjQYxwq",1,1,1); 
create table Stores (
	id int not null auto_increment,
	name varchar(64) not null,
	type enum ('regular','rejection','assembly','wastage','shortage'),
	primary key (id),
	constraint uk_name unique (name)
);
create table UserStoreMapping (
	user int not null,
	store int not null,
	primary key (user, store),
	constraint fk_user_inuserstoremapping foreign key (user) references Users(id),
	constraint fk_store_inuserstoremapping foreign key (store) references Stores(id)
);
create table InvoiceTax	(
	id int not null auto_increment,
	name varchar(32) not null,
	percentage double,
	primary key (id)
);
create table Item (
	id int not null auto_increment,
	code varchar(32),
	description text,
	itemcost double,
	assemblycost double,
	type enum ('single', 'combo'),
	primary key (id),
	constraint uk_item_code unique (code)
);
create table ComboItemBreakUp (
	comboitemId int not null,
	childitemid int not null,
	childitemquantity int not null,
	constraint fk_comboitemId foreign key (comboitemId) references Item(id),
	constraint fk_childitemid foreign key (childitemid) references Item(id)
);	
create table Account (
	id int not null auto_increment,
	name varchar(64) not null,
	address text,
	phone varchar(32),
	email varchar(64),
	primary key (id)
);
create table Orders (
	id int not null auto_increment,
	client int not null,
	created datetime,
	due datetime,
	primary key (id),
	constraint fk_client_in_orders foreign key (client) references Account(id)
);
create table OrderItem(
	orderid int not null,
	itemid int not null,
	quantity int not null,
	primary key (orderid, itemid),
	constraint fk_order_in_orderitem foreign key (orderid) references Orders(id),
	constraint fk_item_in_orderitem foreign key (itemid) references Item(id)
);
create table AssemblyInvoice (
	id int not null auto_increment,
	orderid int not null,
	vendour int not null,
	due datetime,
	invoicecost double default	 0,
	totalcost double default 0,
	confirm int,
	confirmmessage text,
	primary key (id),
	constraint fk_order_in_invoiceitem foreign key (orderid) references Orders(id),
	constraint fk_vendour_in_invoiceitem foreign key (vendour) references Account(id)
);
create table AssemblyInvoiceItem(
	invoice int not null,
	item int not null,
	quantity int not null,
	primary key (invoice, item),
	constraint fk_invoice_in_assemblyinvoiceitem foreign key (invoice) references AssemblyInvoice(id),
	constraint fk_item_in_assemblyinvoiceitem foreign key (item) references Item(id)
);
create table AssemblyInvoiceTax	(
	invoice int not null,
	tax int not null,
	primary key (invoice, tax),
	constraint fk_invoice_in_assemblyinvoicetax foreign key (invoice) references AssemblyInvoice(id),
	constraint fk_tax_in_assemblyinvoicetax foreign key (tax) references InvoiceTax(id)
);
create table Inventory (
	store int not null,
	item int not null,
	inventory int not null,
	primary key (store, item),
	constraint fk_store_in_inventory foreign key (store) references Stores(id),
	constraint fk_item_in_inventory foreign key (item) references Item(id)
);
create table Transactions (
	datetime datetime not null default now(),
	fromstore int not null,
	tostore int not null,
	item int not null,
	quantity int not null,
	primary key (datetime, fromstore, tostore, item),
	constraint fk_fromstore_in_transactions foreign key (fromstore) references Stores(id),
	constraint fk_tostore_in_transactions foreign key (tostore) references Stores(id),
	constraint fk_item_in_transactions foreign key (item) references Item(id)
);
create table Associates (
	id int not null auto_increment,
	name varchar(128),
	email varchar(128),
	phone varchar(32),
	client tinyint,
	vendour tinyint,
	address text,
	notes text,
	primary key (id)
);

-- Baseline data
insert into Users values (2,"Mayuresh","Halshikar","mayuresh@einsicht.com","1234","----","mayuresh123",1,1,1);
insert into Stores values (1, "Main", "regular");
insert into Stores values (2, "Reject-1", "rejection");
insert into Stores values (3, "Assembly-1", "assembly");
insert into Stores values (4, "Wastage-1", "wastage");
insert into Stores values (5, "Shortage-1", "shortage");
insert into Item values (1, "S001", "Test-1", 40, null, "single");
insert into Item values (2, "S002", "Test-2", 35, null, "single");
insert into Item values (3, "S003", "Test-3", 37, null, "single");
insert into Item values (4, "C001", "TestC-1", 87, 5, "combo");
insert into Item values (5, "C002", "TestC-2", 104, 7, "combo");
insert into Item values (6, "C003", "TestC-3", 146, 13, "combo");
insert into UserStoreMapping values (2, 1);
insert into UserStoreMapping values (2, 2);
insert into UserStoreMapping values (2, 3);
insert into Associates values (1,"ABC Pvt. Ltd.","info@abc.com","1234",0,1,"Chakan, Pune","Bank:SBI");
insert into Associates values (2,"Friscon Services Pvt. Ltd.","contact@friscon.in","8998",0,1,"Shivajinagar, Pune","Bank:HDFC");
insert into Associates values (3,"ABC Marketers Pvt. Ltd.","abc@gmail.com","9563",1,0,"Chakan, Pune","Bank:SBI");
insert into Associates values (4,"Tolga Systems","tolga@yahoo.in","7998",1,0,"Kothrud, Pune","Bank:HDFC");
insert into Associates values (5,"Gearbox Automobiles","info@gearbox.in","6439",1,1,"Ranjangao, Pune","Bank:ICICI");

