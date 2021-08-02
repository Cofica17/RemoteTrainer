using RemoteTrainerAPI.Models;
using RemoteTrainerAPI.Models.LoginCredentialsClasses;
using RemoteTrainerAPI.Models.RegisteredUserClasses;
using RemoteTrainerAPI.Models.ResponseModels;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Results;

namespace RemoteTrainerAPI.Controllers
{
    public class LoginController : ApiController
    {
        private LoginCredentialsModel dbLogin = new LoginCredentialsModel();
        private RegisteredUserModel registeredUserModel = new RegisteredUserModel();

        [Route("api/login")]
        public async Task<Object> Login(Models.LoginCredentialsClasses.LoginCredentials loginCredentials)
        {
            RegisteredUser result = registeredUserModel.RegisteredUser.Where(u => u.LoginCredentials.Email == loginCredentials.Email && u.LoginCredentials.Pwd == loginCredentials.Pwd).FirstOrDefault();

            if (result == null)
            {
                return null;
            }

            RegisteredUserResponseModel userModel = new RegisteredUserResponseModel
            {
                IDUser = result.IDRegisteredUser,
                FirstName = result.Firstname,
                LastName = result.Lastname,
                Email = result.LoginCredentials.Email,
                Type = result.UserTypeID
            };

            return Ok(userModel);
        }
    }
}