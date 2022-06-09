insert into cat(jmbm, name, age, weight, breed, age_enum, gender) values ('0001', 'Paja', 36, 3000, 'RUSKA', 'ODRASLA_MACKA', 'MALE');
insert into cat(jmbm, name, age, weight, breed, age_enum, gender) values ('0002', 'Gaja', 36, 3000, 'RUSKA', 'ODRASLA_MACKA', 'MALE');
insert into cat(jmbm, name, age, weight, breed, age_enum, gender) values ('0003', 'Vlaja', 36, 3000, 'RUSKA', 'ODRASLA_MACKA', 'MALE');
insert into cat(jmbm, name, age, weight, breed, age_enum, gender) values ('0004', 'Soraja', 36, 3000, 'RUSKA', 'ODRASLA_MACKA', 'FEMALE');
insert into cat(jmbm, name, age, weight, breed, age_enum, gender) values ('0005', 'Kica', 36, 3000, 'RUSKA', 'ODRASLA_MACKA', 'MALE');

insert into hospitalized_cat(date, cat_id) values (1654538400000, 1);
insert into hospitalized_cat(date, cat_id) values (1654538400000, 2);
insert into hospitalized_cat(date, cat_id) values (1654538400000, 3);
insert into hospitalized_cat(date, cat_id) values (1654538400000, 4);
insert into hospitalized_cat(date, cat_id) values (1654538400000, 5);

insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0001', 'Ahoi', 'HEART', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0002', 'Ahoi', 'OXYGEN', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0002', 'Ahoi', 'OXYGEN', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0002', 'Ahoi', 'OXYGEN', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0002', 'Ahoi', 'OXYGEN', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0002', 'Ahoi', 'OXYGEN', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0003', 'Ahoi', 'FLUID', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0003', 'Ahoi', 'FLUID', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0003', 'Ahoi', 'FLUID', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0003', 'Ahoi', 'FLUID', 1654466400000);
insert into alarm_notification(jmbm, message, notification_type, date_time) values ('0003', 'Ahoi', 'FLUID', 1654466400000);

-- Periodontitis
insert into symptom(name) values ('Gubitak apetita'); -- 1
insert into symptom(name) values ('Potištenost'); -- 2
insert into symptom(name) values ('Višak pljuvačke'); -- 3
insert into symptom(name) values ('Zadah iz usta'); -- 4
insert into symptom(name) values ('Teško zatvaranje usta'); -- 5
insert into symptom(name) values ('Otok na licu'); -- 6
insert into symptom(name) values ('Rupica ispod oka'); -- 7

-- Ubrzana aktivnost štitne žlezde
insert into symptom(name) values ('Gubitak težine'); -- 8
insert into symptom(name) values ('Povraćanje'); -- 9
insert into symptom(name) values ('Često i glasno mjaukanje'); -- 10
insert into symptom(name) values ('Slabost'); -- 11
insert into symptom(name) values ('Krzno lošijeg kvaliteta'); -- 12
insert into symptom(name) values ('Ubrzano disanje'); -- 13

-- Dijabetes
insert into symptom(name) values ('Konzumacija velike količine vode'); -- 14
insert into symptom(name) values ('Često uriniranje'); -- 15
-- insert into symptom(name) values ('Gubitak težine') -- 8
-- insert into symptom(name) values ('Povraćanje'); -- 9
-- insert into symptom(name) values ('Slabost'); -- 11
insert into symptom(name) values ('Kolabiracija'); -- 16

-- Problemi sa glistama
-- insert into symptom(name) values ('Povraćanje'); -- 9
-- insert into symptom(name) values ('Gubitak apetita'); -- 1
insert into symptom(name) values ('Napet stomak'); -- 17
insert into symptom(name) values ('Mat krzno'); -- 18
insert into symptom(name) values ('Proliv'); -- 19
insert into symptom(name) values ('Gliste u izmetu'); -- 20
-- insert into symptom(name) values ('Gubitak težine'); -- 8

-- Oboljenje gornjih respiratornih puteva
insert into symptom(name) values ('Kijanje'); -- 21
insert into symptom(name) values ('Kašalj'); -- 22
insert into symptom(name) values ('Iscedak iz nosa'); -- 23
insert into symptom(name) values ('Povišena temperatura'); -- 24
-- insert into symptom(name) values ('Gubitak apetita'); -- 1
-- insert into symptom(name) values ('Ubrzano disanje'); -- 13

-- Konjuktivitis
insert into symptom(name) values ('Crveno i otečeno oko ili oči'); -- 25
insert into symptom(name) values ('Delimično zatvoreno oko ili oči'); -- 26
insert into symptom(name) values ('Otok unutrašnjeg kapka'); -- 27
insert into symptom(name) values ('Bistri iscedak iz oka'); -- 28
insert into symptom(name) values ('Promena na površini oka'); -- 29
-- insert into symptom(name) values ('Kijanje'); -- 21
-- insert into symptom(name) values ('Gubitak apetita'); -- 1

insert into disease(name) values ('Oboljenje zuba (Periodontitis)'); -- 1
insert into disease(name) values ('Ubrzana aktivnost štitne žlezde'); -- 2
insert into disease(name) values ('Dijabetes'); -- 3
insert into disease(name) values ('Problemi sa glistama'); -- 4
insert into disease(name) values ('Oboljenje gornjih respiratornih puteva'); -- 5
insert into disease(name) values ('Konjuktivitis'); -- 6

insert into disease_symptom (disease_id, symptom_id) values (1, 1);
insert into disease_symptom (disease_id, symptom_id) values (1, 2);
insert into disease_symptom (disease_id, symptom_id) values (1, 3);
insert into disease_symptom (disease_id, symptom_id) values (1, 4);
insert into disease_symptom (disease_id, symptom_id) values (1, 5);
insert into disease_symptom (disease_id, symptom_id) values (1, 6);
insert into disease_symptom (disease_id, symptom_id) values (1, 7);

insert into disease_symptom (disease_id, symptom_id) values (2, 8);
insert into disease_symptom (disease_id, symptom_id) values (2, 9);
insert into disease_symptom (disease_id, symptom_id) values (2, 10);
insert into disease_symptom (disease_id, symptom_id) values (2, 11);
insert into disease_symptom (disease_id, symptom_id) values (2, 12);
insert into disease_symptom (disease_id, symptom_id) values (2, 13);

insert into disease_symptom (disease_id, symptom_id) values (3, 14);
insert into disease_symptom (disease_id, symptom_id) values (3, 15);
insert into disease_symptom (disease_id, symptom_id) values (3, 8);
insert into disease_symptom (disease_id, symptom_id) values (3, 9);
insert into disease_symptom (disease_id, symptom_id) values (3, 11);
insert into disease_symptom (disease_id, symptom_id) values (3, 16);

insert into disease_symptom (disease_id, symptom_id) values (4, 9);
insert into disease_symptom (disease_id, symptom_id) values (4, 1);
insert into disease_symptom (disease_id, symptom_id) values (4, 17);
insert into disease_symptom (disease_id, symptom_id) values (4, 18);
insert into disease_symptom (disease_id, symptom_id) values (4, 19);
insert into disease_symptom (disease_id, symptom_id) values (4, 20);
insert into disease_symptom (disease_id, symptom_id) values (4, 8);

insert into disease_symptom (disease_id, symptom_id) values (5, 21);
insert into disease_symptom (disease_id, symptom_id) values (5, 22);
insert into disease_symptom (disease_id, symptom_id) values (5, 23);
insert into disease_symptom (disease_id, symptom_id) values (5, 24);
insert into disease_symptom (disease_id, symptom_id) values (5, 1);
insert into disease_symptom (disease_id, symptom_id) values (5, 13);

insert into disease_symptom (disease_id, symptom_id) values (6, 25);
insert into disease_symptom (disease_id, symptom_id) values (6, 26);
insert into disease_symptom (disease_id, symptom_id) values (6, 27);
insert into disease_symptom (disease_id, symptom_id) values (6, 28);
insert into disease_symptom (disease_id, symptom_id) values (6, 29);
insert into disease_symptom (disease_id, symptom_id) values (6, 21);
insert into disease_symptom (disease_id, symptom_id) values (6, 1);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Veto', true, false, 'SLAB_LEK', 1);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Vetoquinol', true, true, 'SREDNJE_JAK_LEK', 1);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Stomodine', true, false, 'JAK_LEK', 1);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('OZ', false, true, 'SREDNJE_JAK_LEK', 1);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Stomodine Adv', true, false, 'JAK_LEK', 1);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Expert DeVet', true, true, 'JAK_LEK', 2);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('UAŠŽ', true, false, 'SLAB_LEK', 2);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('FAD Adv', true, true, 'SREDNJE_JAK_LEK', 2);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('FAD Forte', true, true, 'JAK_LEK', 2);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Insulin', true, true, 'SLAB_LEK', 3);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('DBET', false, true, 'SREDNJE_JAK_LEK', 3);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('DBet Forte', true, false, 'JAK_LEK', 3);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Glistamicin', true, false, 'SLAB_LEK', 4);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('PSG', true, true, 'SREDNJE_JAK_LEK', 4);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('PSG Adv', false, true, 'SREDNJE_JAK_LEK', 4);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('PSG Forte', false, true, 'JAK_LEK', 4);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('WhiteSlez', true, true, 'SLAB_LEK', 5);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('OGRP', true, true, 'SREDNJE_JAK_LEK', 5);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('OGRP Forte', false, true, 'JAK_LEK', 5);

insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Virmyl', true, false, 'SREDNJE_JAK_LEK', 6);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('Glyco', true, true, 'SLAB_LEK', 6);
insert into medicine(name, female_fit, male_fit, category, disease_id) values ('HorseVita', true, false, 'JAK_LEK', 6);

insert into medicine_unsuitable_breed values (2, 'RUSKA');
insert into medicine_unsuitable_breed values (2, 'SIJAMSKA');
insert into medicine_unsuitable_breed values (3, 'SKOTSKA');
insert into medicine_unsuitable_breed values (3, 'SFINKS');
insert into medicine_unsuitable_breed values (6, 'MAINE_COON');
insert into medicine_unsuitable_breed values (9, 'MAINE_COON');
insert into medicine_unsuitable_breed values (9, 'SFINKS');
insert into medicine_unsuitable_breed values (11, 'SFINKS');
insert into medicine_unsuitable_breed values (12, 'RUSKA');
insert into medicine_unsuitable_breed values (14, 'SKOTSKA');
insert into medicine_unsuitable_breed values (14, 'RUSKA');
insert into medicine_unsuitable_breed values (15, 'SKOTSKA');
insert into medicine_unsuitable_breed values (18, 'MAINE_COON');
insert into medicine_unsuitable_breed values (21, 'MAINE_COON');

insert into medicine_unsuitable_age values (3, 'MACE');
insert into medicine_unsuitable_age values (3, 'STARA_MACKA');
insert into medicine_unsuitable_age values (6, 'MACE');
insert into medicine_unsuitable_age values (9, 'MACE');
insert into medicine_unsuitable_age values (12, 'MACE');
insert into medicine_unsuitable_age values (12, 'STARA_MACKA');
insert into medicine_unsuitable_age values (15, 'MACE');
insert into medicine_unsuitable_age values (15, 'MLADA_MACKA');
insert into medicine_unsuitable_age values (15, 'STARA_MACKA');
insert into medicine_unsuitable_age values (16, 'MACE');
insert into medicine_unsuitable_age values (16, 'MLADA_MACKA');
insert into medicine_unsuitable_age values (16, 'STARA_MACKA');
insert into medicine_unsuitable_age values (19, 'MACE');
insert into medicine_unsuitable_age values (19, 'STARA_MACKA');
insert into medicine_unsuitable_age values (22, 'MACE');
