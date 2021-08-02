using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.RequestModels
{
    public class ExerciseRequestModel
    {
        public String Name { get; set; }
        public String Description { get; set; }
        public String VideoName { get; set; }
        public String VideoPath { get; set; }
        public int TrainerID { get; set; }
    }
}