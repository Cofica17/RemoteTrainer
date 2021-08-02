using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RemoteTrainerAPI.Models.RequestModels
{
    public class ScheduleForUserRequestModel
    {
        public int IDUser { get; set; }
        public DateTime TodayDate { get; set; }
    }
}