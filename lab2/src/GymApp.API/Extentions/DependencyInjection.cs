using GymApp.Domain.Interfaces;
using GymApp.Infrastructure.Data;
using GymApp.Infrastructure.Data.Repositories;
using Microsoft.EntityFrameworkCore;
using GymApp.Application.Interfaces;
using GymApp.Application.Services;
namespace GymApp.API.Extentions
{
    public static class DependencyInjection
    {
        public static IServiceCollection AddInfrastructure(this IServiceCollection services, IConfiguration configuration)
        {
            services.AddDbContext<GymAppDbContext>(options =>
                options.UseNpgsql(configuration.GetConnectionString("DefaultConnectionString")));

            services.AddScoped<IGymRepository, GymRepository>();
            services.AddScoped<IVisitorRepository, VisitorRepository>();
            services.AddScoped<IMembershipRepository, MembershipRepository>();
            return services;
        }
        public static IServiceCollection AddApplication(this IServiceCollection services)
        {
            services.AddScoped<IMembershipService, MembershipService>();
            return services;
        }
    }
}
