namespace RemoteTrainerAPI.Models.Exercise
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.Exercise.Classes;

    public partial class ExerciseModel : DbContext
    {
        public ExerciseModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Exercise> Exercise { get; set; }
        public virtual DbSet<Video> Video { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Video>()
                .HasMany(e => e.Exercise)
                .WithOptional(e => e.Video)
                .HasForeignKey(e => e.VideoID);
        }
    }
}
