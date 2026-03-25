namespace GymApp.Domain.Models
{
    public class Gym
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public ICollection<Membership> Memberships { get; set; } = new List<Membership>();
    }
}
