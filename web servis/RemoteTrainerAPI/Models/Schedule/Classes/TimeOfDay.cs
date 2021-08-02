namespace RemoteTrainerAPI.Models.Schedule.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("TimeOfDay")]
    public partial class TimeOfDay
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public TimeOfDay()
        {
            ScheduleDay_Training = new HashSet<ScheduleDay_Training>();
        }

        [Key]
        public int IDTimeOfDay { get; set; }

        [Column("TimeOfDay")]
        [StringLength(50)]
        public string TimeOfDay1 { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ScheduleDay_Training> ScheduleDay_Training { get; set; }
    }
}
