using GymApp.Application.Interfaces;
using GymApp.Domain.Interfaces;
using GymApp.Domain.Models;

namespace GymApp.Application.Services
{
    public class MembershipService : IMembershipService
    {
        private readonly IMembershipRepository _membershipRepository;
        public MembershipService(IMembershipRepository membershipRepository)
        {
            _membershipRepository = membershipRepository;
        }

        public async Task<IEnumerable<Membership>> GetAllMembershipsAsync()
        {
            return await _membershipRepository.GetAllAsync();
        }

        public async Task<Membership> GetMembershipByIdAsync(int id)
        {
            return await _membershipRepository.GetByIdAsync(id);
        }

        public async Task CreateMembershipAsync(Membership membership)
        {
            await _membershipRepository.AddAsync(membership);
        }

        public async Task UpdateMembershipAsync(Membership membership)
        {
            await _membershipRepository.UpdateAsync(membership);
        }

        public async Task DeleteMembershipAsync(int id)
        {
            await _membershipRepository.DeleteAsync(id);
        }
    }
}
