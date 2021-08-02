using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using System.Web.UI.WebControls;
using RemoteTrainerAPI.Models;
using RemoteTrainerAPI.Models.RegisteredUserClasses;
using RemoteTrainerAPI.Models.RequestModels;
using RemoteTrainerAPI.Models.ResponseModels;

namespace RemoteTrainerAPI.Controllers
{
    public class RegisteredUserController : ApiController
    {
        private RegisteredUserModel db = new RegisteredUserModel();

        // GET: api/RegisteredUser
        [Route("api/all")]
        public IQueryable<RegisteredUserResponseModel> GetRegisteredUser()
        {
            var users = db.RegisteredUser.Select(u => new RegisteredUserResponseModel
            {
                FirstName = u.Firstname,
                LastName = u.Lastname,
                Email = u.LoginCredentials.Email,
                Type = u.UserTypeID
            });

            return users;
        }

        // GET: api/RegisteredUser
        [Route("api/getTrainers")]
        public List<RegisteredUserResponseModel> GetTrainers()
        {
            var users = db.RegisteredUser.Where(user => user.UserTypeID == 2);
            List<RegisteredUserResponseModel> trainers = new List<RegisteredUserResponseModel>();

            foreach(RegisteredUser user in users)
            {
                RegisteredUserResponseModel trainer = new RegisteredUserResponseModel
                {
                    IDUser = user.IDRegisteredUser,
                    FirstName = user.Firstname,
                    LastName = user.Lastname,
                    Email = user.LoginCredentials.Email
                };

                trainers.Add(trainer);
            }

            return trainers;
        }

        // POST: api/RegisteredUser
        [Route("api/register")]
        [ResponseType(typeof(RegisteredUser))]
        public IHttpActionResult PostRegisteredUser(RegisteredUserRequestModel registeredUser)
        {
            try
            {
                var user = new RegisteredUser
                {
                    Firstname = registeredUser.FirstName,
                    Lastname = registeredUser.LastName,
                    LoginCredentials = new LoginCredentials
                    {
                        Email = registeredUser.Email,
                        Pwd = registeredUser.Password
                    },
                    UserTypeID = registeredUser.UserTypeID
                };

                db.RegisteredUser.Add(user);
                db.SaveChanges();
            }
            catch (DbUpdateException e)
            when (e.InnerException?.InnerException is SqlException sqlEx && (sqlEx.Number == 2601 || sqlEx.Number == 2627))
            {
                string message = "";
                string data = sqlEx.Message.Split('(', ')')[1];
                if (data == registeredUser.Email)
                    return Conflict();

                return InternalServerError();

            }
            catch (Exception ex)
            {
                return InternalServerError();
            }
            

            return Ok();
        }


        [HttpPost]
        [Route("api/getTrainerProfile")]
        [ResponseType(typeof(TrainerProfileResponseModel))]
        public IHttpActionResult GetTrainerProfile([FromBody]int id)
        {
            RegisteredUser registeredUser = db.RegisteredUser.Find(id);
            if (registeredUser == null)
            {
                return NotFound();
            }

            TrainerProfileResponseModel trainerProfile = new TrainerProfileResponseModel
            {
                IDTrainer = registeredUser.IDRegisteredUser,
                FirstName = registeredUser.Firstname,
                LastName = registeredUser.Lastname,
                Email = registeredUser.LoginCredentials.Email,
                DegreeURL = "www.some_degree.url",
                CvURL = "www.some_cv.url",
                ProfileImage = null
            };

            return Ok(trainerProfile);
        }


        // DELETE: api/RegisteredUser/5
        [ResponseType(typeof(RegisteredUser))]
        public IHttpActionResult DeleteRegisteredUser(int id)
        {
            RegisteredUser registeredUser = db.RegisteredUser.Find(id);
            if (registeredUser == null)
            {
                return NotFound();
            }

            db.RegisteredUser.Remove(registeredUser);
            db.SaveChanges();

            return Ok(registeredUser);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool RegisteredUserExists(int id)
        {
            return db.RegisteredUser.Count(e => e.IDRegisteredUser == id) > 0;
        }
    }
}