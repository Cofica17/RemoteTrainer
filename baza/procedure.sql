use RemoteTrainer

create procedure getRegisteredUserByLoginCredentials
@email nvarchar(50),
@pwd nvarchar(50)
as
select * from RegisteredUser
inner join LoginCredentials on RegisteredUser.LoginCredentialsID = LoginCredentials.IDLoginCredentials
where Email = @email and Pwd = @pwd

--exec getRegisteredUserByLoginCredentials @email = 'test@mail.com', @pwd = 'pwd123'