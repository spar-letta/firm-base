-- users
insert into core.users (id, public_id, user_name, deleted, contact_email, first_name) values (1,'7a40fd66-36a6-4487-9509-e9b12a61bff9','testuser',false,'admin@gmail.com','peterKason');
insert into core.users (id, public_id, user_name, deleted, contact_email, first_name) values (2,'2d7c44c0-3e3c-415d-b4e3-feb799f41e04','Cao',false,'xueqin@gmail.com','Xueqin');
insert into core.users (id, public_id, user_name, deleted, contact_email, first_name) values (3,'054e1a02-9cd4-476b-bb4b-22fda2ef02df','Charles',false,'dickens@gmail.com','Dickens');
insert into core.users (id, public_id, user_name, deleted, contact_email, first_name) values (4,'7ba79c3b-f16b-44da-9696-0174e36d7021','Agatha',false,'christie@gmail.com','Christie');

insert into farmer_v1.counties(id, public_id, deleted, county_name, county_code) values (1123, 'c219300f-d912-4abb-9a3c-459569f6a161', false, 'kakabgm', '34');
insert into farmer_v1.counties(id, public_id, deleted, county_name, county_code) values (1124, '512b8907-b571-4405-9cb7-1395b05cb99d', false, 'yemen', '35');

insert into farmer_v1.sub_counties(id, public_id, deleted, sub_county_name) values (2357, '7834f975-ccb0-4bc4-b5c9-f329c7f04e91', false, 'sipala');

insert into farmer_v1.farmer_categories(id, public_id, deleted, category, land_size) values (20, '0c10b297-ad74-4a71-88fa-783b8245aece', false, 'Large', '20');

insert into core.institutions(id, public_id, institution_name) values (24, 'e6cc80f4-62d4-40ee-9acb-772f8b4404fe', 'Mouse agrovet');

insert into farmer_v1.farmers(farmer_id, deleted, public_id, first_name, join_date, institution_id, last_name, other_name, national_id) values (321, false, 'd2d45432-3bfa-47c7-8701-fa1711ca6079', 'enock', now(), 24, 'Jesus', 'friends forever', '7612340');

insert into farmer_v1.contacts(contact_id, deleted, public_id, email_address, phone_number, physical_address_id) values(321, false, '4cfc18bc-ea28-47b5-89ca-3a19b6406afc', 'email.agro@gmail.com', '0756431578', 321);