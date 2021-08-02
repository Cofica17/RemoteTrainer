namespace RemoteTrainerAPI.Models.LoginCredentialsClasses
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class LoginCredentials
    {
        [Key]
        public int IDLoginCredentials { get; set; }

        [Required]
        [StringLength(100)]
        public string Email { get; set; }

        [Required]
        [StringLength(12)]
        public string Pwd { get; set; }
    }
}
