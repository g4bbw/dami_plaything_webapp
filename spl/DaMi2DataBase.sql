drop database if exists DaMi2;

create database DaMi2
go

use DaMi2
go

--�û���--
create table loginuser
(
	userid int identity(10001,1) primary key, --ID
	usernickname varchar(50),--�ǳ�
	username varchar(50) not null, --��¼��
	userpsw varchar(50) not null, --����
	usertel varchar(20) default '��', --ע��绰
	usermail varchar(50) default '��', --ע������
	usercode int default '0', --�Ƿ񼤻� 0Ϊδ���� 1Ϊ����
	usertime datetime default getdate(), --�û�ע��ʱ��
	sex varchar(10), --�Ա�
	ip varchar(20), --���ε�¼ip
	loginip varchar(20), --�ϴε�¼ip	

)
go

insert loginuser values('�ø��ɷ�','540744679@qq.com','123','7200110','123456@qq.com','','','','168.0.0.2','')
insert into loginuser(usernickname,username,userpsw) select  '����','541287397@qq.com','123'

select*from loginuser


--�ջ���ַ��--
create table address
(
	addressid int identity(0,1) primary key, --ID
	addressname varchar(20) not null, --����
	addressadd varchar(100) not null, --��ַ
	zipcode varchar(20) not null, --�ʱ�
	telnumber varchar(20) not null, --�绰
	aid	int not null foreign key references loginuser(userid),--���û�id�����
	
)
go



--����Ա��--
create table master
(
	masterid int identity(20001,1) primary key, --ID
	mastername varchar(50) not null unique, --����Ա����
	masterpsw varchar(20) not null, --����Ա����
	adminkey int default 0, ----����ԱȨ��
		
)
go

insert into master(mastername,masterpsw) select  'admin','123'
insert into master(mastername,masterpsw) select  'admin2','123'
select*from master

--��Ʒ�����--
create table psort
(
	id int identity(1,1) primary key, --����ID
	sort varchar(20) not null, --��Ʒ����
)
go

insert psort values('��Դ')
insert psort values('����������')
insert psort values('����')
insert psort values('���')
insert psort values('��Ĥ')
insert psort values('����')
insert psort values('��ֽ')
insert psort values('֧��')
insert psort values('�ֻ�ˢ����')
insert psort values('���濨�������')
insert psort values('����Ӧ����')
insert psort values('�Ż��ײ�')
select*from psort

--�ֻ����������--
create table phone
(
	id int identity(1,1) primary key,
	sort int not null,
)
go
insert phone values(0)
insert phone values(1)
insert phone values(2)
/**
	ע��0�������1
		1�������2
		2�������1����2������

**/


--��Ʒ��--
create table product
(
	psid int identity(1000001,1) primary key, --��ƷID
	pname varchar(50) not null, --��Ʒ����
	pnameid varchar(20) not null , --��ƷС����
	pselect varchar(20) not null, --��������
	pxx varchar(100) not null ,--������Ʒ
	psort int references psort(id),--��Ʒ����
	phone int references phone(id),--��Ʒ�����ֻ�
	pinfo varchar(50) , -- ��ƷС���
	price varchar(20) not null, --��Ʒ����
	pmoblie varchar(50), --������ֻ�
	pmessage text , --��Ʒ���
	pnorms text , --��Ʒ���
	pimg varchar(50), --��Ʒչʾͼ
	pimgbig varchar(50), --��Ʒ��ͼ
	pimgmiddle varchar(50), --��Ʒ�е�ͼ
	pimgsmall varchar(50), --��ƷСͼ
	
)
go

insert into product(pname,pnameid,pselect,pxx,psort,phone,pinfo,price,pmoblie,pmessage,pnorms,pimg,pimgbig,pimgmiddle,pimgsmall) 
select '�ƶ���Դ','�ƶ���Դ','��','','1','1','','199','','','','img/test/1299_1_1344938511_8.jpg','','',''
insert into product(pname,pnameid,pselect,pxx,psort,phone,pinfo,price,pmoblie,pmessage,pnorms,pimg,pimgbig,pimgmiddle,pimgsmall) 
select 'IDEASKIN��ֽ�����㣩','��ֽ','��','','1','1','','39','','','','img/test/1148_1_1344924846_8.jpg','','',''
insert into product(pname,pnameid,pselect,pxx,psort,phone,pinfo,price,pmoblie,pmessage,pnorms,pimg,pimgbig,pimgmiddle,pimgsmall) 
select 'SanDisk16GB�洢����TF Class6��','���濨','����','16GB','10','1','Class6���ٶ�ȡ�ٶȣ�ȫ���𺳵ļ��ٴ�������','60','�����ֻ�1','','','img/test/1146_1_1346817900_8.jpg','img/test/1146_1_1.jpg','img/test/1146_1_1346817900_8.jpg','img/test/1146_1_2.jpg'

select * from product

--���ﳵ��ϸ��--
create table CartItem
(
	id int identity(1,1) primary key , --ID
	uniqueprice varchar(20) not null, --��Ʒ�ļ۸�
	pcount varchar(20) not null, --��Ʒ����
	product int references product(psid),--��ƷID--
	
	
)

--���ﳵ��--
create table shopcart
(
	shopid int identity(123001,1) primary key, --ID
	userid	int references loginuser(userid),--�û�ID--
	cartitem int references CartItem(id), --�������ﳵ��ϸ
	shoppingcartStatus int default 0, --���ﳵ״̬
	createtime datetime default getdate(), --����ʱ��--
	
)
go


--������Ϣ--
create table indent
(
	id int identity(1101,1) primary key, --����ID
	userid int not null foreign key references loginuser(userid),
	totalprices varchar(30) not null, --������Ʒ�ܼ�
	addressname varchar(20) not null, --����
	addressadd varchar(100) not null, --��ַ
	zipcode varchar(20) not null, --�ʱ�
	telnumber varchar(20) not null, --�绰	
	time datetime default getdate(), --����ʱ��--�ɽ�ʱ��
	info int default '1', --״̬**��ע��--
)
go

select * from indent
--drop table indent
--drop table indentproduct
	--����ע�����ﳵ״̬�Ƕ����ĸ���״̬����ʼֵΪ1��
����--    1���ύ����
����--    2���ѷ���
����--    3���յ�����Ѹ���
����--    4�����׽���-



--������Ʒ--
create table indentproduct
(
	shopid int identity(1,1) primary key, --ID
	did int not null foreign key references indent(id),
	pid int not null foreign key references product(psid),--��ƷID--
	price varchar(30) not null, --������Ʒ�ļ۸�--
	number varchar(20) not null, --������Ʒ������--

)
go

select * from indentproduct

	
	