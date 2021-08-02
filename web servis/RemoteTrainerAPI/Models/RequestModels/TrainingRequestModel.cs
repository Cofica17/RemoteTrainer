using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.RequestModels
{
    public class TrainingRequestModel
    {
        public String Name { get; set; }
        public String Description { get; set; }
        public int TrainerID { get; set; }
        public List<int> ExercisesIDs { get; set; }
    }
}