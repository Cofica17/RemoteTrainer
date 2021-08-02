namespace RemoteTrainerAPI.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Trainer_User_Relationship_RequestsModel : DbContext
    {
        public Trainer_User_Relationship_RequestsModel()
            : base("name=localCS")
        {
        }

        public virtual DbSet<Trainer_User_Relationship_Requests> Trainer_User_Relationship_Requests { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
