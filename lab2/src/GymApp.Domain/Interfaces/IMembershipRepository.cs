using GymApp.Domain.Models;

namespace GymApp.Domain.Interfaces
{
    public interface IMembershipRepository
    {
        Task<Membership> GetByIdAsync(int id);
        Task<IEnumerable<Membership>> GetAllAsync();
        Task AddAsync(Membership membership);
        Task UpdateAsync(Membership membership);
        Task DeleteAsync(int id);
    }
}
