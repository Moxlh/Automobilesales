## 建库

```sql
drop table people;
create table people(
    id int primary key auto_increment,
    name varchar(10) comment '姓名' not null ,
    password varchar(20) comment '密码' not null ,
    gender varchar(3) comment '性别' not null ,
    tel_num varchar(11) comment '电话号码' not null ,
    position varchar(2) comment '职位(0员工 1经理 2总管理员)' not null,
    upper_id int comment '上级主管id',
    sum int default 0 comment '销售额'
) comment '员工';

drop table client;
create table client(
    id int primary key auto_increment,
    name varchar(10) comment '姓名',
    password varchar(20) comment '密码',
    gender varchar(3) comment '性别' not null ,
    tel_num varchar(11) comment '电话号码' not null
) comment '客户';

drop table warehouse;
create table warehouse(
    id int primary key auto_increment,
    brand varchar(20) comment '车辆品牌' not null ,
    model varchar(20) comment '车辆型号' not null ,
    color ENUM('白色', '黑色', '黄色', '红色', '绿色'),
    num int comment '车辆数量' check ( num >= 0 ),
    money double comment '车辆售价' check ( money > 0 )
) comment '车辆仓库';

drop table sell_log;
create table sell_log(
    id int primary key auto_increment,
    client_id int comment '客户id' not null,
    employee_id int comment '员工id' not null,
    brand varchar(20) comment '车辆品牌' not null,
    model varchar(20) comment '车辆型号' not null,
    color ENUM('白色', '黑色', '黄色', '红色', '绿色'),
    num int comment '购买数量' check ( num >= 1 ),
    sell_date date comment '出售日期' not null,
    money int comment '销售额' not null,
    state ENUM('0', '1', '2') default '0' comment '待审核/通过/否决'
) comment '销售日志';

drop table m_e_relationship;
create table m_e_relationship(
    id int primary key auto_increment,
    manager_id int comment '上级主管id' not null,
    employee_id int comment '员工id' not null
) comment '主管和员工关系表';

drop table c_e_relationship;
create table c_e_relationship(
    id int primary key auto_increment,
    client_id int comment '客户id' not null ,
    employee_id int comment '员工id' not null
) comment '客户和员工关系表';

```



## 添加数据

```sql
insert into people (name, password, gender, tel_num, position, upper_id)
VALUES ('admin', '123456', '男', '11451411111', '2', 1),
       ('经理1', '123456', '男', '12344445555', '1', 1),
       ('经理1', '123456', '男', '12344445555', '1', 1),
       ('销售1', '123456', '男', '14565767867', '0', 3),
       ('销售2', '123456', '女', '13575443256', '0', 3),
       ('销售3', '123456', '女', '16789990000', '0', 3),
       ('销售4', '123456', '女', '17889555345', '0', 3),
       ('销售5', '123456', '女', '15678645645', '0', 2),
       ('销售6', '123456', '女', '13432456666', '0', 2),
       ('销售7', '123456', '女', '12334354666', '0', 2),
       ('销售8', '123456', '女', '14667876888', '0', 2),
       ('销售9', '123456', '女', '16787889966', '0', 2);

insert into client (name, password, gender, tel_num)
VALUES ('张三', '123456', '男', '15465131351'),
       ('李四', '123456', '女', '16564164980'),
       ('王森', '123456', '男', '18965131351'),
       ('王梓', '123456', '男', '13465131389'),
       ('李大头', '123456', '男', '16765131312'),
       ('刘祥', '123456', '男', '18965131323'),
       ('刘祥牛', '123456', '女', '12565131334'),
       ('王伟', '123456', '男', '17865131345'),
       ('孙明', '123456', '男', '14565131367'),
       ('东方祥', '123456', '男', '10865131378'),
       ('孤独求败', '123456', '女', '17765131398'),
      ('李四', '123456', '女', '16564164980');

insert into warehouse (brand, model, color, num, money)
VALUES ('法拉利', 'F430', '红色', 10, 44580000),
       ('兰博基尼', 'URUS S', '黄色', 10, 328880000),
       ('宝马3系', '320i', '白色', 10, 3000000),
       ('奔驰C级', 'C200L', '红色', 10, 3500000),
       ('奥迪A4L', '40TFSI', '黑色', 10, 3200000),
       ('沃尔沃S60', 'T4', '黑色', 10, 3000000),
       ('凯迪拉克CT5', '28T豪华型', '白色', 10, 3500000),
       ('雷克萨斯ES', '200豪华版', '白色', 10, 300000),
       ('英菲尼迪Q50L', '2.0T豪华运动版', '红色', 10, 445800),
       ('路虎揽胜极光', 'P200 R-Dynamic SE', '黑色', 10, 44580000);

insert into m_e_relationship (manager_id, employee_id)
VALUES (1, 2),
       (2, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12);

insert into sell_log (client_id, employee_id, brand, model, color, num, sell_date, money)
values (1, 1, '法拉利', 'F430', '红色', 1, '2024-1-1', 44580000),
       (1, 2, '法拉利', 'F430', '红色', 1, '2024-1-1', 44580000),
       (2, 3, '兰博基尼', 'URUS S', '黄色', 1, '2024-1-1', 328880000),
       (3, 4, '宝马3系', '320i', '白色', 1, '2024-1-1', 3000000),
       (4, 5, '奔驰C级', 'C200L', '红色', 1, '2024-1-1', 3500000),
       (5, 6, '奥迪A4L', '40TFSI', '黑色', 1, '2024-1-1', 3200000),
       (6, 7, '沃尔沃S60', 'T4', '黑色', 1, '2024-1-1', 3000000),
       (7, 8, '凯迪拉克CT5', '28T豪华型', '白色', 1, '2024-1-1', 3500000),
       (8, 9, '雷克萨斯ES', '200豪华版', '白色', 1, '2024-1-1', 300000),
       (9, 10, '英菲尼迪Q50L', '2.0T豪华运动版', '红色', 1, '2024-1-1', 445800),
       (10, 11, '路虎揽胜极光', 'P200 R-Dyn.', '黑色', 1, '2024-1-1', 44580000);

insert into c_e_relationship (client_id, employee_id)
values (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 6),
       (6, 7),
       (7, 8),
       (8, 9),
       (9, 10),
       (10, 11);
```



## 功能

```sql
# 视图
# 顾客查看拥有的车
create view client_car as
    (select c.name as c_name, p.name as p_name, s.brand, s.model, s.color, s.num, s.money, s.sell_date from people p, client c, sell_log s where p.id=s.employee_id and c.id=s.client_id and s.state=1);
# 顾客的订单
create view client_order as
    (select c.name as c_name, p.name as p_name, s.brand, s.model, s.color, s.num, s.money, s.sell_date from people p, client c, sell_log s where p.id=s.employee_id and c.id=s.client_id);
# 全部订单
create view order_view as
    (select s.id, p.name, c.name as client_name, s.brand, s.model, s.color, s.sell_date, s.num, s.money, s.state from people p, client c, sell_log s where p.id=s.employee_id and c.id=s.client_id);
# 公司全部员工
create view employee_view as
    (select p1.id, p1.name, p1.password, p1.gender, p1.position, p1.tel_num, p1.upper_id, p2.name as upper_name, p1.sum from people p1, people p2 where p2.id=p1.upper_id);
# 全部客户
create view client_view as
    (select id, name, gender, tel_num from client);

# 索引
# 员工id索引
create index i_people_id on people(id);
# 员工上级id索引
create index i_p_upper_id on people(upper_id);
# 员工名字索引
create index i_p_name on people(name);
# 员工职位索引
create index i_p_pos on people(position);
# 客户id索引
create index i_client_id on client(id);
# 车辆品牌索引
create index i_car_brand on warehouse(brand);
# 车辆型号索引
create index i_car_model on warehouse(model);
# 车辆颜色索引
create index i_car_color on warehouse(color);
# 订单员工id索引
create index i_log_p_id on sell_log(employee_id);
# 订单客户id索引
create index i_log_c_id on sell_log(client_id);
# 订单状态索引
create index i_log_state on sell_log(state);

# 存储过程
# 添加员工
create procedure people_insert(in n varchar(10), in pwd varchar(20), in g varchar(10), in tel varchar(11), in pos ENUM('0', '1', '2'), in upper int)
begin
    insert into people (name, password, gender, tel_num, position, upper_id, sum) value (n, pwd, g, tel, pos, upper, 0);
end;
# 查找所有员工
create procedure get_all_people(
out id int,
out name varchar(20),
out gender varchar(2),
out position varchar(2),
out telNum varchar(11),
out upperId int,
out upperName varchar(20),
out sum int)
begin
    select e.id, e.name, e.gender, e.position, e.tel_num, e.upper_id, e.upper_name, e.sum
    into id, name, gender, position, telNum, upperId, upperName, sum from employee_view e;
end;
# 根据员工姓名查找员工
create procedure get_p_by_name(
in name varchar(20),
out id int,
out password varchar(20),
out gender varchar(2),
out position varchar(2),
out telNum varchar(11),
out upperId int,
out upperName varchar(20),
out sum int)
begin
    select e.id, e.password, e.gender, e.position, e.tel_num, e.upper_id, e.upper_name, e.sum
    into id, password, gender, position, telNum, upperId, upperName, sum from employee_view e where e.name=name;
end;
# 根据员工id查找员工
create procedure get_p_by_id(
out name varchar(20),
in id int,
out password varchar(20),
out gender varchar(2),
out position varchar(2),
out telNum varchar(11),
out upperId int,
out upperName varchar(20),
out sum int)
begin
    select e.id, e.password, e.gender, e.position, e.tel_num, e.upper_id, e.upper_name, e.sum
    into id, password, gender, position, telNum, upperId, upperName, sum from employee_view e where e.id=id;
end;
# 根据上级主管姓名查找员工
create procedure get_ps_by_upper_name(in upper_n varchar(20))
begin
    select * from employee_view where upper_name=upper_n;
end;
# 根据上级主管id查找员工
create procedure get_ps_by_upper_id(in upper_id int)
begin
    select * from employee_view e left join people p on p.upper_id=upper_id and p.id=e.id;
end;
# 添加客户
create procedure client_insert(in n varchar(20), in pwd varchar(20), in g varchar(3), in tel varchar(11))
begin
    insert into client (name, password, gender, tel_num) value (n, pwd, g, tel);
end;
# 查找所有客户名单
create procedure get_all_clients()
begin
    select * from client;
end;
# 根据客户姓名查找客户
create procedure get_c_by_name(in n varchar(20))
begin
    select * from client where name=n;
end;
# 根据销售id查找有关客户
create procedure get_client_by_pid(in pid int)
begin
    select * from client c left join c_e_relationship r on r.employee_id = pid;
end;
# 根据销售名字查找有关客户
create procedure get_client_by_pname(in pname varchar(20))
begin
    select * from client c left join c_e_relationship r on c.id=r.client_id and r.employee_id=(select id from people p where p.name=pname);
end;
# 查找所有订单信息
create procedure get_all_sell_log()
begin
    select * from order_view;
end;
# 根据订单id查找订单
create procedure get_sell_log_by_id(in sid int)
begin
    select * from order_view where id=sid;
end;
# 根据销售id查找订单信息
create procedure get_sell_log_by_pid(in pid int)
begin
    select * from order_view o left join sell_log s on s.employee_id=pid and o.name=(select p.name from people p where p.id=pid);
end;
# 根据销售姓名查找订单信息
create procedure get_sell_log_by_pname(in pname varchar(20))
begin
    select * from order_view where name=pname;
end;
# 根据客户姓名查找订单信息
create procedure get_sell_log_by_cname(in cname varchar(20))
begin
    select * from client_order co where co.c_name=cname;
end;
# 根据销售id和客户id添加订单
create procedure add_sell_log(in c_id int, in e_id int, in brand1 varchar(20), in model1 varchar(20), in color1 varchar(20), in num1 int, in sell_date1 date, in money1 int)
begin
    insert into sell_log (client_id, employee_id, brand, model, color, num, sell_date, money, state) value (c_id, e_id, brand1, model1, color1, num1, sell_date1, money1, 0);
end;
# 根据订单id修改订单
create procedure update_state_by_id(in sId int, in sState varchar(2))
begin
    update sell_log set state=sState where id=sId;
end;
# 查找仓库中所有车
create procedure get_all_car()
begin
    select * from warehouse;
end;
# 根据品牌查找车
create procedure get_car_by_brand(in brand varchar(20))
begin
    select * from warehouse w where w.brand=brand;
end;

# 触发器
# 订单通过后员工业绩增加
create trigger employee_sum_add
    after update on sell_log
    for each row
    begin
        update people p, sell_log s set p.sum=p.sum+s.money where s.id=NEW.id and s.state=1;
    end;
# 车辆买下后检测仓库车辆剩余量
create trigger car_left_check
    after update on warehouse
    for each row
    begin
        delete from warehouse where id=NEW.id and num=0;
    end;
# 产生订单后添加客户与销售的关系
create trigger c_e_log
    after update on sell_log
    for each row
    begin
        insert into c_e_relationship (client_id, employee_id)
            select s.client_id, s.employee_id
            from sell_log s
            where s.id=NEW.id and
                  not exists(select * from c_e_relationship cer, sell_log s where s.id=NEW.id and cer.client_id=s.client_id and cer.employee_id=s.employee_id);
    end;
# 添加员工后修改员工关系表
create trigger m_e_log
    after update on people
    for each row
    begin
        insert into m_e_relationship (manager_id, employee_id)
            select p.upper_id, p.id from people p where id=NEW.id and p.position!=2;
    end;
# 添加车后检查是否已经有相同车辆
create trigger car_add_check
    after insert on warehouse
    for each row
    begin
        update warehouse w set w.num=w.num+NEW.num
        where exists(select * from warehouse w2
        where w2.brand=NEW.brand and w2.model=NEW.model and w2.color=NEW.color and w2.money=NEW.money and w2.id!=NEW.id);

        delete from warehouse w where w.brand=NEW.brand and w.model=NEW.model and w.color=NEW.color and w.money=NEW.money and w.id=NEW.id;
    end;

# 安全措施
# 对用户进行权限管理
grant select, insert, update on people to 'cloudyW'@'localhost';
show grants for 'cloudyW'@'localhost';

# 对用户密码使用PASSWORD()进行加密
create user 'user01'@'localhost';
set password for 'user01'@'localhost' = PASSWORD('123456');

# 加全局锁进行定期备份
flush tables with read lock;
# powershell中执行的备份语句
# mysqldump -u root -p carsellmanagement > carsellmanagement.sql
# 解锁
unlock tables;

# 对日志加锁进行备份
# 对日志的备份需求较频繁，加表锁进行备份
lock tables sell_log read;
# 备份语句
# mysqldump -u root -p sell_log > sell_log.sql
# 解锁
unlock tables;
```

