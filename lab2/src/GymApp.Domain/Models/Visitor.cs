namespace GymApp.Domain.Models
{
    public class Visitor
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string WorkoutRoutine { get; set; }
        public string DietaryNotes { get; set; }
        public ICollection<Membership> Memberships { get; set; } = new List<Membership>();
    }
}
