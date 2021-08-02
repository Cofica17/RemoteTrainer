--drop database RemoteTrainer

create database RemoteTrainer
go
use RemoteTrainer
go

create table LoginCredentials
(
	IDLoginCredentials int primary key identity,
	Email nvarchar(100) unique not null,
	Pwd nvarchar(12) not null
)

create table UserType
(
	IDUserType int primary key identity,
	UserType nvarchar(20)
)


create table RegisteredUser
(
	IDRegisteredUser int primary key identity,
	Firstname nvarchar(max) not null,
	Lastname nvarchar(max) not null,
	ProfileImage varbinary(max) null,
	LoginCredentialsID int foreign key references LoginCredentials(IDLoginCredentials) not null,
	UserTypeID int foreign key references UserType(IDUserType) not null
)

create table Video
(
	IDVideo int primary key identity,
	VideoName nvarchar(50) not null,
	VideoFilepath nvarchar(max) not null,
)

create table Training
(
	IDTraining int primary key identity,
	TrainingName nvarchar(50) not null,
	TrainingDescription nvarchar(max),
	TrainerID int foreign key references RegisteredUser(IDRegisteredUser) not null
)

create table Exercise
(
	IDExercise int primary key identity,
	ExerciseName nvarchar(50) not null,
	ExerciseDescription nvarchar(max),
	TrainerID int foreign key references RegisteredUser(IDRegisteredUser) not null,
	VideoID int foreign key references Video(IDVideo),
)

create table Training_Exercise
(
	TrainingID int foreign key references Training(IDTraining) not null,
	ExerciseID int foreign key references Exercise(IDExercise) not null,
	UNIQUE(TrainingID, ExerciseID)
)

create table Schedule
(
	IDSchedule int primary key identity,
	TrainerID int foreign key references RegisteredUser(IDRegisteredUser) not null,
	UserID int foreign key references RegisteredUser(IDRegisteredUser) not null,
	DateStart date,
	DateEnd date
)

create table TimeOfDay
(
	IDTimeOfDay int primary key identity,
	TimeOfDay nvarchar(50)
)

create table ScheduleDay_Training
(
	ScheduleID int foreign key references Schedule(IDSchedule) not null,
	TrainingID int foreign key references Training(IDTraining) not null,
	TimeOfDayID int foreign key references TimeOfDay(IDTimeOfDay) not null,
	DayDate date,
)

create table Trainer_User_Relationship
(
	TrainerID int foreign key references RegisteredUser(IDRegisteredUser) not null,
	UserID int foreign key references RegisteredUser(IDRegisteredUser) not null
	UNIQUE(TrainerID, UserID)
)

