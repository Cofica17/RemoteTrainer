namespace RemoteTrainerAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.RegisteredUserClasses;

    public partial class RegisteredUserModel : DbContext
    {
        public RegisteredUserModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<LoginCredentials> LoginCredentials { get; set; }
        public virtual DbSet<RegisteredUser> RegisteredUser { get; set; }
        public virtual DbSet<UserType> UserType { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<LoginCredentials>()
                .HasMany(e => e.RegisteredUser)
                .WithRequired(e => e.LoginCredentials)
                .HasForeignKey(e => e.LoginCredentialsID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<UserType>()
                .HasMany(e => e.RegisteredUser)
                .WithRequired(e => e.UserType)
                .HasForeignKey(e => e.UserTypeID)
                .WillCascadeOnDelete(false);
        }
    }
}
