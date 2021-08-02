namespace RemoteTrainerAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Trainer_User_RelModel : DbContext
    {
        public Trainer_User_RelModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Trainer_User_Relationship> Trainer_User_Relationship { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
