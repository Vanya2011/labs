using GymApp.Domain.Models;

namespace GymApp.Domain.Interfaces
{
    public interface IVisitorRepository
    {
        Task<Visitor> GetByIdAsync(int id);
        Task<IEnumerable<Visitor>> GetAllAsync();
        Task AddAsync(Visitor visitor);
        Task UpdateAsync(Visitor visitor);
        Task DeleteAsync(int id);
    }
}
