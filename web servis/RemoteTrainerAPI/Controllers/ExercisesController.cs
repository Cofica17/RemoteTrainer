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
using RemoteTrainerAPI.Models.Exercise.Classes;
using RemoteTrainerAPI.Models.RequestModels;
using RemoteTrainerAPI.Models.ResponseModels;

namespace RemoteTrainerAPI.Controllers
{
    public class ExercisesController : ApiController
    {
        private ExerciseModel db = new ExerciseModel();

        // GET: api/Exercises
        public IQueryable<Exercise> GetExercise()
        {
            return db.Exercise;
        }

        // GET: api/Exercises/5
        [Route("api/getExercise")]
        [ResponseType(typeof(ExerciseResponseModel))]
        [HttpPost]
        public IHttpActionResult GetExercise([FromBody]int id)
        {
            Exercise exercise = db.Exercise.Find(id);

            if (exercise == null)
            {
                return NotFound();
            }

            RemoteTrainerAPI.Models.ResponseModels.Classes.Video video = new RemoteTrainerAPI.Models.ResponseModels.Classes.Video
            {
                Name = exercise.Video.VideoName,
                Path = exercise.Video.VideoFilepath
            };

            ExerciseResponseModel exerciseResponseModel = new ExerciseResponseModel
            {
                IDExercise = exercise.IDExercise,
                Name = exercise.ExerciseName,
                Description = exercise.ExerciseDescription,
                Video = video
            };

            return Ok(exerciseResponseModel);
        }


        [Route("api/getExercisesForTrainer")]
        [ResponseType(typeof(List<ExerciseResponseModel>))]
        [HttpPost]
        public IHttpActionResult GetExercisesForTrainer([FromBody]int id)
        {
            List<Exercise> exercises = db.Exercise.Where(e => e.TrainerID == id).ToList();

            if (exercises == null)
            {
                return NotFound();
            }


            List<ExerciseResponseModel> exercisesResponse = new List<ExerciseResponseModel>();

            foreach (Exercise exercise in exercises)
            {
                RemoteTrainerAPI.Models.ResponseModels.Classes.Video video = new RemoteTrainerAPI.Models.ResponseModels.Classes.Video
                {
                    Name = exercise.Video.VideoName,
                    Path = exercise.Video.VideoFilepath
                };

                exercisesResponse.Add(new ExerciseResponseModel
                {
                    IDExercise = exercise.IDExercise,
                    Name = exercise.ExerciseName,
                    Description = exercise.ExerciseDescription,
                    Video = video
                });
            }

            return Ok(exercisesResponse);
        }


        [Route("api/postExercise")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PostExercise(ExerciseRequestModel exercise)
        {
            Exercise ex = new Exercise
            {
                ExerciseName = exercise.Name,
                ExerciseDescription = exercise.Description,
                Video = new Video
                {
                    VideoName = exercise.VideoName,
                    VideoFilepath = exercise.VideoPath
                },
                TrainerID = exercise.TrainerID
            };

            db.Exercise.Add(ex);
            db.SaveChanges();
            return Ok();
        }
    }
}