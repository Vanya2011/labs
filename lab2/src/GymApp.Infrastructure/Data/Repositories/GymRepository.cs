using GymApp.Domain.Interfaces;
using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;

namespace GymApp.Infrastructure.Data.Repositories
{
    public class GymRepository : IGymRepository
    {
        private readonly GymAppDbContext _context;
        public GymRepository(GymAppDbContext context)
        {
            _context = context;
        }
        public async Task AddAsync(Gym gym)
        {
            await _context.Gyms.AddAsync(gym);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAsync(int id)
        {
            var gym = await _context.Gyms.FindAsync(id);
            if (gym != null)
            {
                _context.Gyms.Remove(gym);
                await _context.SaveChangesAsync();
            }
        }

        public async Task<IEnumerable<Gym>> GetAllAsync()
        {
            return await _context.Gyms.ToListAsync();
        }

        public async Task<Gym> GetByIdAsync(int id)
        {
            return await _context.Gyms.FindAsync(id);
        }

        public async Task UpdateAsync(Gym gym)
        {
            _context.Gyms.Update(gym);
            await _context.SaveChangesAsync();
        }
    }
}
