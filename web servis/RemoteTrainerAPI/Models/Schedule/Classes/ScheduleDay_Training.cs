namespace RemoteTrainerAPI.Models.Schedule.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class ScheduleDay_Training
    {
        [Key]
        [Column(Order = 0)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int ScheduleID { get; set; }

        [Key]
        [Column(Order = 1)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int TrainingID { get; set; }

        [Key]
        [Column(Order = 2)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int TimeOfDayID { get; set; }

        [Column(TypeName = "date")]
        public DateTime? DayDate { get; set; }

        public virtual Schedule Schedule { get; set; }

        public virtual TimeOfDay TimeOfDay { get; set; }

        public virtual Training Training { get; set; }
    }
}
