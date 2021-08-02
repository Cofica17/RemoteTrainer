using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels
{
    public class TrainingResponseModel
    {
        public int IDTraining { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public List<RemoteTrainerAPI.Models.ResponseModels.Classes.Exercise> Exercises { get; set; }
    }
}