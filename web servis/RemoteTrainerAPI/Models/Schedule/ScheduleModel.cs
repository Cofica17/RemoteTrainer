namespace RemoteTrainerAPI.Models.Schedule
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using RemoteTrainerAPI.Models.Schedule.Classes;

    public partial class ScheduleModel : DbContext
    {
        public ScheduleModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Schedule> Schedule { get; set; }
        public virtual DbSet<TimeOfDay> TimeOfDay { get; set; }
        public virtual DbSet<Training> Training { get; set; }
        public virtual DbSet<ScheduleDay_Training> ScheduleDay_Training { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Schedule>()
                .HasMany(e => e.ScheduleDay_Training)
                .WithRequired(e => e.Schedule)
                .HasForeignKey(e => e.ScheduleID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<TimeOfDay>()
                .HasMany(e => e.ScheduleDay_Training)
                .WithRequired(e => e.TimeOfDay)
                .HasForeignKey(e => e.TimeOfDayID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Training>()
                .HasMany(e => e.ScheduleDay_Training)
                .WithRequired(e => e.Training)
                .HasForeignKey(e => e.TrainingID)
                .WillCascadeOnDelete(false);
        }
    }
}
