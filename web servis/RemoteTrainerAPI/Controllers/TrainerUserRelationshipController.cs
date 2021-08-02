using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using RemoteTrainerAPI.Models.RequestModels;
using RemoteTrainerAPI.Models.ResponseModels;
using RemoteTrainerAPI.Models.TrainerUserRelationship;
using RemoteTrainerAPI.Models.TrainerUserRelationship.Classes;

namespace RemoteTrainerAPI.Controllers
{
    public class TrainerUserRelationshipController : ApiController
    {
        private TrainerUserRelationship db = new TrainerUserRelationship();
        private Models.Trainer_User_Relationship_RequestsModel db_requests = new Models.Trainer_User_Relationship_RequestsModel();
        private RemoteTrainerAPI.Models.RegisteredUserModel db_users = new RemoteTrainerAPI.Models.RegisteredUserModel();
        private Models.Trainer_User_RelModel trainer_User_RelModel = new Models.Trainer_User_RelModel();

        // GET: api/TrainerUserRelationship
        public IQueryable<RegisteredUser> GetRegisteredUser()
        {
            return db.RegisteredUser;
        }


        [HttpPost]
        [Route("api/sendTrainerRequest")]
        public IHttpActionResult SendTrainerRequest(Trainer_Use_RRRM trainer_Use_RRRM)
        {
            Models.Trainer_User_Relationship_Requests tr = new Models.Trainer_User_Relationship_Requests
            {
                UserID = trainer_Use_RRRM.UserID,
                TrainerID = trainer_Use_RRRM.TrainerID
            };

            db_requests.Trainer_User_Relationship_Requests.Add(tr);
            db_requests.SaveChanges();

            return Ok();
        }


        [HttpPost]
        [Route("api/acceptUserRequest")]
        public IHttpActionResult AcceptUserRequest(Trainer_Use_RRRM trainer_Use_RRRM)
        {
            Models.Trainer_User_Relationship_Requests tr = db_requests.Trainer_User_Relationship_Requests.Find(trainer_Use_RRRM.TrainerID, trainer_Use_RRRM.UserID);

            Models.Trainer_User_Relationship trainer_User_Relationship = new Models.Trainer_User_Relationship
            {
                UserID = tr.UserID,
                TrainerID = tr.TrainerID
            };

            db_requests.Trainer_User_Relationship_Requests.Remove(tr);
            db_requests.SaveChanges();
            trainer_User_RelModel.Trainer_User_Relationship.Add(trainer_User_Relationship);
            trainer_User_RelModel.SaveChanges();

            return Ok();
        }


        [HttpPost]
        [Route("api/getUsersForTrainer")]
        public IHttpActionResult PostUsersForTrainer([FromBody]int id)
        {
            RegisteredUser trainer = db.RegisteredUser.Find(id);

            List<RegisteredUser> users = trainer.Users.ToList();

            List<RegisteredUserResponseModel> usersResponseModel = new List<RegisteredUserResponseModel>();

            foreach(var user in users)
            {
                RegisteredUserResponseModel userReponseModel = new RegisteredUserResponseModel
                {
                    IDUser = user.IDRegisteredUser,
                    FirstName = user.Firstname,
                    LastName = user.Lastname,
                    Type = user.UserTypeID,
                    Email = user.LoginCredentials.Email
                };

                usersResponseModel.Add(userReponseModel);
            }


            return Ok(usersResponseModel);
        }


        [HttpPost]
        [Route("api/getUsersRequests")]
        public IHttpActionResult PostUsersRequests([FromBody]int id)
        {
            List<Models.Trainer_User_Relationship_Requests> turs = db_requests.Trainer_User_Relationship_Requests.Where(tur => tur.TrainerID == id).ToList();

            List<RegisteredUserResponseModel> usersResponseModel = new List<RegisteredUserResponseModel>();

            foreach (var tur in turs)
            {
                RemoteTrainerAPI.Models.RegisteredUser user = db_users.RegisteredUser.Find(tur.UserID);

                RegisteredUserResponseModel userReponseModel = new RegisteredUserResponseModel
                {
                    IDUser = user.IDRegisteredUser,
                    FirstName = user.Firstname,
                    LastName = user.Lastname,
                    Type = user.UserTypeID,
                    Email = user.LoginCredentials.Email
                };

                usersResponseModel.Add(userReponseModel);
            }


            return Ok(usersResponseModel);
        }


        // PUT: api/TrainerUserRelationship/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutRegisteredUser(int id, RegisteredUser registeredUser)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != registeredUser.IDRegisteredUser)
            {
                return BadRequest();
            }

            db.Entry(registeredUser).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RegisteredUserExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/TrainerUserRelationship
        [ResponseType(typeof(RegisteredUser))]
        public IHttpActionResult PostRegisteredUser(RegisteredUser registeredUser)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.RegisteredUser.Add(registeredUser);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = registeredUser.IDRegisteredUser }, registeredUser);
        }

        // DELETE: api/TrainerUserRelationship/5
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