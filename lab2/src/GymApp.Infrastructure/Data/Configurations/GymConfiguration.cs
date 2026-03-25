using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
namespace GymApp.Infrastructure.Data.Configurations
{
    public class GymConfiguration : IEntityTypeConfiguration<Gym>
    {
        public void Configure(EntityTypeBuilder<Gym> builder)
        {
            builder.HasKey(g => g.Id);
            builder.Property(g => g.Name).IsRequired().HasMaxLength(100);
            builder.Property(g => g.Address).IsRequired().HasMaxLength(200);

            builder.HasData(
                new Gym { Id = 1, Name = "Олімп", Address = "вул піонерська" },
                new Gym { Id = 2, Name = "Атон", Address = "вул машинобудівників" }
            );
        }
    }
}
