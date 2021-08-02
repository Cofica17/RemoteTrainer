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
using RemoteTrainerAPI.Models.Exercise;
using RemoteTrainerAPI.Models.RequestModels;
using RemoteTrainerAPI.Models.ResponseModels;
using RemoteTrainerAPI.Models.Training;
using RemoteTrainerAPI.Models.Training.Classes;
using RemoteTrainerAPI.Models.TrainingExerciseRelationship;

namespace RemoteTrainerAPI.Controllers
{
    public class TrainingsController : ApiController
    {
        private TrainingModel db = new TrainingModel();
        private Training_ExerciseModel training_ExerciseModel = new Training_ExerciseModel();

        // GET: api/Trainings
        public IQueryable<Training> GetTraining()
        {
            return db.Training;
        }

        [Route("api/getTraining")]
        [ResponseType(typeof(TrainingResponseModel))]
        [HttpPost]
        public IHttpActionResult GetTraining([FromBody]int id)
        {
            Training training = db.Training.Find(id);
            if (training == null)
            {
                return NotFound();
            }

            List<RemoteTrainerAPI.Models.ResponseModels.Classes.Exercise> exercises = new List<RemoteTrainerAPI.Models.ResponseModels.Classes.Exercise>();

            foreach (Exercise exercise in training.Exercise)
            {
                RemoteTrainerAPI.Models.ResponseModels.Classes.Exercise responseExercise = new RemoteTrainerAPI.Models.ResponseModels.Classes.Exercise
                {
                    IDExercise = exercise.IDExercise,
                    Name = exercise.ExerciseName,
                    Description = exercise.ExerciseDescription
                };

                exercises.Add(responseExercise);
            }

            TrainingResponseModel trainingResponseModel = new TrainingResponseModel
            {
                IDTraining = training.IDTraining,
                Name = training.TrainingName,
                Description = training.TrainingDescription,
                Exercises = exercises
            };

            return Ok(trainingResponseModel);
        }


        [Route("api/getTrainingsForTrainer")]
        [ResponseType(typeof(RemoteTrainerAPI.Models.ResponseModels.Classes.Training))]
        [HttpPost]
        public IHttpActionResult GetTrainingsForTrainer([FromBody]int id)
        {
            List<Training> trainings = db.Training.Where(t => t.TrainerID == id).ToList();

            List<RemoteTrainerAPI.Models.ResponseModels.Classes.Training> trainingsResponse = new List<Models.ResponseModels.Classes.Training>();

            foreach (Training tr in trainings)
            {
                RemoteTrainerAPI.Models.ResponseModels.Classes.Training training = new RemoteTrainerAPI.Models.ResponseModels.Classes.Training
                {
                    Name = tr.TrainingName,
                    ID = tr.IDTraining

                };

                trainingsResponse.Add(training);
            }

            return Ok(trainingsResponse);
        }

        // POST: api/Trainings
        [Route("api/postTraining")]
        [HttpPost]
        public IHttpActionResult PostTraining(TrainingRequestModel training)
        {
            if (training == null)
            {
                return BadRequest();
            }

            Training tr = new Training
            {
                TrainingName = training.Name,
                TrainingDescription = training.Description,
                TrainerID = training.TrainerID,
            };

            db.Training.Add(tr);
            db.SaveChanges();

            foreach (int id in training.ExercisesIDs)
            {
                training_ExerciseModel.Training_Exercise.Add(new Training_Exercise { TrainingID = tr.IDTraining, ExerciseID = id });
                training_ExerciseModel.SaveChanges();
            }

            return Ok();
            
        }

        // DELETE: api/Trainings/5
        [ResponseType(typeof(Training))]
        public IHttpActionResult DeleteTraining(int id)
        {
            Training training = db.Training.Find(id);
            if (training == null)
            {
                return NotFound();
            }

            db.Training.Remove(training);
            db.SaveChanges();

            return Ok(training);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TrainingExists(int id)
        {
            return db.Training.Count(e => e.IDTraining == id) > 0;
        }
    }
}