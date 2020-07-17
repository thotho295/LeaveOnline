CREATE TABLE IF NOT EXISTS employee (
    id int not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null ,
    on_work TINYINT(1) not null,
    role varchar(15) not null,
    constraint primary key pk_employee (id),
    constraint uq_employee UNIQUE (email)
    );
CREATE TABLE IF NOT EXISTS leave_request (
    id int not null auto_increment,
    start_time timestamp not null default current_timestamp(),
    end_time timestamp not null default current_timestamp(),
    reason varchar(255) not null,
    employee_id int not null,
    status varchar(20) not null,
    constraint primary key pk_leave_request (id),
    constraint foreign key fk_leave_request (employee_id) references employee (id)
    );
CREATE TABLE IF NOT EXISTS employee_manager (
    employee_id int not null ,
    manager_id int not null ,
    constraint primary key pk_employee_manager (employee_id, manager_id),
    constraint foreign key fk_employee_manager_employee (employee_id) references employee (id),
    constraint foreign key fk_employee_manager_manager (manager_id) references employee (id)
);
CREATE TABLE IF NOT EXISTS request_approver (
    request_id int not null ,
    employee_id int not null ,
    constraint primary key pk_request_approver (request_id, employee_id),
    constraint foreign key fk_request_approver_request (request_id) references leave_request (id),
    constraint foreign key fk_request_approver_employee (employee_id) references employee (id)
);