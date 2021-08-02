namespace RemoteTrainerAPI.Models.Exercise.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Video")]
    public partial class Video
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Video()
        {
            Exercise = new HashSet<Exercise>();
        }

        [Key]
        public int IDVideo { get; set; }

        [Required]
        [StringLength(50)]
        public string VideoName { get; set; }

        [Required]
        public string VideoFilepath { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Exercise> Exercise { get; set; }
    }
}
