create table owner(
    owner_id                number(6,0) generated as identity,
    owner_name              varchar2(50 char),
    phone_number            varchar2(20 char),
    address                 varchar2(100 char),
    created_time            timestamp,
    modified_time           timestamp,
    memo                    varchar2(100 char),
    constraint pk_owner_id primary key(owner_id),
    constraint nn_owner_name check(owner_name is not null),
    constraint nn_owner_phone_number check(phone_number is not null)
);

create table doctor(
    doctor_id                      number(6,0) generated as identity,
    doctor_name             varchar2(50 char),
    specialty               varchar2(50 char),  
    phone_number            varchar2(20 char),
    memo                    varchar2(100 char),
    created_time           timestamp,
    modified_time          timestamp,
    constraint pk_doctor_id primary key(doctor_id),
    constraint nn_doctor_name check(doctor_name is not null),
    constraint nn_doctor_specialty check(specialty is not null),
    constraint nn_doctor_phone_number check(phone_number is not null)
);


create table animal(
    animal_id                      number(6,0) generated as identity,
    animal_name             varchar2(50 char),
    animal_type             varchar2(20 char),
    age                     number(6,0),
    gender                  varchar2(10 char),
    weight                  number(6,1),
    memo                    varchar2(100 char),
    created_time            timestamp,
    modified_time           timestamp,
    owner_id                number(6,0),
    constraint pk_animal_id primary key(animal_id),
    constraint nn_animal_name check(animal_name is not null),
    constraint nn_animal_type check(animal_type is not null),
    constraint nn_animal_age check(age is not null),
    constraint nn_animal_gender check(gender is not null),
    constraint nn_animal_ownerid check(owner_id is not null),
    constraint fk_animal_ownerid foreign key(owner_id) references owner(owner_id)
);


create table reception (
    reception_id            number(6,0) generated as identity,
    animals_id             number(6,0),
    doctors_id             number(6,0),
    owners_id              number(6,0),
    appointment_time       varchar2(20 char),
    appointment_date       varchar2(20 char),
    reception_status       varchar2(20 char),    
    created_time           timestamp,
    modified_time          timestamp,
    constraint pk_reception_id primary key(reception_id),
    constraint fk_reception_animal_id foreign key(animals_id) references animal(animal_id) ,
    constraint fk_reception_doctor_id foreign key(doctors_id) references doctor(doctor_id) ,
    constraint fk_reception_owner_id foreign key(owners_id) references owner(owner_id) ,
    constraint nn_reception_appointment_time check(appointment_time is not null),
    constraint nn_reception_appointment_date check(appointment_date is not null),
    constraint nn_reception_reception_status check(reception_status is not null)
);
commit;

drop table reception;
drop table animal;
drop table owner;
drop table doctor;
delete from reception;
delete from animal;
delete from owner;
delete from doctor;
commit;




select * from reception;

select 
    a.Id, a.animal_name, a.animal_type, a.age, a.gender, a.weight,
    o.owner_name
from animal a 
    join owner o on a.id = o.id;

delete from owner where id = 1;

select r.id, r.animal_id, r.doctor_id, r.owner_id, r.appointment_time, r.RECEPTION_STATUS,
       a.animal_name, a.animal_type, a.age, a.gender, a.weight,
       a.memo, a.created_time, a.modified_time,
       o.owner_name, o.phone_number, o.address, o.memo
from reception r
join animal a on r.animal_id = a.id
join owner o on r.owner_id = o.id;

alter table animal modify owner_id number(6,0) not null;

SELECT 
    a.animal_id, 
    a.animal_name, 
    a.animal_type, 
    a.age,
    a.gender,
    a.memo,
    
    o.owner_name, 
    o.phone_number
FROM animal a
JOIN owner o ON a.owner_id = o.owner_id;

ALTER TABLE animal DROP CONSTRAINT fk_animal_ownerid;
ALTER TABLE animal
ADD CONSTRAINT fk_animal_ownerid
FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
ON DELETE CASCADE;
delete from owner;

DELETE FROM doctor WHERE doctor_id = 2;

ALTER TABLE reception
DROP CONSTRAINT fk_reception_animal_id;

ALTER TABLE reception
DROP CONSTRAINT fk_reception_doctor_id;

ALTER TABLE reception
DROP CONSTRAINT fk_reception_owner_id;

ALTER TABLE reception
ADD CONSTRAINT fk_reception_animal_id
FOREIGN KEY (animals_id) REFERENCES animal(animal_id)
ON DELETE CASCADE;

ALTER TABLE reception
ADD CONSTRAINT fk_reception_doctor_id
FOREIGN KEY (doctors_id) REFERENCES doctor(doctor_id)
ON DELETE CASCADE;

ALTER TABLE reception
ADD CONSTRAINT fk_reception_owner_id
FOREIGN KEY (owners_id) REFERENCES owner(owner_id)
ON DELETE CASCADE;

SELECT * FROM all_cons_columns
WHERE table_name = 'RECEPTION' AND constraint_name LIKE 'FK_RECEPTION%';

commit;

ALTER TABLE animal
DROP CONSTRAINT fk_animal_ownerid;

ALTER TABLE animal
ADD CONSTRAINT fk_animal_ownerid
FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
ON DELETE CASCADE;

delete from animal where  animal.owner_id = 6;

SELECT * FROM all_cons_columns
WHERE table_name = 'ANIMAL' AND constraint_name LIKE 'FK_ANIMAL%';


select * from animal where animal_name like '%s%';
select * from animal where upper(animal_name) like upper('%s') order by animal_id desc;

select * from doctor where doctor_id = 1;

ALTER TABLE doctor MODIFY created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;

sELECT 
    r.reception_id,
    a.animal_name, a.animal_type,
    o.owner_name, o.phone_number,
    d.doctor_name
FROM
    Reception r
 JOIN
    Animal a ON r.animals_id = a.animal_id
 JOIN
    Owner o ON r.owners_id = o.owner_id
 JOIN
    Doctor d ON r.doctors_id = d.doctor_id
ORDER BY
    r.reception_id DESC;
    
select r.reception_Id, r.appointment_time, r.reception_status,
r.created_time, r.modified_time,a.animal_name,a.animal_type,o.owner_name,d.doctor_name
from reception r
join animal a on r.animals_id = a.animal_id
join owner o on r.owners_id = o.owner_id
join doctor d on r.doctors_id = d.doctor_id;

select doctor_name || ' - ' || specialty AS doctor_info from doctor; 


SELECT animal_id FROM ANIMAL WHERE animal_name = 1;
SELECT * FROM Reception;
SELECT * FROM Animal;
SELECT * FROM Owner;
SELECT * FROM Doctor;
SELECT * FROM user_constraints WHERE table_name = 'RECEPTION';

SELECT * FROM user_constraints WHERE table_name = 'ANIMAL';

SELECT r.animals_id, r.doctors_id, r.owners_id
FROM Reception r
LEFT JOIN Animal a ON r.animals_id = a.animal_id
LEFT JOIN Owner o ON r.owners_id = o.owner_id
LEFT JOIN Doctor d ON r.doctors_id = d.doctor_id
WHERE a.animal_id IS NULL OR o.owner_id IS NULL OR d.doctor_id IS NULL;

SELECT 
    r.reception_id, 
    r.appointment_time, 
    r.reception_status, 
    a.animal_name, 
    o.owner_name, 
    d.doctor_name
FROM 
    Reception r
LEFT JOIN Animal a ON r.animals_id = a.animal_id
LEFT JOIN Owner o ON r.owners_id = o.owner_id
LEFT JOIN Doctor d ON r.doctors_id = d.doctor_id;

INSERT INTO Owner (owner_name, phone_number, address, created_time) 
VALUES ('John Doe', '010-1234-5678', 'Seoul', SYSTIMESTAMP);

-- Doctor 데이터 삽입
INSERT INTO Doctor (doctor_name, specialty, phone_number, created_time) 
VALUES ('Dr. Smith', 'Veterinary', '010-5678-1234', SYSTIMESTAMP);

-- Animal 데이터 삽입
INSERT INTO Animal (animal_name, animal_type, age, gender, weight, owner_id, created_time)
VALUES ('Buddy', 'Dog', 5, 'Male', 12.5, 1, SYSTIMESTAMP);

-- Reception 데이터 삽입
INSERT INTO Reception (animals_id, doctors_id, owners_id, appointment_time, reception_status, created_time) 
VALUES (1, 1, 1, TO_TIMESTAMP('2024-11-30 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Pending', SYSTIMESTAMP);
commit;
select DOCTOR_ID from DOCTOR where DOCTOR_NAME = '1';
	      