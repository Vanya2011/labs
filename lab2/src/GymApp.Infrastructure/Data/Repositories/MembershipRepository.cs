using GymApp.Domain.Interfaces;
using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;

namespace GymApp.Infrastructure.Data.Repositories
{
    public class MembershipRepository : IMembershipRepository
    {
        private readonly GymAppDbContext _context;

        public MembershipRepository(GymAppDbContext context)
        {
            _context = context;
        }
        public async Task<Membership> GetByIdAsync(int id)
        {
            return await _context.Memberships
                .Include(m => m.Gym)
                .Include(m => m.Visitor)
                .FirstOrDefaultAsync(m => m.Id == id);
        }
        public async Task<IEnumerable<Membership>> GetAllAsync()
        {
            return await _context.Memberships
                .Include(m => m.Gym)
                .Include(m => m.Visitor)
                .ToListAsync();
        }
        public async Task AddAsync(Membership membership)
        {
            await _context.Memberships.AddAsync(membership);
            await _context.SaveChangesAsync(); 
        }
        public async Task UpdateAsync(Membership membership)
        {
            _context.Memberships.Update(membership);
            await _context.SaveChangesAsync();
        }
        public async Task DeleteAsync(int id)
        {
            var membership = await _context.Memberships.FindAsync(id);
            if (membership != null)
            {
                _context.Memberships.Remove(membership);
                await _context.SaveChangesAsync();
            }
        }
    }
}
