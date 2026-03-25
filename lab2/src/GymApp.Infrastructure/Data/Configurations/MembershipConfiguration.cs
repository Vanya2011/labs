using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace GymApp.Infrastructure.Data.Configurations
{
    public class MembershipConfiguration : IEntityTypeConfiguration<Membership>
    {
        public void Configure(EntityTypeBuilder<Membership> builder)
        {
            builder.HasKey(m => m.Id);
            builder.Property(m => m.Price).HasColumnType("decimal(18,2)");

            builder.HasOne(m => m.Gym)
                .WithMany(g => g.Memberships)
                .HasForeignKey(m => m.GymId)
                .OnDelete(DeleteBehavior.Restrict);

            builder.HasOne(m => m.Visitor)
                .WithMany(v => v.Memberships)
                .HasForeignKey(m => m.VisitorId)
                .OnDelete(DeleteBehavior.Restrict);
        }
    }
}
