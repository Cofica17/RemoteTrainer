namespace RemoteTrainerAPI.Models.Training
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.Training.Classes;

    public partial class TrainingModel : DbContext
    {
        public TrainingModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Exercise> Exercise { get; set; }
        public virtual DbSet<Training> Training { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Exercise>()
                .HasMany(e => e.Training)
                .WithMany(e => e.Exercise)
                .Map(m => m.ToTable("Training_Exercise").MapLeftKey("ExerciseID").MapRightKey("TrainingID"));
        }
    }
}
