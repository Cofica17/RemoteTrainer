namespace RemoteTrainerAPI.Models.TrainerUserRelationship.Classes
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("RegisteredUser")]
    public partial class RegisteredUser
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public RegisteredUser()
        {
            Trainers = new HashSet<RegisteredUser>();
            Users = new HashSet<RegisteredUser>();
        }

        [Key]
        public int IDRegisteredUser { get; set; }

        [Required]
        public string Firstname { get; set; }

        [Required]
        public string Lastname { get; set; }

        public int LoginCredentialsID { get; set; }

        public int UserTypeID { get; set; }

        public virtual LoginCredentials LoginCredentials { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<RegisteredUser> Users { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<RegisteredUser> Trainers { get; set; }
    }
}
