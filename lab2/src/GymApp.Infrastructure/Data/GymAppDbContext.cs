using Microsoft.EntityFrameworkCore;
using GymApp.Domain.Models;
namespace GymApp.Infrastructure.Data
{
    public class GymAppDbContext : DbContext
    {
        public GymAppDbContext(DbContextOptions<GymAppDbContext> options) : base(options)
        {
        }
        public DbSet<Gym> Gyms { get; set; }
        public DbSet<Visitor> Visitors { get; set; }
        public DbSet<Membership> Memberships { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfigurationsFromAssembly(typeof(GymAppDbContext).Assembly);
            base.OnModelCreating(modelBuilder);
        }

    }
}
