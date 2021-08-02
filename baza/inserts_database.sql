use RemoteTrainer

insert into UserType values('User')
insert into UserType values('Trainer')

insert into TimeOfDay values('Morning')
insert into TimeOfDay values('Midday')
insert into TimeOfDay values('Evening')

insert into LoginCredentials values('user@mail.com', 'pwd123')
insert into RegisteredUser values('Filip', 'User', null, 1, 1)

insert into LoginCredentials values('trainer@mail.com', 'pwd123')
insert into RegisteredUser values('Filip', 'Trainer', null,  2, 2)

insert into LoginCredentials values('markic@mail.com', 'pwd123')
insert into RegisteredUser values('Marko', 'Markić', null, 3, 2)

insert into LoginCredentials values('ivoivic@mail.com', 'pwd123')
insert into RegisteredUser values('Ivo', 'Ivić', null, 4, 2)

insert into LoginCredentials values('anananic@mail.com', 'pwd123')
insert into RegisteredUser values('Ana', 'Anić', null, 5, 2)

insert into LoginCredentials values('miric2@mail.com', 'pwd123')
insert into RegisteredUser values('Miro', 'Mirić', null, 6, 1)

insert into Trainer_User_Relationship values(2,1)
insert into Trainer_User_Relationship values(2,6)
insert into Trainer_User_Relationship values(2,5)

insert into Schedule values(2, 1, '01-08-2021', '02-28-2021')

insert into Training values('Cardio', 'Cardio trening ima 5x necega, 20 sprinteva i ostaloga sta se nade i tako to', 2)
insert into Training values('Bodywight', 'Cardio trening ima 5x necega, 20 sprinteva i ostaloga sta se nade i tako to', 2)

insert into ScheduleDay_Training values(1, 1, 1, '02-15-2021')
insert into ScheduleDay_Training values(1, 2, 2, '02-15-2021')

insert into Video values('Video zagrijavanja', 'www.filepath.to.video.zagrijavanja.com')
insert into Video values('Video trcanja', 'www.filepath.to.video.trcanja.com')
insert into Video values('Video istezanja', 'www.filepath.to.video.istezanja.com')

insert into Exercise values('Zagrijavanje', 'Lagano zagrijavanje, trcanje 10 min, nesto tako i tako se zagrij jako dobro oke buraz?', 2,1)
insert into Exercise values('Trcanje 30 min', 'Trcanje 30 minuta srednji tempo.', 2, 2)
insert into Exercise values('Istezanje', 'itegni se dobro i to je to za danas', 2, 3)

insert into Training_Exercise values(1,1)
insert into Training_Exercise values(1,2)
insert into Training_Exercise values(1,3)

insert into Video values('Video zagrijavanja', 'www.filepath.to.video.zagrijavanja.com')
insert into Video values('Video utega', 'www.filepath.to.video.utega.com')
insert into Video values('Video istezanja', 'www.filepath.to.video.istezanja.com')

insert into Exercise values('Zagrijavanje', 'Lagano zagrijavanje, trcanje 10 min, nesto tako i tako se zagrij jako dobro oke buraz?', 2, 4)
insert into Exercise values('Utezi', 'Rad s nekakvinm utezima i puno vjezbi i puno ponavljanja ima da budes jak heheehehh', 2, 5)
insert into Exercise values('Istezanje', 'istegni se dobro i to je to za danas', 2, 6)

insert into Training_Exercise values(2,4)
insert into Training_Exercise values(2,5)
insert into Training_Exercise values(2,6)

insert into Training values('Bodywight', 'Cardio trening ima 5x necega, 20 sprinteva i ostaloga sta se nade i tako to', 2)
insert into Exercise values('Zagrijavanje', 'Lagano zagrijavanje, trcanje 10 minuta, ponovi tako dva puta', 2, 4)
insert into Exercise values('Utezi', 'Rad s utezima, deset ponavljanja, dvije minute odmora između serija', 2, 5)
insert into Exercise values('Istezanje', 'Istezanje kvadricepsa i listova, leđa i trbuh', 2, 6)
insert into Exercise values('Sprintevi', 'Sprint na 100 metara, ponoviti 10 puta, 4 minute odmora između sprinta', 2, 6)
insert into Training_Exercise values(3,7)
insert into Training_Exercise values(3,8)
insert into Training_Exercise values(3,9)
insert into Training_Exercise values(3,10)
