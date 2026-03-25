using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace GymApp.Infrastructure.Data.Configurations
{
    public class VisitorConfiguration : IEntityTypeConfiguration<Visitor>
    {
        public void Configure(EntityTypeBuilder<Visitor> builder)
        {
            builder.HasKey(v => v.Id);
            builder.Property(v => v.FirstName).IsRequired().HasMaxLength(50);
            builder.Property(v => v.LastName).IsRequired().HasMaxLength(50);

            builder.HasData(
                new Visitor
                {
                    Id = 1,
                    FirstName = "Ваня",
                    LastName = "Шевченко",
                    WorkoutRoutine = "Кардіо 2 рази на тиждень",
                    DietaryNotes = "Без обмежень"
                },
                new Visitor
                {
                    Id = 2,
                    FirstName = "Саша",
                    LastName = "Коваленко",
                    WorkoutRoutine = "3 рази на тиждень силові тренування",
                    DietaryNotes = "Високобілкова дієта"
                }
            );
        }
    }
}
