namespace RemoteTrainerAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.LoginCredentialsClasses;

    public partial class LoginCredentialsModel : DbContext
    {
        public LoginCredentialsModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<LoginCredentials> LoginCredentials { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
