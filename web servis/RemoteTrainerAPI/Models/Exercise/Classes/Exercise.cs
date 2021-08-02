namespace RemoteTrainerAPI.Models.Exercise.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Exercise")]
    public partial class Exercise
    {
        [Key]
        public int IDExercise { get; set; }

        [Required]
        [StringLength(50)]
        public string ExerciseName { get; set; }

        public string ExerciseDescription { get; set; }

        public int TrainerID { get; set; }

        public int? VideoID { get; set; }

        public virtual Video Video { get; set; }
    }
}
