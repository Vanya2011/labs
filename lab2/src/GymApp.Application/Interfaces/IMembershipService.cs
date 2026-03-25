using GymApp.Domain.Models;

namespace GymApp.Application.Interfaces
{
    public interface IMembershipService
    {
        Task<IEnumerable<Membership>> GetAllMembershipsAsync();
        Task<Membership> GetMembershipByIdAsync(int id);
        Task CreateMembershipAsync(Membership membership);
        Task UpdateMembershipAsync(Membership membership);
        Task DeleteMembershipAsync(int id);
    }
}
