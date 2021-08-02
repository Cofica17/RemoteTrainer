namespace RemoteTrainerAPI.Models.Training.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Exercise")]
    public partial class Exercise
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Exercise()
        {
            Training = new HashSet<Training>();
        }

        [Key]
        public int IDExercise { get; set; }

        [Required]
        [StringLength(50)]
        public string ExerciseName { get; set; }

        public string ExerciseDescription { get; set; }

        public int TrainerID { get; set; }

        public int? VideoID { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Training> Training { get; set; }
    }
}
