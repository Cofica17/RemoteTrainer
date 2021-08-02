using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels.Classes
{
    public class Exercise
    {
        public int IDExercise { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
    }
}