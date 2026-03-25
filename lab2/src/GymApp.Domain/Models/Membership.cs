namespace GymApp.Domain.Models
{
    public class Membership
    {
        public int Id { get; set; }
        public string MembershipType { get; set; }
        public DateTime IssueDate { get; set; }
        public DateTime ExpirationDate { get; set; }
        public decimal Price { get; set; }
        public int GymId { get; set; }
        public Gym? Gym { get; set; }
        public int VisitorId { get; set; }
        public Visitor? Visitor { get; set; }
    }
}
