using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Runtime.Remoting;
using System.Web.Http;
using System.Web.Http.Description;
using RemoteTrainerAPI.Models.RequestModels;
using RemoteTrainerAPI.Models.ResponseModels;
using RemoteTrainerAPI.Models.Schedule;
using RemoteTrainerAPI.Models.Schedule.Classes;

namespace RemoteTrainerAPI.Controllers
{
    public class ScheduleDay_TrainingController : ApiController
    {
        private ScheduleModel db = new ScheduleModel();

        // GET: api/ScheduleDay_Training
        public IQueryable<ScheduleDay_Training> GetScheduleDay_Training()
        {
            return db.ScheduleDay_Training;
        }

        [Route("api/getScheduleForUser")]
        [ResponseType(typeof(ScheduleResponseModel))]
        [HttpPost]
        public IHttpActionResult GetScheduleDay_Training(ScheduleForUserRequestModel request)
        {
            ScheduleResponseModel schedule = new ScheduleResponseModel();
            schedule.Trainings = new List<Models.ResponseModels.Classes.Training>();
            try
            {
                List<ScheduleDay_Training> scheduleDay_Trainings = db.ScheduleDay_Training.Where(sdt => sdt.Schedule.DateStart < request.TodayDate && sdt.Schedule.DateEnd > request.TodayDate && sdt.Schedule.UserID == request.IDUser).ToList();

                if (scheduleDay_Trainings == null)
                {
                    return NotFound();
                }

                if (scheduleDay_Trainings.Count == 0)
                {
                    return NotFound();
                }

                schedule.ID = scheduleDay_Trainings[0].Schedule.IDSchedule;
                schedule.DateFrom = scheduleDay_Trainings[0].Schedule.DateStart;
                schedule.DateTo = scheduleDay_Trainings[0].Schedule.DateEnd;
                
                foreach(ScheduleDay_Training sdt in scheduleDay_Trainings)
                {
                    RemoteTrainerAPI.Models.ResponseModels.Classes.Training training = new RemoteTrainerAPI.Models.ResponseModels.Classes.Training
                    {
                        ID = sdt.Training.IDTraining,
                        DayDate = sdt.DayDate,
                        Name = sdt.Training.TrainingName,
                        TimeOfDay = sdt.TimeOfDay.TimeOfDay1
                    };

                    schedule.Trainings.Add(training);
                }

            }
            catch (Exception e)
            {
                return InternalServerError();
            }

            return Ok(schedule);
        }

        // PUT: api/ScheduleDay_Training/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutScheduleDay_Training(int id, ScheduleDay_Training scheduleDay_Training)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != scheduleDay_Training.ScheduleID)
            {
                return BadRequest();
            }

            db.Entry(scheduleDay_Training).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ScheduleDay_TrainingExists(id))
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

        // POST: api/ScheduleDay_Training
        [ResponseType(typeof(ScheduleDay_Training))]
        public IHttpActionResult PostScheduleDay_Training(ScheduleDay_Training scheduleDay_Training)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ScheduleDay_Training.Add(scheduleDay_Training);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (ScheduleDay_TrainingExists(scheduleDay_Training.ScheduleID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = scheduleDay_Training.ScheduleID }, scheduleDay_Training);
        }

        // DELETE: api/ScheduleDay_Training/5
        [ResponseType(typeof(ScheduleDay_Training))]
        public IHttpActionResult DeleteScheduleDay_Training(int id)
        {
            ScheduleDay_Training scheduleDay_Training = db.ScheduleDay_Training.Find(id);
            if (scheduleDay_Training == null)
            {
                return NotFound();
            }

            db.ScheduleDay_Training.Remove(scheduleDay_Training);
            db.SaveChanges();

            return Ok(scheduleDay_Training);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ScheduleDay_TrainingExists(int id)
        {
            return db.ScheduleDay_Training.Count(e => e.ScheduleID == id) > 0;
        }
    }
}