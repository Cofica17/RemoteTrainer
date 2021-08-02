using RemoteTrainerAPI.Models.Exercise.Classes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels
{
    public class ExerciseResponseModel
    {
        public int IDExercise { get; set; }
        public String Name { get; set; }
        public String Description { get; set; }
        public RemoteTrainerAPI.Models.ResponseModels.Classes.Video Video { get; set; }
    }
}