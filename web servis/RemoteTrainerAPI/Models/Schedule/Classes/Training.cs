namespace RemoteTrainerAPI.Models.Schedule.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Training")]
    public partial class Training
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Training()
        {
            ScheduleDay_Training = new HashSet<ScheduleDay_Training>();
        }

        [Key]
        public int IDTraining { get; set; }

        [Required]
        [StringLength(50)]
        public string TrainingName { get; set; }

        public string TrainingDescription { get; set; }

        public int TrainerID { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ScheduleDay_Training> ScheduleDay_Training { get; set; }
    }
}
