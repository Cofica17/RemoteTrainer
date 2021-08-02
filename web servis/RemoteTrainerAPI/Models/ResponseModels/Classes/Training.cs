using Newtonsoft.Json;
using RemoteTrainerAPI.Models.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.ResponseModels.Classes
{
    public class Training
    {
        public int ID { get; set; }
        public String Name { get; set; }
        public String TimeOfDay { get; set; }
        [JsonConverter(typeof(OnlyDateConverter))]
        public DateTime? DayDate { get; set; }
    }
}