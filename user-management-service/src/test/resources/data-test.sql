
insert into core.users (change_password, public_id, date_created, date_modified, created_by_id, modified_by_id, deleted, first_name, last_name, other_name, user_name, password, contact_email, contact_phone_number, verified_email, verified_phone_number, status,id) values (false, 'e76345b6-8c1e-4aa2-9512-39467e6ec336', '2023-01-01 12:00:00', '2023-01-01 12:00:00', null, null, false, 'John', 'Doe', 'Jr.', 'john.doe', 'password123', 'john.doe@example.com', '1234567890', true, false, 'Active', 51);
insert into core.users (change_password,contact_email,contact_phone_number,created_by_id,date_created,date_modified,deleted,first_name,last_name,modified_by_id,other_name,password,public_id,status,user_name,verified_email,verified_phone_number,id) values (false,'admin@gmail.com','0708461561',null,now(),null,false,'admin','admin1',null,null,'$2a$10$JH61hCfSSk2jhXTGvjehtuZ8LmzJ53xE2hdgX5fkt/WPP3wYIkOJm','bb874ce2-dc46-4f11-8915-c1d644f236df','Active','123456',false,false,100);


insert into core.roles (created_by_id,date_created,date_modified,deleted,description,modified_by_id,name,public_id,id) values (null,now(),null,false,'role_admin',null,'ROLE_ADMIN','fb874ce2-dc46-4f11-8915-c1d644f236dd',100);
insert into core.roles (created_by_id,date_created,date_modified,deleted,description,modified_by_id,name,public_id,id) values (null, now(),null,false,'role_secretary',null,'ROLE_SECRETARY','ab874ce2-dc46-4f11-8915-c1d644f236da',101);

insert into core.user_roles(user_id_fk, role_id_fk) values (100, 100);

insert into core.privileges(id, public_id, deleted, name, role_id) values (212, 'bb874ce2-dc46-4f11-8915-c1d644f236df', false, 'READ_USER', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (213, 'ab874ce2-dc46-4f11-8915-c1d644f236d4', false, 'READ_ROLES', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (214, 'db874ce2-dc46-4f11-8915-c1d644f236d1', false, 'USER_UPDATE_PRIVILEGE', null);
insert into core.privileges(id, public_id, deleted, name, role_id) values (215, 'ec06cecc-9979-4d1f-ba7d-640506135227', false, 'ASSIGN_PRIVILEGE_TO_ROLE', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (216, 'ac3976ff-4d9e-4ad8-a19c-1c55cdf4f8be', false, 'CREATE_ROLE', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (217, 'a7c6a7f9-3d9e-4280-b02a-8c2ce912b33f', false, 'CREATE_PRIVILEGE', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (218, '40ce4097-c5bd-4343-a8d7-88490622d035', false, 'READ_PRIVILEGES', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (219, '88fe8c8a-897b-48ae-8156-80ab6d0a9d84', false, 'CREATE_INSTITUTION', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (220, '5a3fdfdb-affe-4a76-a842-b67d4c1da45b', false, 'READ_INSTITUTION', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (221, 'bfb688f6-08b9-4f09-b295-d0b1a276b230', false, 'CREATE_USER', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (223, '5b63a353-ff19-4005-bc6c-a9c2f5ea6358', false, 'READ_USERS', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (224, '86c23c7d-2cfb-4424-a9a4-7e5a5b036561', false, 'UPDATE_USER', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (225, '1392ef09-2f56-4844-a542-4441e8da3332', false, 'CREATE_INSTITUTION_USER', 100);
insert into core.privileges(id, public_id, deleted, name, role_id) values (226, '8e5d0e98-df10-4e16-ae56-233d89ba62b5', false, 'READ_PROFILE', 100);

insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 212);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 213);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 214);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 215);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 216);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 217);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 218);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 219);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 220);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 221);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 223);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 224);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 225);
insert into core.role_privileges(role_id_fk, privilege_id_fk) values (100, 226);

insert into core.institutions(id, public_id, created_by_id, deleted, institution_name, institution_phone_number, email_address, institution_website, institution_deactivated, institution_unique_code) values(123, 'eb894640-60ba-432b-9be2-4b13707bcdc1', 100,false, 'Kitale stop Agrovet', '0754321121', 'kitaagro@yahoo.com', 'www.kitl.com', false, 'UDH8223');
insert into core.institutions(id, public_id, created_by_id, deleted, institution_name, institution_phone_number, email_address, institution_website, institution_deactivated, institution_unique_code) values(124, '3020173a-93b4-4927-b9d9-9c0b6ff88c93', 100,false, 'Bungoma stop Agrovet', '0754321122', 'bgmagro@yahoo.com', 'www.bgm.com', false, '3844');