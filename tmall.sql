create table user(
  id int(11) NOT NULL auto_increment,
  name varchar(255) default null,
  password varchar(255) default null,
  primary key(id)
) engine=InnoDB default charset=utf8;

create table category(
   id int(11) not null auto_increment,
   name varchar(255) default null,
   primary key(id)
) engine=InnoDB default charset=utf8;

create table property(
	id int(11) not null auto_increment,
	cid int(11) default null,
	name varchar(255) default null,
	primary key(id),
	constraint fk_property_catgory foreign key(cid) references category (id)
) engine=InnoDB default charset=utf8;

create table product(
    id int(11) not null auto_increment,
    name varchar(255) default null,
    subTitle varchar(255) default null,
    orignalPrice float default null,
    promotePrice float default null,
    stock int(11) default null,
    cid int(11) default null,
    createDate datetime default null,
    primary key (id),
    constraint fk_product_category foreign key(cid) references category (id)
) engine=InnoDB default charset=utf8;

create table propertyvalue(
    id int(11) not null auto_increment,
    pid int(11) default null,
    ptid int(11) default null,
    value varchar(255) default null,
    primary key (id),
    constraint fk_propertyvalue_property foreign key(ptid) references property(id),
    constraint fk_propertyvalue_product foreign key(pid) references product(id)

)engine=InnoDB default charset=utf8;

create table productimage(
     id int(11) not null auto_increment,
     pid int(11) default null,
     type varchar(255) default null,
     primary key (id),
     constraint fk_productimage_product foreign key (pid) references product (id)
)engine=InnoDB default charset=utf8;

create table review(
     id int(11) not null auto_increment,
     content varchar(4000) default null,
     uid int(11) default null,
     pid int(11) default null,
     primary key(id),
     constraint fk_review_product foreign key(pid) references product (id),
     constraint fk_review_user foreign key(uid) references user(id)
)engine=InnoDB default charset=utf8;

create table order_(
     id int(11) not null auto_increment,
     ordercode varchar(255) default null,
     address varchar(255) default null,
     post varchar(255) default null,
     receiver varchar(255) default null,
     mobile varchar(255) default null,
     userMessage varchar(255) default null,
     createDate datetime default null,
     payDate datetime default null,
     deliveryDate datetime default null,
     confirmDate datetime default null,
     uid int(11) default null,
     status varchar(255) default null,
     primary key (id),
     constraint fk_order_user foreign key (uid) references user (id)
) engine=InnoDB default charset=utf8;

create table orderitem(
     id int(11) not null auto_increment,
     pid int(11) default null,
     oid int(11) default null,
     uid int(11) default null,
     number int(11) default null,
     primary key(id),
     constraint fk_orderitem_product foreign key (pid) references product(id),
     constraint fk_orderitem_user foreign key(uid) references user(id)
)engine=InnoDB default charset=utf8;