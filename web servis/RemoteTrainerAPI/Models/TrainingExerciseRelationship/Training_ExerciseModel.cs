namespace RemoteTrainerAPI.Models.TrainingExerciseRelationship
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Training_ExerciseModel : DbContext
    {
        public Training_ExerciseModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Training_Exercise> Training_Exercise { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
