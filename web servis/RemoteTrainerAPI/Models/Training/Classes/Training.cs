namespace RemoteTrainerAPI.Models.Training.Classes
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
            Exercise = new HashSet<Exercise>();
        }

        [Key]
        public int IDTraining { get; set; }

        [Required]
        [StringLength(50)]
        public string TrainingName { get; set; }

        public string TrainingDescription { get; set; }

        public int TrainerID { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Exercise> Exercise { get; set; }
    }
}
