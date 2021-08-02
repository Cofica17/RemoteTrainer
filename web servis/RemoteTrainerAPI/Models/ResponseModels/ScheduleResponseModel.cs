using Newtonsoft.Json;
using RemoteTrainerAPI.Models.ResponseModels.Classes;
using RemoteTrainerAPI.Models.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels
{
    public class ScheduleResponseModel
    {
        public int ID { get; set; }
        [JsonConverter(typeof(OnlyDateConverter))]
        public DateTime? DateFrom { get; set; }
        [JsonConverter(typeof(OnlyDateConverter))]
        public DateTime? DateTo { get; set; }
        public List<RemoteTrainerAPI.Models.ResponseModels.Classes.Training> Trainings { get; set; }
    }
}