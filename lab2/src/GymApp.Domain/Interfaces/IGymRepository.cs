using GymApp.Domain.Models;

namespace GymApp.Domain.Interfaces
{
    public interface IGymRepository
    {
        Task<Gym> GetByIdAsync(int id);
        Task<IEnumerable<Gym>> GetAllAsync();
        Task AddAsync(Gym gym);
        Task UpdateAsync(Gym gym);
        Task DeleteAsync(int id);
    }
}
