using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels
{
    public class TrainerProfileResponseModel
    {
        public int IDTrainer { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string DegreeURL { get; set; }
        public string CvURL { get; set; }
        public byte[] ProfileImage { get; set; }
    }
}