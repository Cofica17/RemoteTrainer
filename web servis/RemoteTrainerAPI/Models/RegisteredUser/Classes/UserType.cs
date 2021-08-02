namespace RemoteTrainerAPI.Models.RegisteredUserClasses
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("UserType")]
    public partial class UserType
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public UserType()
        {
            RegisteredUser = new HashSet<RegisteredUser>();
        }

        [Key]
        public int IDUserType { get; set; }

        [Column("UserType")]
        [StringLength(20)]
        public string UserType1 { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<RegisteredUser> RegisteredUser { get; set; }
    }
}
