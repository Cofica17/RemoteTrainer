namespace RemoteTrainerAPI.Models.TrainerUserRelationship
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.TrainerUserRelationship.Classes;

    public partial class TrainerUserRelationship : DbContext
    {
        public TrainerUserRelationship()
            : base("name=localCS")
        {
        }

        public virtual DbSet<LoginCredentials> LoginCredentials { get; set; }
        public virtual DbSet<RegisteredUser> RegisteredUser { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<LoginCredentials>()
                .HasMany(e => e.RegisteredUser)
                .WithRequired(e => e.LoginCredentials)
                .HasForeignKey(e => e.LoginCredentialsID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<RegisteredUser>()
                .HasMany(e => e.Users)
                .WithMany(e => e.Trainers)
                .Map(m => m.ToTable("Trainer_User_Relationship").MapLeftKey("TrainerID").MapRightKey("UserID"));
        }
    }
}
