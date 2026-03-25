using GymApp.Domain.Interfaces;
using GymApp.Domain.Models;
using Microsoft.EntityFrameworkCore;

namespace GymApp.Infrastructure.Data.Repositories
{
    public class VisitorRepository : IVisitorRepository
    {
        private readonly GymAppDbContext _context;
        public VisitorRepository(GymAppDbContext context)
        {
            _context = context;
        }
        public async Task<Visitor> GetByIdAsync(int id) => await _context.Visitors.FindAsync(id);
        public async Task<IEnumerable<Visitor>> GetAllAsync() => await _context.Visitors.ToListAsync();
        public async Task AddAsync(Visitor visitor) 
        { 
            await _context.Visitors.AddAsync(visitor);
            await _context.SaveChangesAsync();
        }

        public async Task UpdateAsync(Visitor visitor) 
        {
            _context.Visitors.Update(visitor);
            await _context.SaveChangesAsync(); 
        }

        public async Task DeleteAsync(int id) 
        {
            var v = await _context.Visitors.FindAsync(id); 
            if (v != null) 
            {
                _context.Visitors.Remove(v);
                await _context.SaveChangesAsync(); 
            }

        }
    }
}
