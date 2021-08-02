namespace RemoteTrainerAPI.Models
{
    using RemoteTrainerAPI.Models.RegisteredUserClasses;
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("RegisteredUser")]
    public partial class RegisteredUser
    {
        [Key]
        public int IDRegisteredUser { get; set; }

        [Required]
        public string Firstname { get; set; }

        [Required]
        public string Lastname { get; set; }

        public int LoginCredentialsID { get; set; }

        public int UserTypeID { get; set; }

        public virtual LoginCredentials LoginCredentials { get; set; }

        public virtual UserType UserType { get; set; }
    }
}
